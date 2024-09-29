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

    public static final RegistryObject<BlockEntityType<FlamingFlouriteFurnaceBlockEntity>> FLAMING_FLOURITE_FURNACE_BLOCK_ENTITY =
            BLOCK_ENTITIES.register("flaming_flourite_furnace_block_entity", () ->
                    BlockEntityType.Builder.of(FlamingFlouriteFurnaceBlockEntity::new,
                            ModBlocks.FLAMING_FLOURITE_FURNACE_BLOCK.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
