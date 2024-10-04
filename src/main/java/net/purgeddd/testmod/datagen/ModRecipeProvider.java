package net.purgeddd.testmod.datagen;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.purgeddd.testmod.block.ModBlocks;
import net.purgeddd.testmod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraft.tags.BlockTags;
import net.purgeddd.testmod.util.ModTags;


import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output) {
        super(output);
    }


    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        // FF Sword
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.FLAMING_FLOURITE_SWORD.get())
                .pattern(" F ")
                .pattern(" F ")
                .pattern(" O ")
                .define('F', ModItems.FLAMING_FLOURITE.get())
                .define('O', ModItems.OBSIDIAN_ROD.get())
                .unlockedBy(getHasName(ModItems.FLAMING_FLOURITE.get()), has(ModItems.FLAMING_FLOURITE.get()))
                .unlockedBy(getHasName(ModItems.OBSIDIAN_ROD.get()), has(ModItems.OBSIDIAN_ROD.get()))
                .save(pWriter);

        // FF Bow
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.FLAMING_FLOURITE_BOW.get())
                .pattern(" FS")
                .pattern("F S")
                .pattern(" FS")
                .define('F', ModItems.FLAMING_FLOURITE.get())
                .define('S', Items.STRING.asItem())
                .unlockedBy(getHasName(ModItems.FLAMING_FLOURITE.get()), has(ModItems.FLAMING_FLOURITE.get()))
                .unlockedBy(getHasName(Items.STRING.asItem()), has(Items.STRING.asItem()))
                .save(pWriter);

        // FF Pickaxe
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.FLAMING_FLOURITE_PICKAXE.get())
                .pattern("FFF")
                .pattern(" O ")
                .pattern(" O ")
                .define('F', ModItems.FLAMING_FLOURITE.get())
                .define('O', ModItems.OBSIDIAN_ROD.get())
                .unlockedBy(getHasName(ModItems.FLAMING_FLOURITE.get()), has(ModItems.FLAMING_FLOURITE.get()))
                .unlockedBy(getHasName(ModItems.OBSIDIAN_ROD.get()), has(ModItems.OBSIDIAN_ROD.get()))
                .save(pWriter);

        // FF Axe
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.FLAMING_FLOURITE_AXE.get())
                .pattern("FF ")
                .pattern("FO ")
                .pattern(" O ")
                .define('F', ModItems.FLAMING_FLOURITE.get())
                .define('O', ModItems.OBSIDIAN_ROD.get())
                .unlockedBy(getHasName(ModItems.FLAMING_FLOURITE.get()), has(ModItems.FLAMING_FLOURITE.get()))
                .unlockedBy(getHasName(ModItems.OBSIDIAN_ROD.get()), has(ModItems.OBSIDIAN_ROD.get()))
                .save(pWriter);

        // FF Shovel
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.FLAMING_FLOURITE_SHOVEL.get())
                .pattern(" F ")
                .pattern(" O ")
                .pattern(" O ")
                .define('F', ModItems.FLAMING_FLOURITE.get())
                .define('O', ModItems.OBSIDIAN_ROD.get())
                .unlockedBy(getHasName(ModItems.FLAMING_FLOURITE.get()), has(ModItems.FLAMING_FLOURITE.get()))
                .unlockedBy(getHasName(ModItems.OBSIDIAN_ROD.get()), has(ModItems.OBSIDIAN_ROD.get()))
                .save(pWriter);

        // FF Hoe
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.FLAMING_FLOURITE_HOE.get())
                .pattern("FF ")
                .pattern(" O ")
                .pattern(" O ")
                .define('F', ModItems.FLAMING_FLOURITE.get())
                .define('O', ModItems.OBSIDIAN_ROD.get())
                .unlockedBy(getHasName(ModItems.FLAMING_FLOURITE.get()), has(ModItems.FLAMING_FLOURITE.get()))
                .unlockedBy(getHasName(ModItems.OBSIDIAN_ROD.get()), has(ModItems.OBSIDIAN_ROD.get()))
                .save(pWriter);

        // FF Helmet
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.FLAMING_FLOURITE_HELMET.get())
                .pattern("FFF")
                .pattern("F F")
                .pattern("   ")
                .define('F', ModItems.FLAMING_FLOURITE.get())
                .unlockedBy(getHasName(ModItems.FLAMING_FLOURITE.get()), has(ModItems.FLAMING_FLOURITE.get()))
                .save(pWriter);

        // FF Chestplate
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.FLAMING_FLOURITE_CHESTPLATE.get())
                .pattern("F F")
                .pattern("FFF")
                .pattern("FFF")
                .define('F', ModItems.FLAMING_FLOURITE.get())
                .unlockedBy(getHasName(ModItems.FLAMING_FLOURITE.get()), has(ModItems.FLAMING_FLOURITE.get()))
                .save(pWriter);

        // FF Leggings
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.FLAMING_FLOURITE_LEGGINGS.get())
                .pattern("FFF")
                .pattern("F F")
                .pattern("F F")
                .define('F', ModItems.FLAMING_FLOURITE.get())
                .unlockedBy(getHasName(ModItems.FLAMING_FLOURITE.get()), has(ModItems.FLAMING_FLOURITE.get()))
                .save(pWriter);

        // FF Boots
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.FLAMING_FLOURITE_BOOTS.get())
                .pattern("F F")
                .pattern("F F")
                .define('F', ModItems.FLAMING_FLOURITE.get())
                .unlockedBy(getHasName(ModItems.FLAMING_FLOURITE.get()), has(ModItems.FLAMING_FLOURITE.get()))
                .save(pWriter);

        // Corrupted Helmet
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.CORRUPTED_HELMET.get())
                .pattern("CCC")
                .pattern("C C")
                .pattern("   ")
                .define('C', ModItems.CORRUPTED_CORE.get())
                .unlockedBy(getHasName(ModItems.CORRUPTED_CORE.get()), has(ModItems.CORRUPTED_CORE.get()))
                .save(pWriter);

        // Corrupted Chestplate
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.CORRUPTED_CHESTPLATE.get())
                .pattern("C C")
                .pattern("CCC")
                .pattern("CCC")
                .define('C', ModItems.CORRUPTED_CORE.get())
                .unlockedBy(getHasName(ModItems.CORRUPTED_CORE.get()), has(ModItems.CORRUPTED_CORE.get()))
                .save(pWriter);

        // Corrupted Leggings
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.CORRUPTED_LEGGINGS.get())
                .pattern("CCC")
                .pattern("C C")
                .pattern("C C")
                .define('C', ModItems.CORRUPTED_CORE.get())
                .unlockedBy(getHasName(ModItems.CORRUPTED_CORE.get()), has(ModItems.CORRUPTED_CORE.get()))
                .save(pWriter);

        // Corrupted Boots
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.CORRUPTED_BOOTS.get())
                .pattern("C C")
                .pattern("C C")
                .define('C', ModItems.CORRUPTED_CORE.get())
                .unlockedBy(getHasName(ModItems.CORRUPTED_CORE.get()), has(ModItems.CORRUPTED_CORE.get()))
                .save(pWriter);

        // FF Block
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FLAMING_FLOURITE_BLOCK.get())
                .pattern("FFF")
                .pattern("FFF")
                .pattern("FFF")
                .define('F', ModItems.FLAMING_FLOURITE.get())
                .unlockedBy(getHasName(ModItems.FLAMING_FLOURITE.get()), has(ModItems.FLAMING_FLOURITE.get()))
                .save(pWriter);

        // Crafting Table
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, Blocks.CRAFTING_TABLE.asItem())
                .pattern("PP")
                .pattern("PP")
                .define('P', ModTags.Items.PLANKS)
                .unlockedBy(getHasName(ModBlocks.CYAN_PLANKS.get()), has(ModBlocks.CYAN_PLANKS.get()))
                .save(pWriter);

        // Stick
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Items.STICK.asItem())
                .pattern("P")
                .pattern("P")
                .define('P', ModTags.Items.PLANKS)
                .unlockedBy(getHasName(ModBlocks.CYAN_PLANKS.get()), has(ModBlocks.CYAN_PLANKS.get()))
                .save(pWriter);

        // Obsidian Rod
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.OBSIDIAN_ROD.get())
                .pattern("#  ")
                .pattern("#  ")
                .define('#', Items.OBSIDIAN.asItem())
                .unlockedBy(getHasName(Items.OBSIDIAN.asItem()), has(Items.OBSIDIAN.asItem()))
                .save(pWriter);

        // Unholy Saviour Sword
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.UNHOLY_SAVIOUR_SWORD.get())
                .pattern("  B")
                .pattern(" C ")
                .pattern("H  ")
                .define( 'H', ModItems.UNHOLY_SAVIOUR_SWORD_HANDLE.get())
                .define( 'C', ModItems.UNHOLY_SAVIOUR_SWORD_CROSSGUARD.get())
                .define( 'B', ModItems.UNHOLY_SAVIOUR_SWORD_BLADE.get())
                .unlockedBy(getHasName(ModItems.UNHOLY_SAVIOUR_SWORD_BLADE.get()), has(ModItems.UNHOLY_SAVIOUR_SWORD_BLADE.get()))
                .unlockedBy(getHasName(ModItems.UNHOLY_SAVIOUR_SWORD_CROSSGUARD.get()), has(ModItems.UNHOLY_SAVIOUR_SWORD_CROSSGUARD.get()))
                .unlockedBy(getHasName(ModItems.UNHOLY_SAVIOUR_SWORD_HANDLE.get()), has(ModItems.UNHOLY_SAVIOUR_SWORD_HANDLE.get()))
                .save(pWriter);

        // Rainbow Apple
        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, ModItems.RAINBOW_APPLE.get())
                .pattern("SPG")
                .pattern("EAD")
                .pattern("LMC")
                .define('S', Items.REDSTONE.asItem())
                .define('P', Items.COPPER_INGOT.asItem())
                .define('G', Items.GOLD_INGOT.asItem())
                .define('E', Items.EMERALD.asItem())
                .define('A', Items.APPLE.asItem())
                .define('D', Items.DIAMOND.asItem())
                .define('L', Items.LAPIS_LAZULI.asItem())
                .define('M', Items.AMETHYST_SHARD.asItem())
                .define('C', Items.COAL.asItem())
                .unlockedBy(getHasName(Items.DIAMOND.asItem()), has(Items.DIAMOND.asItem()))
                .unlockedBy(getHasName(Items.REDSTONE.asItem()), has(Items.REDSTONE.asItem()))
                .unlockedBy(getHasName(Items.COPPER_INGOT.asItem()), has(Items.COPPER_INGOT.asItem()))
                .unlockedBy(getHasName(Items.GOLD_INGOT.asItem()), has(Items.GOLD_INGOT.asItem()))
                .unlockedBy(getHasName(Items.EMERALD.asItem()), has(Items.EMERALD.asItem()))
                .unlockedBy(getHasName(Items.APPLE.asItem()), has(Items.APPLE.asItem()))
                .unlockedBy(getHasName(Items.LAPIS_LAZULI.asItem()), has(Items.LAPIS_LAZULI.asItem()))
                .unlockedBy(getHasName(Items.AMETHYST_SHARD.asItem()), has(Items.AMETHYST_SHARD.asItem()))
                .unlockedBy(getHasName(Items.COAL.asItem()), has(Items.COAL.asItem())).save(pWriter);


        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.FLAMING_FLOURITE.get(), 9)
                .requires(ModBlocks.FLAMING_FLOURITE_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.FLAMING_FLOURITE_BLOCK.get()), has(ModBlocks.FLAMING_FLOURITE_BLOCK.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, ModBlocks.CYAN_PLANKS.get(), 4)
                .requires(ModTags.Items.CYAN_LOGS) // Use the cyan_logs tag
                .group("cyan_planks")
                .unlockedBy("has_cyan_logs", has(ModTags.Items.CYAN_LOGS)) // Unlock condition based on cyan_logs tag
                .save(pWriter);



    }


}