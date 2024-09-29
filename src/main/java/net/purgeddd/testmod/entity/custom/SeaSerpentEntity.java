package net.purgeddd.testmod.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import net.minecraft.world.entity.animal.Squid;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.animal.axolotl.Axolotl;
import net.minecraft.world.entity.monster.Drowned;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.purgeddd.testmod.entity.ai.AquaticPathNavigation;
import net.purgeddd.testmod.entity.ai.FollowPlayerGoal;
import net.purgeddd.testmod.entity.ai.SeaSerpentBiteGoal;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;

import java.util.EnumSet;

public class SeaSerpentEntity extends WaterAnimal implements GeoEntity {

    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    public SeaSerpentEntity(EntityType<? extends WaterAnimal> p_30341_, Level p_30342_) {
        super(p_30341_, p_30342_);
    }

    // Swim more naturally in water
    @Override
    protected void handleAirSupply(int air) {
        this.setAirSupply(this.getMaxAirSupply()); // Prevents drowning
    }

    // Additional entity capabilities (e.g., aggression)
    public boolean isAggressive() {
        return this.getTarget() != null;
    }

    public static AttributeSupplier setAttributes() {
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 100D)
                .add(Attributes.ATTACK_DAMAGE, 20f)
                .add(Attributes.ATTACK_SPEED, 0.1f)
                .add(Attributes.ATTACK_KNOCKBACK, 300)
                .add(Attributes.MOVEMENT_SPEED, 0.2f).build();

    }

    protected void registerGoals() {
        this.goalSelector.addGoal(0, new BreathAirGoal(this));
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(1, new SeaSerpentBiteGoal(this, 1, false));
        this.goalSelector.addGoal(2 , new TargetGoal());
        this.goalSelector.addGoal(3, new RandomSwimmingGoal(this, 0.8F, 3));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new FollowBoatGoal(this));
        this.addBehaviourGoals();
    }

    protected void addBehaviourGoals() {
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Squid.class, 40, false, true, null));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, AbstractSchoolingFish.class, 100, false, true, null));
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, AbstractSchoolingFish.class, 100, false, true, null));
        this.targetSelector.addGoal(6, new NearestAttackableTargetGoal<>(this, Axolotl.class, 4, false, true, null));

    }

    @Override
    public boolean shouldDespawnInPeaceful() {
        return true;  // Forces the entity to despawn in peaceful mode
    }


    // Register the animation controllers
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::predicate));
        controllerRegistrar.add(new AnimationController<>(this, "biteController", 0, this::bitePredicate));
    }

    private PlayState bitePredicate(AnimationState<SeaSerpentEntity> seaSerpentEntityAnimationState) {

        if (this.swinging && seaSerpentEntityAnimationState.getController().getAnimationState() == AnimationController.State.STOPPED) {
            seaSerpentEntityAnimationState.getController().forceAnimationReset();
            seaSerpentEntityAnimationState.getController().setAnimation(RawAnimation.begin().then("sea_serpent_bite", Animation.LoopType.PLAY_ONCE));
            this.swinging = false;
        }

        return PlayState.CONTINUE;
    }


    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }


    private <T extends GeoAnimatable> PlayState predicate(AnimationState<T> tAnimationState) {

        if (tAnimationState.isMoving()) {
            tAnimationState.getController().setAnimation(RawAnimation.begin().then("sea_serpent_idle", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        }

        // Default idle animation
        tAnimationState.getController().setAnimation(RawAnimation.begin().then("sea_serpent_swim", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }

    private int suffocationTimer = 0;
    private static final int SUFFOCATION_THRESHOLD = 50;

    @Override
    public void tick() {
        super.tick();

        // Check if the entity is in water
        if (this.isInWaterRainOrBubble()) {
            // Reset the suffocation timer if in water
            this.suffocationTimer = 0;
        } else {
            // Increment the suffocation timer if not in water
            this.suffocationTimer++;

            // If the suffocation timer exceeds the threshold, deal damage
            if (this.suffocationTimer > SUFFOCATION_THRESHOLD) {
                // Apply damage every 20 ticks (1 second)
                if (this.suffocationTimer % 20 == 0) {
                    this.hurt(damageSources().dryOut(), 5.0F); // Adjust damage amount as needed
                }
            }
        }
    }


    // Set attacking flag
    public void setAttacking(boolean attacking) {
        this.isAttacking = attacking;
    }

    // Boolean flag to track if the entity is attacking
    private boolean isAttacking = false;

    protected PathNavigation createNavigation(Level worldIn) {
        return new AquaticPathNavigation(this, worldIn);
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.COD_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.COD_HURT;
    }

    public void travel(Vec3 travelVector) {
        if (this.isEffectiveAi() && this.isInWater()) {
            this.moveRelative(this.getSpeed(), travelVector);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
            if (this.getTarget() == null) {
                this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.005D, 0.0D));
            }
        } else {
            super.travel(travelVector);
        }

    }

    private class TargetGoal extends Goal {

        public TargetGoal() {
            this.setFlags(EnumSet.of(Flag.MOVE));
        }

        @Override
        public boolean canUse() {
            return SeaSerpentEntity.this.getTarget() != null && SeaSerpentEntity.this.getTarget().isAlive();
        }

        public void tick() {
            LivingEntity target = SeaSerpentEntity.this.getTarget();
            double speed = 1.0F;
            if (SeaSerpentEntity.this.distanceTo(target) < 10) {
                if (SeaSerpentEntity.this.distanceTo(target) < 1.9D) {
                    SeaSerpentEntity.this.doHurtTarget(target);
                    speed = 0.8F;
                } else {
                    speed = 0.6F;
                    SeaSerpentEntity.this.lookAt(target, 70, 70);
                    if (target instanceof Squid) {
                        Vec3 mouth = SeaSerpentEntity.this.position();
                        float squidSpeed = 0.07F;
                        ((Squid) target).setMovementVector((float) (mouth.x - target.getX()) * squidSpeed, (float) (mouth.y - target.getEyeY()) * squidSpeed, (float) (mouth.z - target.getZ()) * squidSpeed);
                        SeaSerpentEntity.this.level().broadcastEntityEvent(SeaSerpentEntity.this, (byte) 68);
                    }
                }
            }
            if (target instanceof Drowned || target instanceof Player) {
                speed = 1.0F;
            }
            SeaSerpentEntity.this.getNavigation().moveTo(target, speed);
        }
    }

    public static boolean checkSeaSerpentSpawnRules(EntityType<? extends LivingEntity> p_217018_, ServerLevelAccessor p_217019_, MobSpawnType p_217020_, BlockPos p_217021_, RandomSource p_217022_) {
        return p_217021_.getY() <= p_217019_.getSeaLevel() - 40 && p_217019_.getRawBrightness(p_217021_, 0) == 0 && p_217019_.getBlockState(p_217021_).is(Blocks.WATER);
    }

}













