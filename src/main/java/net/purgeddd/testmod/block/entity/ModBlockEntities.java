package net.purgeddd.testmod.block.entity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.purgeddd.testmod.TestMod;
import net.purgeddd.testmod.block.ModBlocks;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, TestMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<FlamingFlouriteForgeBlockEntity>> FLAMING_FLOURITE_FORGE_BE =
            BLOCK_ENTITIES.register("flaming_flourite_forge", () -> BlockEntityType.Builder.of(
                    FlamingFlouriteForgeBlockEntity::new,
                    ModBlocks.FLAMING_FLOURITE_FORGE.get()
            ).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
