package net.purgeddd.testmod.datagen;

import net.purgeddd.testmod.TestMod;
import net.purgeddd.testmod.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, TestMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.FLAMING_FLOURITE_BLOCK);
        blockWithItem(ModBlocks.FLAMING_FLOURITE_ORE);

        logBlock(((RotatedPillarBlock) ModBlocks.CYAN_LOG.get()));
        axisBlock((RotatedPillarBlock) ModBlocks.CYAN_WOOD.get(), blockTexture(ModBlocks.CYAN_LOG.get()), blockTexture(ModBlocks.CYAN_LOG.get()));
        axisBlock((RotatedPillarBlock) ModBlocks.STRIPPED_CYAN_LOG.get(), new ResourceLocation(TestMod.MOD_ID, "block/stripped_cyan_log"),
                new ResourceLocation(TestMod.MOD_ID, "block/stripped_cyan_log_top"));
        axisBlock((RotatedPillarBlock) ModBlocks.STRIPPED_CYAN_WOOD.get(), new ResourceLocation(TestMod.MOD_ID, "block/stripped_cyan_log"),
                new ResourceLocation(TestMod.MOD_ID, "block/stripped_cyan_log"));

        blockWithItem(ModBlocks.CYAN_PLANKS);
        blockWithItem(ModBlocks.PINK_LEAVES);
        saplingBlock(ModBlocks.CYAN_SAPLING);

        simpleBlockItem(ModBlocks.CYAN_LOG.get(), models().withExistingParent("testmod:cyan_log", "minecraft:block/cube_column"));
        simpleBlockItem(ModBlocks.CYAN_WOOD.get(), models().withExistingParent("testmod:cyan_wood", "minecraft:block/cube_column"));
        simpleBlockItem(ModBlocks.STRIPPED_CYAN_LOG.get(), models().withExistingParent("testmod:stripped_cyan_log", "minecraft:block/cube_column"));
        simpleBlockItem(ModBlocks.STRIPPED_CYAN_WOOD.get(), models().withExistingParent("testmod:stripped_cyan_wood", "minecraft:block/cube_column"));
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    private void saplingBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cross(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }
}













