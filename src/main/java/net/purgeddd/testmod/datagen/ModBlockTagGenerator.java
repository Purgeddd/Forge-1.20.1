package net.purgeddd.testmod.datagen;

import net.minecraft.world.item.MinecartItem;
import net.purgeddd.testmod.TestMod;
import net.purgeddd.testmod.block.ModBlocks;
import net.purgeddd.testmod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {

    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                                @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, TestMod.MOD_ID, existingFileHelper);
    }


    protected void addTags(HolderLookup.Provider pProvider) {

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.FLAMING_FLOURITE_BLOCK.get(),
                        ModBlocks.FLAMING_FLOURITE_ORE.get());

    }


        //this.tag(ModTags.Blocks.NEEDS_FLAMING_FLOURITE_TOOL).add(ModBlocks.))

}





