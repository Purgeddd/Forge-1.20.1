package net.purgeddd.testmod.fluid;

import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.purgeddd.testmod.TestMod;
import net.purgeddd.testmod.block.ModBlocks;
import net.purgeddd.testmod.item.ModItems;

public class ModFluids {
    public static final DeferredRegister<Fluid> FLUIDS =
            DeferredRegister.create(ForgeRegistries.FLUIDS, TestMod.MOD_ID);

    public static final RegistryObject<FlowingFluid> SOURCE_GASOLINE = FLUIDS.register("gasoline_fluid",
            () -> new ForgeFlowingFluid.Source(ModFluids.GASOLINE_FLUID_PROPERTIES));

    public static final RegistryObject<FlowingFluid> FLOWING_GASOLINE = FLUIDS.register("flowing_gasoline_fluid",
            () -> new ForgeFlowingFluid.Flowing(ModFluids.GASOLINE_FLUID_PROPERTIES));

    public static final ForgeFlowingFluid.Properties GASOLINE_FLUID_PROPERTIES = new ForgeFlowingFluid.Properties(
            ModFluidTypes.GASOLINE_FLUID_TYPE, SOURCE_GASOLINE, FLOWING_GASOLINE)
            .slopeFindDistance(6).levelDecreasePerBlock(2).block(ModBlocks.GASOLINE_BLOCK)
            .bucket(ModItems.GASOLINE_BUCKET);

    public static void register(IEventBus eventBus) {
        FLUIDS.register(eventBus);
    }
}