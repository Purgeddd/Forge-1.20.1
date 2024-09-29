package net.purgeddd.testmod.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.purgeddd.testmod.TestMod;
import net.purgeddd.testmod.entity.custom.MozaicButterflyEntity;
import software.bernie.geckolib.model.GeoModel;


public class MozaicButterflyModel extends GeoModel<MozaicButterflyEntity> {
    @Override
    public ResourceLocation getModelResource(MozaicButterflyEntity MozaicButterflyEntity) {
        return new ResourceLocation(TestMod.MOD_ID, "geo/mozaic_butterfly.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(MozaicButterflyEntity MozaicButterflyEntity) {
        return new ResourceLocation(TestMod.MOD_ID, "textures/entity/mozaic_butterfly_texture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(MozaicButterflyEntity MozaicButterflyEntity) {
        return new ResourceLocation(TestMod.MOD_ID, "animations/mozaic_butterfly.animation.json");
    }
}