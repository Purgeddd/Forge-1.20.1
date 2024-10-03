package net.purgeddd.testmod.event;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.purgeddd.testmod.TestMod;
import net.purgeddd.testmod.block.ModBlocks;

@Mod.EventBusSubscriber(modid = TestMod.MOD_ID)
public class FuelRegistry {

    @SubscribeEvent
    public static void onFurnaceFuelBurnTime(FurnaceFuelBurnTimeEvent event) {
        ItemStack fuel = event.getItemStack();

        if (fuel.getItem() == ModBlocks.CYAN_PLANKS.get().asItem()) {
            event.setBurnTime(300); // 300 ticks
        }

        if (fuel.getItem() == ModBlocks.CYAN_LOG.get().asItem()) {
            event.setBurnTime(300); // 300 ticks
        }

        if (fuel.getItem() == ModBlocks.CYAN_WOOD.get().asItem()) {
            event.setBurnTime(300); // 300 ticks
        }

        if (fuel.getItem() == ModBlocks.STRIPPED_CYAN_LOG.get().asItem()) {
            event.setBurnTime(300); // 300 ticks
        }

        if (fuel.getItem() == ModBlocks.STRIPPED_CYAN_WOOD.get().asItem()) {
            event.setBurnTime(300); // 300 ticks
        }
    }


}
