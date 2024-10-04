package net.purgeddd.testmod.item;

import net.minecraft.world.item.*;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.purgeddd.testmod.TestMod;
import net.purgeddd.testmod.entity.ModEntities;
import net.purgeddd.testmod.fluid.ModFluids;
import net.purgeddd.testmod.item.custom.CorruptedArmorItem;
import net.purgeddd.testmod.item.custom.FlamingFlouriteBowItem;
import net.purgeddd.testmod.item.custom.ModArmorItem;
import net.purgeddd.testmod.item.custom.UnholySaviourSwordItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, TestMod.MOD_ID);

    public static final RegistryObject<Item> FLAMING_FLOURITE = ITEMS.register("flaming_flourite",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> OBSIDIAN_ROD = ITEMS.register("obsidian_rod",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> RAINBOW_APPLE = ITEMS.register("rainbow_apple",
            () -> new SimpleFoiledItem(new Item.Properties().rarity(Rarity.RARE).food(ModFoods.RAINBOW_APPLE)));

    public static final RegistryObject<Item> FORSAKEN_FEATHER = ITEMS.register("forsaken_feather",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CORRUPTED_CORE = ITEMS.register("corrupted_core",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> SEA_SERPENT_TOOTH = ITEMS.register("sea_serpent_tooth",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> UNHOLY_SAVIOUR_SWORD_BLADE = ITEMS.register("unholy_saviour_sword_blade",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> UNHOLY_SAVIOUR_SWORD_CROSSGUARD = ITEMS.register("unholy_saviour_sword_crossguard",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> UNHOLY_SAVIOUR_SWORD_HANDLE = ITEMS.register("unholy_saviour_sword_handle",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> GOLDEN_CHICKEN_BREAST = ITEMS.register("golden_chicken_breast",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON).food(ModFoods.GOLDEN_CHICKEN_BREAST)));

    public static final RegistryObject<Item> FLAMING_FLOURITE_SWORD = ITEMS.register("flaming_flourite_sword",
            () -> new SwordItem(ModToolTiers.FLAMING_FLOURITE, 6,2, new Item.Properties()));

    public static final RegistryObject<Item> FLAMING_FLOURITE_PICKAXE = ITEMS.register("flaming_flourite_pickaxe",
            () -> new PickaxeItem(ModToolTiers.FLAMING_FLOURITE, 2,2, new Item.Properties()));

    public static final RegistryObject<Item> FLAMING_FLOURITE_AXE = ITEMS.register("flaming_flourite_axe",
            () -> new AxeItem(ModToolTiers.FLAMING_FLOURITE, 8,1, new Item.Properties()));

    public static final RegistryObject<Item> FLAMING_FLOURITE_SHOVEL = ITEMS.register("flaming_flourite_shovel",
            () -> new ShovelItem(ModToolTiers.FLAMING_FLOURITE, 0,0, new Item.Properties()));

    public static final RegistryObject<Item> FLAMING_FLOURITE_HOE = ITEMS.register("flaming_flourite_hoe",
            () -> new HoeItem(ModToolTiers.FLAMING_FLOURITE, 0,0, new Item.Properties()));

    public static final RegistryObject<Item> CORRUPTED_BATTLE_AXE = ITEMS.register("corrupted_battle_axe",
            () -> new AxeItem(ModToolTiers.CORRUPTED, 6,2, new Item.Properties()));

    public static final RegistryObject<Item> FLAMING_FLOURITE_BOW = ITEMS.register("flaming_flourite_bow",
            () -> new FlamingFlouriteBowItem(new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> PARASAUR_SPAWN_EGG = ITEMS.register("parasaur_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.PARASAUR, 0xA2CD70,0xFF8700,
                    new Item.Properties()));

    public static final RegistryObject<Item> MOZAIC_BUTTERFLY_SPAWN_EGG = ITEMS.register("mozaic_butterfly_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.MOZAIC_BUTTERFLY, 0xFFFFFF,0xFFFFFF,
                    new Item.Properties()));

    public static final RegistryObject<Item> GOLDEN_CHICKEN_SPAWN_EGG = ITEMS.register("golden_chicken_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.GOLDEN_CHICKEN, 0xFFFFFF,0xFFFFFF,
                    new Item.Properties()));

    public static final RegistryObject<Item> OBSIDIAN_GOLEM_SPAWN_EGG = ITEMS.register("obsidian_golem_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.OBSIDIAN_GOLEM, 0xFFFFFF,0xFFFFFF,
                    new Item.Properties()));

    public static final RegistryObject<Item> SEA_SERPENT_SPAWN_EGG = ITEMS.register("sea_serpent_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntities.SEA_SERPENT, 0xFFFFFF,0xFFFFFF,
                    new Item.Properties()));

    public static final RegistryObject<Item> UNHOLY_SAVIOUR_SWORD = ITEMS.register("unholy_saviour_sword",
            () -> new UnholySaviourSwordItem(new Item.Properties().durability(4000)));

    public static final RegistryObject<Item> FLAMING_FLOURITE_HELMET = ITEMS.register("flaming_flourite_helmet",
            () -> new ModArmorItem(ModArmorMaterials.FLAMING_FLOURITE, ArmorItem.Type.HELMET, new Item.Properties()));

    public static final RegistryObject<Item> FLAMING_FLOURITE_CHESTPLATE = ITEMS.register("flaming_flourite_chestplate",
            () -> new ArmorItem(ModArmorMaterials.FLAMING_FLOURITE, ArmorItem.Type.CHESTPLATE, new Item.Properties()));

    public static final RegistryObject<Item> FLAMING_FLOURITE_LEGGINGS = ITEMS.register("flaming_flourite_leggings",
            () -> new ArmorItem(ModArmorMaterials.FLAMING_FLOURITE, ArmorItem.Type.LEGGINGS, new Item.Properties()));

    public static final RegistryObject<Item> FLAMING_FLOURITE_BOOTS = ITEMS.register("flaming_flourite_boots",
            () -> new ArmorItem(ModArmorMaterials.FLAMING_FLOURITE, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> CORRUPTED_HELMET = ITEMS.register("corrupted_helmet",
            () -> new CorruptedArmorItem(ModArmorMaterials.CORRUPTED, ArmorItem.Type.HELMET, new Item.Properties()));

    public static final RegistryObject<Item> CORRUPTED_CHESTPLATE = ITEMS.register("corrupted_chestplate",
            () -> new CorruptedArmorItem(ModArmorMaterials.CORRUPTED, ArmorItem.Type.CHESTPLATE, new Item.Properties()));

    public static final RegistryObject<Item> CORRUPTED_LEGGINGS = ITEMS.register("corrupted_leggings",
            () -> new CorruptedArmorItem(ModArmorMaterials.CORRUPTED, ArmorItem.Type.LEGGINGS, new Item.Properties()));

    public static final RegistryObject<Item> CORRUPTED_BOOTS = ITEMS.register("corrupted_boots",
            () -> new CorruptedArmorItem(ModArmorMaterials.CORRUPTED, ArmorItem.Type.BOOTS, new Item.Properties()));

    public static final RegistryObject<Item> GASOLINE_BUCKET = ITEMS.register("gasoline_bucket",
            () -> new BucketItem(ModFluids.SOURCE_GASOLINE, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));



    public static void register(IEventBus eventbus){
        ITEMS.register(eventbus);
    }
}
