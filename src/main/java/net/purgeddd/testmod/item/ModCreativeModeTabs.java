package net.purgeddd.testmod.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.purgeddd.testmod.TestMod;



public class ModCreativeModeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB,
            TestMod.MOD_ID);

    public static RegistryObject<CreativeModeTab> TEST_TAB = CREATIVE_MODE_TABS.register("test_tab", () ->
            CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.CORRUPTED_CORE.get()))
                    .title(Component.translatable
                            ("creativemodetab.test_tab")).displayItems((pParameters, pOutput) -> {

                        pOutput.accept(ModItems.FLAMING_FLOURITE_SWORD.get());
                        pOutput.accept(ModItems.FLAMING_FLOURITE_PICKAXE.get());
                        pOutput.accept(ModItems.FLAMING_FLOURITE_AXE.get());
                        pOutput.accept(ModItems.FLAMING_FLOURITE_SHOVEL.get());
                        pOutput.accept(ModItems.FLAMING_FLOURITE_HOE.get());






                    }).build());






    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);

    }


}
