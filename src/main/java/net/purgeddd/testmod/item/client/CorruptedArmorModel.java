package net.purgeddd.testmod.item.client;

import net.minecraft.resources.ResourceLocation;
import net.purgeddd.testmod.TestMod;
import net.purgeddd.testmod.item.custom.CorruptedArmorItem;
import net.purgeddd.testmod.item.custom.UnholySaviourSwordItem;
import software.bernie.geckolib.model.GeoModel;

public class CorruptedArmorModel extends GeoModel<CorruptedArmorItem> {
    @Override
    public ResourceLocation getModelResource(CorruptedArmorItem corruptedArmorItem) {
        return new ResourceLocation(TestMod.MOD_ID, "geo/corrupted_armor.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(CorruptedArmorItem corruptedArmorItem) {
        return new ResourceLocation(TestMod.MOD_ID, "textures/models/armor/corrupted_armor_texture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(CorruptedArmorItem corruptedArmorItem) {
        return new ResourceLocation(TestMod.MOD_ID, "animations/corrupted_armor.animation.json");
    }
}
