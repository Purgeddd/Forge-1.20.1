package net.purgeddd.testmod.datagen;


import net.minecraft.data.loot.EntityLootSubProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraftforge.registries.RegistryObject;
import net.purgeddd.testmod.entity.ModEntities;
import net.purgeddd.testmod.item.ModItems;

import java.util.stream.Stream;

public class ModEntityLootTables extends EntityLootSubProvider {
    protected ModEntityLootTables() {
        super(FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    public void generate() {
        // Define loot tables for custom entities
        add(ModEntities.GOLDEN_CHICKEN.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.GOLDEN_CHICKEN_BREAST.get())

                        )
                )
        );

        add(ModEntities.PARASAUR.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.FLAMING_FLOURITE.get())

                        )
                )
        );

        add(ModEntities.MOZAIC_BUTTERFLY.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.FLAMING_FLOURITE.get())

                        )
                )
        );

        add(ModEntities.OBSIDIAN_GOLEM.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(Items.OBSIDIAN.asItem())

                        )
                )
        );

        add(ModEntities.SEA_SERPENT.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .add(LootItem.lootTableItem(ModItems.SEA_SERPENT_TOOTH.get())

                        )
                )
        );

    }

    @Override
    protected Stream<EntityType<?>> getKnownEntityTypes() {
        return ModEntities.ENTITY_TYPES.getEntries().stream().map(RegistryObject::get);
    }
}






