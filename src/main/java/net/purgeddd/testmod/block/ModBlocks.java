package net.purgeddd.testmod.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.purgeddd.testmod.TestMod;
import net.purgeddd.testmod.block.custom.FlamingFlouriteForgeBlock;
import net.purgeddd.testmod.block.custom.ModFlammableRotatedPillarBlock;
import net.purgeddd.testmod.fluid.ModFluids;
import net.purgeddd.testmod.item.ModItems;
import net.purgeddd.testmod.worldgen.tree.CyanTreeGrower;

import java.util.function.Supplier;


public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, TestMod.MOD_ID);

    public static final RegistryObject<Block> FLAMING_FLOURITE_BLOCK = registerBlock("flaming_flourite_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).strength(5f).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> FLAMING_FLOURITE_ORE = registerBlock("flaming_flourite_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE).strength(5f).requiresCorrectToolForDrops(),
                    UniformInt.of(2,6)));

    public static final RegistryObject<Block> CYAN_LOG = registerBlock("cyan_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LOG).strength(2f)));

    public static final RegistryObject<Block> CYAN_WOOD = registerBlock("cyan_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.OAK_WOOD).strength(2f)));

    public static final RegistryObject<Block> STRIPPED_CYAN_LOG = registerBlock("stripped_cyan_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_LOG).strength(2f)));

    public static final RegistryObject<Block> STRIPPED_CYAN_WOOD = registerBlock("stripped_cyan_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.copy(Blocks.STRIPPED_OAK_WOOD).strength(2f)));


    public static final RegistryObject<Block> CYAN_PLANKS = registerBlock("cyan_planks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).strength(2f)){

                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }

            });

    public static final RegistryObject<Block> PINK_LEAVES = registerBlock("pink_leaves",
            () -> new LeavesBlock(BlockBehaviour.Properties.copy(Blocks.OAK_LEAVES).strength(2f)){

                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 30;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 60;
                }

                @Override
                public float getDestroyProgress(BlockState state, Player player, BlockGetter level, BlockPos pos) {
                    ItemStack tool = player.getMainHandItem();
                    if (tool.getItem() instanceof ShearsItem) {
                        return 15.0F; // or any other appropriate speed
                    }
                    return super.getDestroyProgress(state, player, level, pos);
                }


            });

    public static final RegistryObject<Block> CYAN_SAPLING = registerBlock("cyan_sapling",
            () -> new SaplingBlock(new CyanTreeGrower(), BlockBehaviour.Properties.copy(Blocks.OAK_SAPLING)));

    public static final RegistryObject<LiquidBlock> GASOLINE_BLOCK = BLOCKS.register("gasoline_block",
            () -> new LiquidBlock(ModFluids.SOURCE_GASOLINE, BlockBehaviour.Properties.copy(Blocks.WATER).noLootTable()));

    public static final RegistryObject<Block> FLAMING_FLOURITE_FORGE = registerBlock("flaming_flourite_forge",
            () -> new FlamingFlouriteForgeBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion()));




    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }


    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block){
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }


    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
