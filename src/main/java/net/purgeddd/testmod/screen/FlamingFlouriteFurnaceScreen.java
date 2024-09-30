package net.purgeddd.testmod.screen;

import net.minecraft.client.gui.screens.inventory.AbstractFurnaceScreen;
import net.minecraft.client.gui.screens.recipebook.SmeltingRecipeBookComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.purgeddd.testmod.TestMod;
import net.purgeddd.testmod.recipe.FlamingFlouriteRecipeBookComponent;

public class FlamingFlouriteFurnaceScreen extends AbstractFurnaceScreen<FlamingFlouriteFurnaceMenu> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(TestMod.MOD_ID, "textures/gui/flaming_flourite_furnace.png");

    public FlamingFlouriteFurnaceScreen(FlamingFlouriteFurnaceMenu furnaceMenu, Inventory inventory, Component component) {
        super(furnaceMenu, new FlamingFlouriteRecipeBookComponent(), inventory, component, TEXTURE);
    }
}

