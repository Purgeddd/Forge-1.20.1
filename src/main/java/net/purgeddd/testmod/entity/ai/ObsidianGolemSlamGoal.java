package net.purgeddd.testmod.entity.ai;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.purgeddd.testmod.entity.custom.ObsidianGolemEntity;

import java.util.EnumSet;

public class ObsidianGolemSlamGoal extends Goal {
    protected final ObsidianGolemEntity mob;  // Change to specific ObsidianGolemEntity to access custom methods
    private final double speedModifier;
    private final boolean followingTargetEvenIfNotSeen;
    private int ticksUntilNextAttack;
    private final int attackInterval = 40;  // Total ticks for the attack cycle

    public ObsidianGolemSlamGoal(ObsidianGolemEntity mob, double speedModifier, boolean followingTargetEvenIfNotSeen) {
        this.mob = mob;
        this.speedModifier = speedModifier;
        this.followingTargetEvenIfNotSeen = followingTargetEvenIfNotSeen;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK, Goal.Flag.TARGET));
    }

    @Override
    public boolean canUse() {
        LivingEntity target = this.mob.getTarget();
        return target != null && target.isAlive() && this.mob.distanceToSqr(target) <= this.getAttackReachSqr(target) && this.mob.shouldUseFirstAttack();
    }

    @Override
    public void start() {

            this.mob.getNavigation().stop();
            this.mob.setAttacking(true);  // Start the attack animation
            this.ticksUntilNextAttack = 0;
    }

    @Override
    public void stop() {
        this.mob.setAttacking(false);  // Stop the attack animation
        this.mob.getNavigation().stop();
    }

    @Override
    public boolean canContinueToUse() {
        return this.mob.getTarget() != null && this.mob.getTarget().isAlive();
    }

    @Override
    public void tick() {
        LivingEntity target = this.mob.getTarget();
        if (target != null) {
            this.mob.getLookControl().setLookAt(target, 30.0F, 30.0F);

            double distanceSq = this.mob.distanceToSqr(target);

            // Start the attack if we're close enough
            if (distanceSq <= this.getAttackReachSqr(target)) {
                if (this.ticksUntilNextAttack == 0) {
                    this.mob.swing(InteractionHand.MAIN_HAND);  // Visual swing
                }

                // Deal damage on the 20th tick of the attack cycle
                if (this.ticksUntilNextAttack == 15) {
                    this.mob.doHurtTarget(target);  // Apply damage at this point
                }

                // Reset the attack after 40 ticks (or customize this value as needed)AS
                this.ticksUntilNextAttack++;
                if (this.ticksUntilNextAttack >= this.attackInterval) {
                    this.resetAttack();
                }
            }
        }
    }

    private void resetAttack() {
        this.ticksUntilNextAttack = 0;
        this.mob.setAttacking(false);  // Stop the attack animation after completing
    }

    protected double getAttackReachSqr(LivingEntity target) {
        return (this.mob.getBbWidth() * 2.0F * this.mob.getBbWidth() * 2.0F + target.getBbWidth());
    }
}

