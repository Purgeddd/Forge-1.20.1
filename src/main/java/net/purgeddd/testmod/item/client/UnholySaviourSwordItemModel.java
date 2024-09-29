package net.purgeddd.testmod.item.client;

import net.minecraft.resources.ResourceLocation;
import net.purgeddd.testmod.TestMod;
import net.purgeddd.testmod.item.custom.UnholySaviourSwordItem;
import software.bernie.geckolib.model.GeoModel;

public class UnholySaviourSwordItemModel extends GeoModel<UnholySaviourSwordItem> {
    @Override
    public ResourceLocation getModelResource(UnholySaviourSwordItem unholySaviourSwordItem) {
        return new ResourceLocation(TestMod.MOD_ID, "geo/unholy_saviour_sword.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(UnholySaviourSwordItem unholySaviourSwordItem) {
        return new ResourceLocation(TestMod.MOD_ID, "textures/item/unholy_saviour_sword_model.png");
    }

    @Override
    public ResourceLocation getAnimationResource(UnholySaviourSwordItem unholySaviourSwordItem) {
        return new ResourceLocation(TestMod.MOD_ID, "animations/unholy_saviour_sword.animation.json");
    }
}
