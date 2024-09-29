package net.purgeddd.testmod.util;

import net.purgeddd.testmod.TestMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.core.Registry;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> NEEDS_FLAMING_FLOURITE_TOOL = tag("needs_flaming_flourite_tool");

        public static final TagKey<Block> NEEDS_CORRUPTED_TOOL = tag("needs_flaming_flourite_tool");


        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(TestMod.MOD_ID, name));
        }
    }

    public static class Items {

        public static final TagKey<Item> PLANKS = tag("planks");

        public static final TagKey<Item> CYAN_LOGS = tag("cyan_logs");
    }

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(TestMod.MOD_ID, name));
        }
    }
