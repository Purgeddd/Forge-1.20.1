package net.purgeddd.testmod.entity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.animal.Turtle;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;
import net.purgeddd.testmod.entity.ai.FollowPlayerGoal;
import net.purgeddd.testmod.entity.ai.ObsidianGolemFlingGoal;
import net.purgeddd.testmod.entity.ai.ObsidianGolemSlamGoal;
import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.*;
import software.bernie.geckolib.core.object.PlayState;

import javax.annotation.Nullable;
import java.util.Random;

public class ObsidianGolemEntity extends Monster implements GeoEntity {

    private AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    // Boolean flag to track if the entity is attacking
    private boolean isAttacking = false;

    public ObsidianGolemEntity(EntityType<? extends Monster> p_33002_, Level p_33003_) {
        super(p_33002_, p_33003_);
    }

    public static AttributeSupplier setAttributes() {
        return Monster.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 50D)
                .add(Attributes.ATTACK_DAMAGE, 15f)
                .add(Attributes.ATTACK_SPEED, 0.1f)
                .add(Attributes.ATTACK_KNOCKBACK, 300)
                .add(Attributes.MOVEMENT_SPEED, 0.3f).build();

    }

    protected void registerGoals() {
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
        //this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, false));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new FollowPlayerGoal(this, 1, 32));
        this.goalSelector.addGoal(1, new ObsidianGolemSlamGoal(this, 1.0D, false));
        this.goalSelector.addGoal(1, new ObsidianGolemFlingGoal(this, 1.0D, false));
        this.addBehaviourGoals();
    }

    protected void addBehaviourGoals() {
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal(this, AbstractVillager.class, false));
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal(this, IronGolem.class, true));
        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal(this, Turtle.class, 10, true, false, Turtle.BABY_ON_LAND_SELECTOR));
    }

    // Register the animation controllers
    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {
        controllerRegistrar.add(new AnimationController<>(this, "controller", 0, this::predicate));
        controllerRegistrar.add(new AnimationController<>(this, "slamController", 0, this::slamPredicate));
        controllerRegistrar.add(new AnimationController<>(this, "flingController", 0, this::flingPredicate));
    }

    private PlayState slamPredicate(AnimationState<ObsidianGolemEntity> obsidianGolemEntityAnimationState) {

        if(this.swinging && obsidianGolemEntityAnimationState.getController().getAnimationState() == AnimationController.State.STOPPED) {
            obsidianGolemEntityAnimationState.getController().forceAnimationReset();
            obsidianGolemEntityAnimationState.getController().setAnimation(RawAnimation.begin().then("obsidiangolemslam", Animation.LoopType.PLAY_ONCE));
            this.swinging = false;
        }

        return PlayState.CONTINUE;
    }

    private PlayState flingPredicate(AnimationState<ObsidianGolemEntity> obsidianGolemEntityAnimationState) {

        if(this.swinging && obsidianGolemEntityAnimationState.getController().getAnimationState() == AnimationController.State.STOPPED) {
            obsidianGolemEntityAnimationState.getController().forceAnimationReset();
            obsidianGolemEntityAnimationState.getController().setAnimation(RawAnimation.begin().then("obsidiangolemfling", Animation.LoopType.PLAY_ONCE));
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
            // Play the walking animation
            tAnimationState.getController().setAnimation(RawAnimation.begin().then("obsidiangolemwalk", Animation.LoopType.LOOP));
            return PlayState.CONTINUE;
        }

        // Default idle animation
        tAnimationState.getController().setAnimation(RawAnimation.begin().then("obsidiangolemidle", Animation.LoopType.LOOP));
        return PlayState.CONTINUE;
    }


    // Set attacking flag
    public void setAttacking(boolean attacking) {
        this.isAttacking = attacking;
    }

    // Play step sound
    @Override
    @Nullable
    public void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.iron_golem.step")), 0.30f, 0.5f);
    }

    @Override
    public SoundEvent getAmbientSound() {
        return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.iron_golem.ambient"));
    }

    public SoundEvent getHurtSound(DamageSource damageSource) {
        return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.iron_golem.hurt"));
    }

    public SoundEvent getDeathSound() {
        return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.iron_golem.death"));
    }

    private final Random random = new Random();
    private boolean useFirstAttack = true;
    private int attackCooldown = 0;

    @Override
    public void tick() {
        super.tick();
        // Check and update the attack selection based on cooldown or conditions.
        if (attackCooldown <= 0) {
            // Randomly decide which attack to use next.
            useFirstAttack = random.nextBoolean();
            attackCooldown = 100; // Set cooldown to 5 seconds (20 ticks per second).
        } else {
            attackCooldown--;
        }
    }

    public boolean shouldUseFirstAttack() {
        return useFirstAttack;
    }


    }




