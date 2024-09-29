package net.purgeddd.testmod.entity.client;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.purgeddd.testmod.TestMod;
import net.purgeddd.testmod.entity.custom.SeaSerpentEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class SeaSerpentModel extends GeoModel<SeaSerpentEntity> {
    @Override
    public ResourceLocation getModelResource(SeaSerpentEntity seaSerpentEntity) {
        return new ResourceLocation(TestMod.MOD_ID, "geo/sea_serpent.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(SeaSerpentEntity seaSerpentEntity) {
        return new ResourceLocation(TestMod.MOD_ID, "textures/entity/sea_serpent_texture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(SeaSerpentEntity seaSerpentEntity) {
        return new ResourceLocation(TestMod.MOD_ID, "animations/sea_serpent.animation.json");
    }

    @Override
    public void setCustomAnimations(SeaSerpentEntity animatable, long instanceId, AnimationState<SeaSerpentEntity> animationState) {
        CoreGeoBone headrotate = getAnimationProcessor().getBone("headrotate");

        if (headrotate != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            headrotate.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            headrotate.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }
}