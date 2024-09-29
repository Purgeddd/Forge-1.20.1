package net.purgeddd.testmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.purgeddd.testmod.TestMod;
import net.purgeddd.testmod.entity.custom.ParasaurEntity;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class ParasaurRenderer extends GeoEntityRenderer<ParasaurEntity> {
    public ParasaurRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ParasaurModel());
        this.shadowRadius = 0.8f;
    }


    @Override
    public ResourceLocation getTextureLocation(ParasaurEntity animatable) {
        return new ResourceLocation(TestMod.MOD_ID, "textures/entity/parasaur_texture.png");
    }

    @Override
    public void render(ParasaurEntity entity, float entityYaw, float partialTick, PoseStack poseStack,
                       MultiBufferSource bufferSource, int packedLight) {
        if(entity.isBaby()) {
            poseStack.scale(0.4f,0.4f,0.4f);
        }

        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight);
    }

    @Override
    public RenderType getRenderType(ParasaurEntity animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        return super.getRenderType(animatable, texture, bufferSource, partialTick);
    }
}
