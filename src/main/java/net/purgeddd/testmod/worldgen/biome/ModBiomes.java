package net.purgeddd.testmod.worldgen.biome;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.purgeddd.testmod.TestMod;

import java.util.function.Supplier;



public class ModBiomes
{
    public static final ResourceKey<Biome> CYAN_FOREST = register("cyan_forest_biome");
    public static final ResourceKey<Biome> THE_ABYSS = register("the_abyss");

    private static ResourceKey<Biome> register(String name)
    {
        return ResourceKey.create(Registries.BIOME, new ResourceLocation(TestMod.MOD_ID, name));
    }
}

