package net.purgeddd.testmod.entity.client;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.purgeddd.testmod.TestMod;
import net.purgeddd.testmod.entity.custom.ObsidianGolemEntity;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class ObsidianGolemRenderer extends GeoEntityRenderer<ObsidianGolemEntity> {
    public ObsidianGolemRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new ObsidianGolemModel());
        this.shadowRadius = 0.75f;
    }

    @Override
    public ResourceLocation getTextureLocation(ObsidianGolemEntity animatable) {
        return new ResourceLocation(TestMod.MOD_ID, "textures/entity/obsidian_golem_texture.png");
    }

    @Override
    public RenderType getRenderType(ObsidianGolemEntity animatable, ResourceLocation texture, @Nullable MultiBufferSource bufferSource, float partialTick) {
        return super.getRenderType(animatable, texture, bufferSource, partialTick);
    }
}