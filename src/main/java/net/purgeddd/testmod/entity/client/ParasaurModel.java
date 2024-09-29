package net.purgeddd.testmod.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.purgeddd.testmod.TestMod;
import net.purgeddd.testmod.entity.custom.ParasaurEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class ParasaurModel extends GeoModel<ParasaurEntity> {
    @Override
    public ResourceLocation getModelResource(ParasaurEntity parasaurEntity) {
        return new ResourceLocation(TestMod.MOD_ID, "geo/parasaur.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(ParasaurEntity parasaurEntity) {
        return new ResourceLocation(TestMod.MOD_ID, "textures/entity/parasaur_texture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(ParasaurEntity parasaurEntity) {
        return new ResourceLocation(TestMod.MOD_ID, "animations/parasaur.animation.json");
    }

    @Override
    public void setCustomAnimations(ParasaurEntity animatable, long instanceId, AnimationState<ParasaurEntity> animationState) {
        CoreGeoBone headrotate = getAnimationProcessor().getBone("headrotate");

        if (headrotate != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            headrotate.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            headrotate.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }
}
