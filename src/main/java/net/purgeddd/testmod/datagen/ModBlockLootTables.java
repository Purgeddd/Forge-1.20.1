package net.purgeddd.testmod.datagen;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;
import net.purgeddd.testmod.block.ModBlocks;
import net.purgeddd.testmod.item.ModItems;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }


    @Override
    protected void generate() {
        dropSelf(ModBlocks.FLAMING_FLOURITE_BLOCK.get());

        add(ModBlocks.FLAMING_FLOURITE_ORE.get(),
                (block) -> createOreDrop(ModBlocks.FLAMING_FLOURITE_ORE.get(), ModItems.FLAMING_FLOURITE.get()));

        this.dropSelf(ModBlocks.CYAN_LOG.get());
        this.dropSelf(ModBlocks.CYAN_PLANKS.get());
        this.dropSelf(ModBlocks.CYAN_WOOD.get());
        this.dropSelf(ModBlocks.STRIPPED_CYAN_WOOD.get());
        this.dropSelf(ModBlocks.STRIPPED_CYAN_LOG.get());
        this.dropSelf(ModBlocks.CYAN_SAPLING.get());


        this.add(ModBlocks.PINK_LEAVES.get(), (block) ->
                createLeavesDrops(block, ModBlocks.PINK_LEAVES.get(), NORMAL_LEAVES_SAPLING_CHANCES));

    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }






}
