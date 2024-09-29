package net.purgeddd.testmod.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.purgeddd.testmod.TestMod;
import net.purgeddd.testmod.entity.custom.*;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, TestMod.MOD_ID);

    public static final RegistryObject<EntityType<ParasaurEntity>> PARASAUR =
            ENTITY_TYPES.register("parasaur",
                    () -> EntityType.Builder.of(ParasaurEntity::new, MobCategory.CREATURE)
                            .sized(3, 4)
                            .build(new ResourceLocation(TestMod.MOD_ID, "parasaur").toString()));

    public static final RegistryObject<EntityType<MozaicButterflyEntity>> MOZAIC_BUTTERFLY =
            ENTITY_TYPES.register("mozaic_butterfly",
                    () -> EntityType.Builder.of(MozaicButterflyEntity::new, MobCategory.CREATURE)
                            .sized(0.5f, 0.5f)
                            .build(new ResourceLocation(TestMod.MOD_ID, "mozaic_butterfly").toString()));

    public static final RegistryObject<EntityType<GoldenChickenEntity>> GOLDEN_CHICKEN =
            ENTITY_TYPES.register("golden_chicken",
                    () -> EntityType.Builder.of(GoldenChickenEntity::new, MobCategory.CREATURE)
                            .sized(0.75f, 0.75f)
                            .build(new ResourceLocation(TestMod.MOD_ID, "golden_chicken").toString()));

    public static final RegistryObject<EntityType<ObsidianGolemEntity>> OBSIDIAN_GOLEM =
            ENTITY_TYPES.register("obsidian_golem",
                    () -> EntityType.Builder.of(ObsidianGolemEntity::new, MobCategory.MONSTER)
                            .sized(1.5f, 2f)
                            .build(new ResourceLocation(TestMod.MOD_ID, "obsidian_golem").toString()));

    public static final RegistryObject<EntityType<SeaSerpentEntity>> SEA_SERPENT =
            ENTITY_TYPES.register("sea_serpent",
                    () -> EntityType.Builder.of(SeaSerpentEntity::new, MobCategory.MONSTER)
                            .sized(5f, 4f)
                            .build(new ResourceLocation(TestMod.MOD_ID, "sea_serpent").toString()));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
