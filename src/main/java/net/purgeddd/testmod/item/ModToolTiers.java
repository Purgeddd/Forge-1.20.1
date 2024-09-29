package net.purgeddd.testmod.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;
import net.purgeddd.testmod.TestMod;
import net.purgeddd.testmod.util.ModTags;

import java.util.List;

public class ModToolTiers {
    public static final Tier FLAMING_FLOURITE = TierSortingRegistry.registerTier(
            new ForgeTier(5,3000,13f,4.0f, 25,
                    ModTags.Blocks.NEEDS_FLAMING_FLOURITE_TOOL, () -> Ingredient.of(ModItems.FLAMING_FLOURITE.get())),
            new ResourceLocation(TestMod.MOD_ID, "flaming_flourite"), List.of(Tiers.NETHERITE),
            List.of());

    public static final Tier CORRUPTED = TierSortingRegistry.registerTier(
            new ForgeTier(6,4000,15f,6.0f, 30,
                    ModTags.Blocks.NEEDS_CORRUPTED_TOOL, () -> Ingredient.of(ModItems.CORRUPTED_CORE.get())),
            new ResourceLocation(TestMod.MOD_ID, "corrupted"), List.of(Tiers.NETHERITE),
            List.of());



}
