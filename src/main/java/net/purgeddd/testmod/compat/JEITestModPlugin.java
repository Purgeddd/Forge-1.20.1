package net.purgeddd.testmod.compat;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import net.minecraft.resources.ResourceLocation;
import net.purgeddd.testmod.TestMod;



@JeiPlugin
public class JEITestModPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(TestMod.MOD_ID, "jei_plugin");
    }
}
