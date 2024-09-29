package net.purgeddd.testmod.event;


import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.GlowSquid;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;

import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.purgeddd.testmod.TestMod;
import net.purgeddd.testmod.entity.ModEntities;
import net.purgeddd.testmod.entity.client.GoldenChickenRenderer;
import net.purgeddd.testmod.entity.custom.*;


@Mod.EventBusSubscriber(modid = TestMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEvents {

    @SubscribeEvent
    public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
        event.put(ModEntities.PARASAUR.get(), ParasaurEntity.setAttributes());
        event.put(ModEntities.MOZAIC_BUTTERFLY.get(), MozaicButterflyEntity.setAttributes());
        event.put(ModEntities.GOLDEN_CHICKEN.get(), GoldenChickenEntity.setAttributes());
        event.put(ModEntities.OBSIDIAN_GOLEM.get(), ObsidianGolemEntity.setAttributes());
        event.put(ModEntities.SEA_SERPENT.get(), SeaSerpentEntity.setAttributes());


    }


    @SubscribeEvent
    public static void entitySpawnRestriction(SpawnPlacementRegisterEvent event) {
        event.register(ModEntities.PARASAUR.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(ModEntities.MOZAIC_BUTTERFLY.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(ModEntities.GOLDEN_CHICKEN.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Animal::checkAnimalSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(ModEntities.OBSIDIAN_GOLEM.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                Monster::checkMonsterSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
        event.register(ModEntities.SEA_SERPENT.get(), SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                SeaSerpentEntity::checkSeaSerpentSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
    }


}

