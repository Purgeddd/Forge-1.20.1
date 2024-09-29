package net.purgeddd.testmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.purgeddd.testmod.TestMod;
import net.purgeddd.testmod.entity.custom.MozaicButterflyEntity;
import net.purgeddd.testmod.entity.custom.ParasaurEntity;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class MozaicButterflyRenderer extends GeoEntityRenderer<MozaicButterflyEntity> {
    public MozaicButterflyRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new MozaicButterflyModel());
    }

    @Override
    public ResourceLocation getTextureLocation(MozaicButterflyEntity animatable) {
        return new ResourceLocation(TestMod.MOD_ID, "textures/entity/mozaic_butterfly_texture.png");
    }

    @Override
    public void render(MozaicButterflyEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {
        if(entity.isBaby()) {
            poseStack.scale(0.4f,0.4f,0.4f);
        }


        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

    @Override
    public void preRender(PoseStack poseStack, MozaicButterflyEntity entity, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green,
                          float blue, float alpha) {
        float scale = 2f; // Size of Butterfly
        this.scaleHeight = scale;
        this.scaleWidth = scale;
        super.preRender(poseStack, entity, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }


}