package net.purgeddd.testmod.event;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.ComputeFovModifierEvent;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.purgeddd.testmod.TestMod;
import net.purgeddd.testmod.block.ModBlocks;
import net.purgeddd.testmod.item.custom.FlamingFlouriteBowItem;

@Mod.EventBusSubscriber(modid = TestMod.MOD_ID)
public class ForgeEvents {

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

    @SubscribeEvent
    public static void onComputeFovModifierEvent(ComputeFovModifierEvent event) {
        if (event.getPlayer().isUsingItem() && event.getPlayer().getUseItem().getItem() instanceof FlamingFlouriteBowItem) {
            float fovModifier = 1f;
            int ticksUsingItem = event.getPlayer().getTicksUsingItem();
            float deltaTicks = (float) ticksUsingItem / 20.0F;
            if (deltaTicks > 1.0F) {
                deltaTicks = 1.0F;
            } else {
                deltaTicks *= deltaTicks;
            }
            fovModifier *= 1.0F - deltaTicks * 0.15F;
            event.setNewFovModifier(fovModifier);
        }
    }


}
