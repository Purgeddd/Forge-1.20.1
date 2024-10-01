package net.purgeddd.testmod.datagen;

import net.minecraft.resources.ResourceKey;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ModelFile;
import net.purgeddd.testmod.TestMod;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.purgeddd.testmod.block.ModBlocks;
import net.purgeddd.testmod.item.ModItems;

import java.util.LinkedHashMap;

public class ModItemModelProvider extends ItemModelProvider {

    private static LinkedHashMap<ResourceKey<TrimMaterial>, Float> trimMaterials = new LinkedHashMap<>();
    static {
        trimMaterials.put(TrimMaterials.QUARTZ, 0.1F);
        trimMaterials.put(TrimMaterials.IRON, 0.2F);
        trimMaterials.put(TrimMaterials.NETHERITE, 0.3F);
        trimMaterials.put(TrimMaterials.REDSTONE, 0.4F);
        trimMaterials.put(TrimMaterials.COPPER, 0.5F);
        trimMaterials.put(TrimMaterials.GOLD, 0.6F);
        trimMaterials.put(TrimMaterials.EMERALD, 0.7F);
        trimMaterials.put(TrimMaterials.DIAMOND, 0.8F);
        trimMaterials.put(TrimMaterials.LAPIS, 0.9F);
        trimMaterials.put(TrimMaterials.AMETHYST, 1.0F);
    }

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, TestMod.MOD_ID, existingFileHelper);
    }


    private void trimmedArmorItem(RegistryObject<Item> itemRegistryObject) {
        final String MOD_ID = TestMod.MOD_ID;

        if(itemRegistryObject.get() instanceof ArmorItem armorItem) {
            trimMaterials.entrySet().forEach(entry -> {

                ResourceKey<TrimMaterial> trimMaterial = entry.getKey();
                float trimValue = entry.getValue();

                String armorType = switch (armorItem.getEquipmentSlot()) {
                    case HEAD -> "helmet";
                    case CHEST -> "chestplate";
                    case LEGS -> "leggings";
                    case FEET -> "boots";
                    default -> "";
                };

                String armorItemPath = "item/" + armorItem;
                String trimPath = "trims/items/" + armorType + "_trim_" + trimMaterial.location().getPath();
                String currentTrimName = armorItemPath + "_" + trimMaterial.location().getPath() + "_trim";
                ResourceLocation armorItemResLoc = new ResourceLocation(MOD_ID, armorItemPath);
                ResourceLocation trimResLoc = new ResourceLocation(trimPath); // minecraft namespace
                ResourceLocation trimNameResLoc = new ResourceLocation(MOD_ID, currentTrimName);

                // This is used for making the ExistingFileHelper acknowledge that this texture exist, so this will
                // avoid an IllegalArgumentException
                existingFileHelper.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures");

                // Trimmed armorItem files
                getBuilder(currentTrimName)
                        .parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", armorItemResLoc)
                        .texture("layer1", trimResLoc);

                // Non-trimmed armorItem file (normal variant)
                this.withExistingParent(itemRegistryObject.getId().getPath(),
                                mcLoc("item/generated"))
                        .override()
                        .model(new ModelFile.UncheckedModelFile(trimNameResLoc))
                        .predicate(mcLoc("trim_type"), trimValue).end()
                        .texture("layer0",
                                new ResourceLocation(MOD_ID,
                                        "item/" + itemRegistryObject.getId().getPath()));
            });
        }
    }


    @Override
    protected void registerModels() {
        simpleItem(ModItems.FLAMING_FLOURITE);
        saplingItem(ModBlocks.CYAN_SAPLING);
        simpleItem(ModItems.OBSIDIAN_ROD);
        simpleItem(ModItems.FORSAKEN_FEATHER);
        simpleItem(ModItems.UNHOLY_SAVIOUR_SWORD_BLADE);
        simpleItem(ModItems.UNHOLY_SAVIOUR_SWORD_CROSSGUARD);
        simpleItem(ModItems.UNHOLY_SAVIOUR_SWORD_HANDLE);
        simpleItem(ModItems.RAINBOW_APPLE);
        simpleItem(ModItems.GOLDEN_CHICKEN_BREAST);
        simpleItem(ModItems.CORRUPTED_HELMET);
        simpleItem(ModItems.CORRUPTED_CHESTPLATE);
        simpleItem(ModItems.CORRUPTED_LEGGINGS);
        simpleItem(ModItems.CORRUPTED_BOOTS);
        simpleItem(ModItems.CORRUPTED_CORE);
        simpleItem(ModItems.SEA_SERPENT_TOOTH);
        simpleItem(ModItems.GASOLINE_BUCKET);

        withExistingParent(ModItems.PARASAUR_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.MOZAIC_BUTTERFLY_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.GOLDEN_CHICKEN_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.OBSIDIAN_GOLEM_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(ModItems.SEA_SERPENT_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));

        handheldItem(ModItems.FLAMING_FLOURITE_SWORD);
        handheldItem(ModItems.FLAMING_FLOURITE_PICKAXE);
        handheldItem(ModItems.FLAMING_FLOURITE_AXE);
        handheldItem(ModItems.FLAMING_FLOURITE_SHOVEL);
        handheldItem(ModItems.FLAMING_FLOURITE_HOE);
        handheldItem(ModItems.CORRUPTED_BATTLE_AXE);

        trimmedArmorItem(ModItems.FLAMING_FLOURITE_HELMET);
        trimmedArmorItem(ModItems.FLAMING_FLOURITE_CHESTPLATE);
        trimmedArmorItem(ModItems.FLAMING_FLOURITE_LEGGINGS);
        trimmedArmorItem(ModItems.FLAMING_FLOURITE_BOOTS);
    }

    private ItemModelBuilder saplingItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(TestMod.MOD_ID,"block/" + item.getId().getPath()));
    }


    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(TestMod.MOD_ID,"item/" + item.getId().getPath()));
    }

    private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(TestMod.MOD_ID,"item/" + item.getId().getPath()));
    }



}