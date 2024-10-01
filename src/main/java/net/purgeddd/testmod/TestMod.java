package net.purgeddd.testmod;

import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.purgeddd.testmod.block.entity.ModBlockEntities;
import net.purgeddd.testmod.block.ModBlocks;
import net.purgeddd.testmod.entity.ModEntities;
import net.purgeddd.testmod.entity.client.*;
import net.purgeddd.testmod.fluid.ModFluidTypes;
import net.purgeddd.testmod.fluid.ModFluids;
import net.purgeddd.testmod.item.ModCreativeModeTabs;
import net.purgeddd.testmod.item.ModItems;
import net.purgeddd.testmod.recipe.ModRecipes;
import net.purgeddd.testmod.screen.ModMenuTypes;
import net.purgeddd.testmod.worldgen.biome.CyanForestBiome;
import net.purgeddd.testmod.worldgen.biome.TheAbyssBiome;
import net.purgeddd.testmod.worldgen.biome.surface.ModSurfaceRules;
import org.slf4j.Logger;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(TestMod.MOD_ID)
public class TestMod
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "testmod";
    public static final Logger LOGGER = LogUtils.getLogger();




    public TestMod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModCreativeModeTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModEntities.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        ModRecipes.register(modEventBus);
        ModFluidTypes.register(modEventBus);
        ModFluids.register(modEventBus);



        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);


        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(() ->
        {
            // Weights are kept intentionally low as we add minimal biomes
            Regions.register(new CyanForestBiome(new ResourceLocation(MOD_ID, "overworld_1"), 2));
            Regions.register(new TheAbyssBiome(new ResourceLocation(MOD_ID, "overworld_2"), 1));

            // Register our surface rules
            SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MOD_ID, ModSurfaceRules.makeRules());
            
        });
    }




    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(ModItems.FLAMING_FLOURITE);
            event.accept(ModItems.OBSIDIAN_ROD);
            event.accept(ModItems.FORSAKEN_FEATHER);
            event.accept(ModItems.CORRUPTED_CORE);
            event.accept(ModItems.SEA_SERPENT_TOOTH);




        }
        if (event.getTab() == ModCreativeModeTabs.TEST_TAB.get()) {
            event.accept(ModItems.FLAMING_FLOURITE);
            event.accept(ModItems.FLAMING_FLOURITE_SWORD);
            event.accept(ModItems.FLAMING_FLOURITE_PICKAXE);
            event.accept(ModItems.FLAMING_FLOURITE_AXE);
            event.accept(ModItems.FLAMING_FLOURITE_SHOVEL);
            event.accept(ModItems.FLAMING_FLOURITE_HOE);
            event.accept(ModBlocks.FLAMING_FLOURITE_BLOCK);
            event.accept(ModBlocks.FLAMING_FLOURITE_ORE);
            event.accept(ModBlocks.CYAN_LOG);
            event.accept(ModBlocks.CYAN_WOOD);
            event.accept(ModBlocks.CYAN_PLANKS);
            event.accept(ModBlocks.CYAN_SAPLING);
            event.accept(ModBlocks.STRIPPED_CYAN_WOOD);
            event.accept(ModBlocks.STRIPPED_CYAN_LOG);
            event.accept(ModBlocks.PINK_LEAVES);
            event.accept(ModItems.PARASAUR_SPAWN_EGG);
            event.accept(ModItems.MOZAIC_BUTTERFLY_SPAWN_EGG);
            event.accept(ModItems.GOLDEN_CHICKEN_SPAWN_EGG);
            event.accept(ModItems.OBSIDIAN_GOLEM_SPAWN_EGG);
            event.accept(ModItems.SEA_SERPENT_SPAWN_EGG);
            event.accept(ModItems.OBSIDIAN_ROD);
            event.accept(ModItems.FORSAKEN_FEATHER);
            event.accept(ModItems.SEA_SERPENT_TOOTH);
            event.accept(ModItems.FLAMING_FLOURITE_HELMET);
            event.accept(ModItems.FLAMING_FLOURITE_CHESTPLATE);
            event.accept(ModItems.FLAMING_FLOURITE_LEGGINGS);
            event.accept(ModItems.FLAMING_FLOURITE_BOOTS);
            event.accept(ModItems.RAINBOW_APPLE);
            event.accept(ModItems.GOLDEN_CHICKEN_BREAST);
            event.accept(ModItems.UNHOLY_SAVIOUR_SWORD_HANDLE);
            event.accept(ModItems.UNHOLY_SAVIOUR_SWORD_CROSSGUARD);
            event.accept(ModItems.UNHOLY_SAVIOUR_SWORD_BLADE);
            event.accept(ModItems.CORRUPTED_HELMET);
            event.accept(ModItems.CORRUPTED_CHESTPLATE);
            event.accept(ModItems.CORRUPTED_LEGGINGS);
            event.accept(ModItems.CORRUPTED_BOOTS);
            event.accept(ModItems.CORRUPTED_CORE);
            event.accept(ModItems.CORRUPTED_BATTLE_AXE);
            event.accept(ModItems.GASOLINE_BUCKET);



            // Create the enchanted ItemStack for the Unholy Saviour Sword
            ItemStack enchantedSword = new ItemStack(ModItems.UNHOLY_SAVIOUR_SWORD.get());
            enchantedSword.enchant(Enchantments.SHARPNESS, 10);
            enchantedSword.enchant(Enchantments.UNBREAKING, 5);
            enchantedSword.enchant(Enchantments.FIRE_ASPECT, 2);
            enchantedSword.enchant(Enchantments.KNOCKBACK, 5);
            enchantedSword.enchant(Enchantments.MENDING, 1);
            enchantedSword.enchant(Enchantments.MOB_LOOTING, 3);

            // Add the enchanted sword to the creative tab
            event.accept(enchantedSword);


        }
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ModBlocks.FLAMING_FLOURITE_BLOCK);
            event.accept(ModBlocks.FLAMING_FLOURITE_ORE);


        }

        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(ModItems.FLAMING_FLOURITE_PICKAXE);
            event.accept(ModItems.FLAMING_FLOURITE_AXE);
            event.accept(ModItems.FLAMING_FLOURITE_SHOVEL);
            event.accept(ModItems.FLAMING_FLOURITE_HOE);
            event.accept(ModItems.GASOLINE_BUCKET);

        }

        if (event.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {
            event.accept(ModBlocks.CYAN_LOG);
            event.accept(ModBlocks.CYAN_WOOD);
            event.accept(ModBlocks.CYAN_PLANKS);
            event.accept(ModBlocks.CYAN_SAPLING);
            event.accept(ModBlocks.STRIPPED_CYAN_WOOD);
            event.accept(ModBlocks.STRIPPED_CYAN_LOG);
            event.accept(ModBlocks.PINK_LEAVES);
        }

        if (event.getTabKey() == CreativeModeTabs.SPAWN_EGGS){
            event.accept(ModItems.PARASAUR_SPAWN_EGG);
            event.accept(ModItems.MOZAIC_BUTTERFLY_SPAWN_EGG);
            event.accept(ModItems.GOLDEN_CHICKEN_SPAWN_EGG);
            event.accept(ModItems.OBSIDIAN_GOLEM_SPAWN_EGG);
            event.accept(ModItems.SEA_SERPENT_SPAWN_EGG);
        }

        if (event.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS){
            event.accept(ModItems.RAINBOW_APPLE);
            event.accept(ModItems.GOLDEN_CHICKEN_BREAST);
        }

        if (event.getTabKey() == CreativeModeTabs.COMBAT){
            event.accept(ModItems.FLAMING_FLOURITE_SWORD);
            event.accept(ModItems.FLAMING_FLOURITE_HELMET);
            event.accept(ModItems.FLAMING_FLOURITE_CHESTPLATE);
            event.accept(ModItems.FLAMING_FLOURITE_LEGGINGS);
            event.accept(ModItems.FLAMING_FLOURITE_BOOTS);
            event.accept(ModItems.UNHOLY_SAVIOUR_SWORD_HANDLE);
            event.accept(ModItems.UNHOLY_SAVIOUR_SWORD_CROSSGUARD);
            event.accept(ModItems.UNHOLY_SAVIOUR_SWORD_BLADE);
            event.accept(ModItems.CORRUPTED_HELMET);
            event.accept(ModItems.CORRUPTED_CHESTPLATE);
            event.accept(ModItems.CORRUPTED_LEGGINGS);
            event.accept(ModItems.CORRUPTED_BOOTS);
            event.accept(ModItems.CORRUPTED_BATTLE_AXE);

            // Create the enchanted ItemStack for the Unholy Saviour Sword
            ItemStack enchantedSword = new ItemStack(ModItems.UNHOLY_SAVIOUR_SWORD.get());
            enchantedSword.enchant(Enchantments.SHARPNESS, 10);
            enchantedSword.enchant(Enchantments.UNBREAKING, 5);
            enchantedSword.enchant(Enchantments.FIRE_ASPECT, 2);
            enchantedSword.enchant(Enchantments.KNOCKBACK, 5);
            enchantedSword.enchant(Enchantments.MENDING, 1);
            enchantedSword.enchant(Enchantments.MOB_LOOTING, 3);

            // Add the enchanted sword to the creative tab
            event.accept(enchantedSword);
        }
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(ModEntities.PARASAUR.get(), ParasaurRenderer::new);
            EntityRenderers.register(ModEntities.MOZAIC_BUTTERFLY.get(), MozaicButterflyRenderer::new);
            EntityRenderers.register(ModEntities.GOLDEN_CHICKEN.get(), GoldenChickenRenderer::new);
            EntityRenderers.register(ModEntities.OBSIDIAN_GOLEM.get(), ObsidianGolemRenderer::new);
            EntityRenderers.register(ModEntities.SEA_SERPENT.get(), SeaSerpentRenderer::new);

            //MenuScreens.register(ModMenuTypes..get(), ::new);

            ItemBlockRenderTypes.setRenderLayer(ModFluids.SOURCE_GASOLINE.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.FLOWING_GASOLINE.get(), RenderType.translucent());


        }
    }



}