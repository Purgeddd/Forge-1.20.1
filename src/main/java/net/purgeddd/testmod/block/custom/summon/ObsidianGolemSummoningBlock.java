package net.purgeddd.testmod.block.custom.summon;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.purgeddd.testmod.entity.ModEntities;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class ObsidianGolemSummoningBlock {
    @SubscribeEvent
    public static void onBlockPlace(BlockEvent.EntityPlaceEvent event) {
        execute(event, event.getLevel(), event.getPos().getX(), event.getPos().getY(), event.getPos().getZ());
    }

    public static void execute(LevelAccessor world, double x, double y, double z) {
        execute(null, world, x, y, z);
    }

    private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z) {
        if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.CARVED_PUMPKIN && (world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == Blocks.OBSIDIAN
                && (world.getBlockState(BlockPos.containing(x, y - 2, z))).getBlock() == Blocks.OBSIDIAN
                && ((world.getBlockState(BlockPos.containing(x + 1, y - 1, z))).getBlock() == Blocks.OBSIDIAN && (world.getBlockState(BlockPos.containing(x - 1, y - 1, z))).getBlock() == Blocks.OBSIDIAN
                || (world.getBlockState(BlockPos.containing(x, y - 1, z + 1))).getBlock() == Blocks.OBSIDIAN && (world.getBlockState(BlockPos.containing(x, y - 1, z - 1))).getBlock() == Blocks.OBSIDIAN)) {
            world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
            world.setBlock(BlockPos.containing(x, y - 1, z), Blocks.AIR.defaultBlockState(), 3);
            world.setBlock(BlockPos.containing(x, y - 2, z), Blocks.AIR.defaultBlockState(), 3);
            world.setBlock(BlockPos.containing(x + 1, y - 1, z), Blocks.AIR.defaultBlockState(), 3);
            world.setBlock(BlockPos.containing(x - 1, y - 1, z), Blocks.AIR.defaultBlockState(), 3);
            world.setBlock(BlockPos.containing(x, y - 1, z + 1), Blocks.AIR.defaultBlockState(), 3);
            world.setBlock(BlockPos.containing(x, y - 1, z - 1), Blocks.AIR.defaultBlockState(), 3);
            if (world instanceof ServerLevel _level) {
                Entity entityToSpawn = ModEntities.OBSIDIAN_GOLEM.get().spawn(_level, BlockPos.containing(x, y - 2, z), MobSpawnType.MOB_SUMMONED);
                if (entityToSpawn != null) {
                    entityToSpawn.setYRot(world.getRandom().nextFloat() * 360F);
                }
            }
        }
    }
}


