package net.purgeddd.testmod.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.purgeddd.testmod.TestMod;
import net.purgeddd.testmod.entity.custom.ObsidianGolemEntity;
import net.purgeddd.testmod.entity.custom.ParasaurEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class ObsidianGolemModel extends GeoModel<ObsidianGolemEntity> {
    @Override
    public ResourceLocation getModelResource(ObsidianGolemEntity obsidianGolemEntity) {
        return new ResourceLocation(TestMod.MOD_ID, "geo/obsidian_golem.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ObsidianGolemEntity obsidianGolemEntity) {
        return new ResourceLocation(TestMod.MOD_ID, "textures/entity/obsidian_golem_texture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ObsidianGolemEntity obsidianGolemEntity) {
        return new ResourceLocation(TestMod.MOD_ID, "animations/obsidian_golem.animation.json");
    }

    @Override
    public void setCustomAnimations(ObsidianGolemEntity animatable, long instanceId, AnimationState<ObsidianGolemEntity> animationState) {
        CoreGeoBone headrotate = getAnimationProcessor().getBone("headrotate");

        if (headrotate != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            headrotate.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            headrotate.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }
}