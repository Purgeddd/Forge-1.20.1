package net.purgeddd.testmod.entity.client;

import net.minecraft.client.model.ChickenModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.purgeddd.testmod.TestMod;
import net.purgeddd.testmod.entity.custom.GoldenChickenEntity;
import net.purgeddd.testmod.entity.custom.ParasaurEntity;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

public class GoldenChickenModel extends GeoModel<GoldenChickenEntity>{


    @Override
    public ResourceLocation getModelResource(GoldenChickenEntity goldenChickenEntity) {
        return new ResourceLocation(TestMod.MOD_ID, "geo/golden_chicken.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(GoldenChickenEntity goldenChickenEntity) {
        return new ResourceLocation(TestMod.MOD_ID, "textures/entity/golden_chicken_texture.png");
    }

    @Override
    public ResourceLocation getAnimationResource(GoldenChickenEntity goldenChickenEntity) {
        return new ResourceLocation(TestMod.MOD_ID, "animations/golden_chicken.animation.json");
    }

    @Override
    public void setCustomAnimations(GoldenChickenEntity animatable, long instanceId, AnimationState<GoldenChickenEntity> animationState) {
        CoreGeoBone skull = getAnimationProcessor().getBone("skull");

        if (skull != null) {
            EntityModelData entityData = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            skull.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
            skull.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
        }
    }

}
