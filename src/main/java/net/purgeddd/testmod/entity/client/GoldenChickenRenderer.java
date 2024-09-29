package net.purgeddd.testmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.purgeddd.testmod.TestMod;
import net.purgeddd.testmod.entity.custom.GoldenChickenEntity;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class GoldenChickenRenderer extends GeoEntityRenderer<GoldenChickenEntity> {
    public GoldenChickenRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new GoldenChickenModel());
    }

    @Override
    public ResourceLocation getTextureLocation(GoldenChickenEntity animatable) {
        return new ResourceLocation(TestMod.MOD_ID, "textures/entity/golden_chicken_texture.png");
    }

    @Override
    public void render(GoldenChickenEntity entity, float entityYaw, float partialTick, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {

        if(entity.isBaby()) {
            poseStack.scale(0.4f,0.4f,0.4f);
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }
}
