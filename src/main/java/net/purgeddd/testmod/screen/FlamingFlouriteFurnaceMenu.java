package net.purgeddd.testmod.screen;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractFurnaceMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.purgeddd.testmod.recipe.FlamingFlouriteFurnaceRecipe;
import net.purgeddd.testmod.recipe.ModRecipes;

public class FlamingFlouriteFurnaceMenu extends AbstractFurnaceMenu {

    protected FlamingFlouriteFurnaceMenu(int pContainerId, Inventory pPlayerInventory, FriendlyByteBuf friendlyByteBuf) {
        this(pContainerId, pPlayerInventory);
    }

    public FlamingFlouriteFurnaceMenu(int p_39535_, Inventory p_39536_, Container container, ContainerData containerData) {
        super(ModMenuTypes.FLAMING_FLOURITE_FURNACE_MENU.get(), FlamingFlouriteFurnaceRecipe.Type.INSTANCE, RecipeBookType.FURNACE, p_39535_, p_39536_, container, containerData);
    }

    public FlamingFlouriteFurnaceMenu(int pContainerId, Inventory pPlayerInventory) {
        super(ModMenuTypes.FLAMING_FLOURITE_FURNACE_MENU.get(), FlamingFlouriteFurnaceRecipe.Type.INSTANCE, RecipeBookType.FURNACE, pContainerId, pPlayerInventory);
    }

    @Override
    protected boolean isFuel(ItemStack p_38989_) {
        return true;
    }
}

