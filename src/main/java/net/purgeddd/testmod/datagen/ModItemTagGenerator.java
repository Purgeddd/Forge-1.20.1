package net.purgeddd.testmod.datagen;

import net.purgeddd.testmod.TestMod;
import net.purgeddd.testmod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {
    public ModItemTagGenerator(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> aSuper,
                               CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, aSuper, p_275322_, TestMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.FLAMING_FLOURITE_HELMET.get(),
                        ModItems.FLAMING_FLOURITE_CHESTPLATE.get(),
                        ModItems.FLAMING_FLOURITE_LEGGINGS.get(),
                        ModItems.FLAMING_FLOURITE_BOOTS.get());
    }
}