package net.purgeddd.testmod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {

    public static final FoodProperties RAINBOW_APPLE = new FoodProperties.Builder().nutrition(5).
            saturationMod(0.4f).effect(() -> new MobEffectInstance(MobEffects.HEALTH_BOOST, 200),
                    1.0f).effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 400, 0),
                    1.0f).effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1500, 0),
                    1.0f).effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 1500, 1),
                    1.0f).effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 1500, 0),
                    1.0F).alwaysEat().build();

    public static final FoodProperties GOLDEN_CHICKEN_BREAST = new FoodProperties.Builder().nutrition(5).
            saturationMod(0.4f).effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 200, 0),
                    1.0f).effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 750, 0),
                    1.0f).effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 750, 1),
                    1.0f).effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 750, 0),
                    1.0F).alwaysEat().build();



}



