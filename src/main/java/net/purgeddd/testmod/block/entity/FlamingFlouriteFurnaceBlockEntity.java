package net.purgeddd.testmod.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.purgeddd.testmod.recipe.FlamingFlouriteFurnaceRecipe;
import net.purgeddd.testmod.screen.FlamingFlouriteFurnaceMenu;

import java.util.Map;

public class FlamingFlouriteFurnaceBlockEntity extends AbstractFurnaceBlockEntity {

    private Map<Item, Integer> BURN_DURATION_MAP =
            Map.of(Items.BLAZE_POWDER, 800);

    public FlamingFlouriteFurnaceBlockEntity(BlockPos p_155545_, BlockState p_155546_) {
        super(ModBlockEntities.FLAMING_FLOURITE_FURNACE_BLOCK_ENTITY.get(), p_155545_, p_155546_, FlamingFlouriteFurnaceRecipe.Type.INSTANCE);
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("block.testmod.flaming_flourite_furnace");
    }

    @Override
    protected AbstractContainerMenu createMenu(int p_59293_, Inventory p_59294_) {
        return new FlamingFlouriteFurnaceMenu(p_59293_, p_59294_, this, dataAccess);
    }

    @Override
    protected int getBurnDuration(ItemStack pFuel) {
        return BURN_DURATION_MAP.getOrDefault(pFuel.getItem(), 0);
    }
}
