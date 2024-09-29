package net.purgeddd.testmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.purgeddd.testmod.TestMod;
import net.purgeddd.testmod.entity.custom.MozaicButterflyEntity;
import net.purgeddd.testmod.entity.custom.SeaSerpentEntity;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class SeaSerpentRenderer extends GeoEntityRenderer<SeaSerpentEntity> {

    public SeaSerpentRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new SeaSerpentModel());
        this.shadowRadius = 1.5f;
    }

    @Override
    public ResourceLocation getTextureLocation(SeaSerpentEntity animatable) {
        return new ResourceLocation(TestMod.MOD_ID, "textures/entity/sea_serpent_texture.png");
    }

    @Override
    public RenderType getRenderType(SeaSerpentEntity animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        return super.getRenderType(animatable, texture, bufferSource, partialTick);
    }

    @Override
    public void preRender(PoseStack poseStack, SeaSerpentEntity entity, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green,
                          float blue, float alpha) {
        float scale = 2f;
        this.scaleHeight = scale;
        this.scaleWidth = scale;
        super.preRender(poseStack, entity, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
    }


}