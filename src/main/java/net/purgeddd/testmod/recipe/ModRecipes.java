package net.purgeddd.testmod.recipe;

import net.purgeddd.testmod.TestMod;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, TestMod.MOD_ID);

    public static final RegistryObject<RecipeSerializer<FlamingFlouriteFurnaceRecipe>> FLAMING_FLOURITE_FURNACE_SERIALIZER =
            SERIALIZERS.register("flaming_flourite_recipe", () -> FlamingFlouriteFurnaceRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}