package net.purgeddd.testmod.entity.ai;

import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import java.util.EnumSet;

public class FollowPlayerGoal extends Goal {
    private final Mob mob;
    private Player closestPlayer;
    private final double speed;
    private final float maxDistance;  // Maximum distance to detect players
    private final Level level;

    public FollowPlayerGoal(Mob mob, double speed, float maxDistance) {
        this.mob = mob;
        this.speed = speed;
        this.maxDistance = maxDistance;
        this.level = mob.level();
        this.setFlags(EnumSet.of(Goal.Flag.MOVE));  // Set goal flags, MOVE for movement
    }

    @Override
    public boolean canUse() {
        // Find the nearest player within maxDistance
        this.closestPlayer = this.level.getNearestPlayer(this.mob, this.maxDistance);

        // Ensure the closest player is in Survival or Adventure mode and not in Creative mode
        return this.closestPlayer != null && !this.closestPlayer.isCreative() && !this.closestPlayer.isSpectator()
                && this.mob.hasLineOfSight(this.closestPlayer);
    }

    @Override
    public boolean canContinueToUse() {
        // Continue following the player if they're in range, visible, and not in Creative mode
        return this.closestPlayer != null && !this.closestPlayer.isCreative() && !this.closestPlayer.isSpectator()
                && this.mob.distanceTo(this.closestPlayer) <= this.maxDistance
                && this.mob.hasLineOfSight(this.closestPlayer);
    }

    @Override
    public void start() {
        // Begin moving towards the player
        this.mob.getNavigation().moveTo(this.closestPlayer, this.speed);

    }

    @Override
    public void stop() {
        // Stop moving when the goal ends
        this.closestPlayer = null;
        this.mob.getNavigation().stop();
    }

    @Override
    public void tick() {
        // Continuously update the movement towards the player
        if (this.closestPlayer != null) {
            this.mob.getNavigation().moveTo(this.closestPlayer, this.speed);
        }
    }
}


