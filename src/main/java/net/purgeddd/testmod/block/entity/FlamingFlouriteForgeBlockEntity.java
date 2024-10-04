package net.purgeddd.testmod.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandlerItem;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.purgeddd.testmod.block.custom.FlamingFlouriteForgeBlock;
import net.purgeddd.testmod.fluid.ModFluids;
import net.purgeddd.testmod.item.ModItems;
import net.purgeddd.testmod.recipe.FlamingFlouriteForgeRecipe;
import net.purgeddd.testmod.screen.FlamingFlouriteForgeMenu;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class FlamingFlouriteForgeBlockEntity extends BlockEntity implements MenuProvider {

    private final ItemStackHandler itemStackHandler = new ItemStackHandler(3){

        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return switch (slot) {
                case 0 -> stack.getCapability(ForgeCapabilities.FLUID_HANDLER_ITEM).isPresent();
                case 1 -> false;
                case 2 -> true;
                default -> super.isItemValid(slot, stack);
            };
        }
    };

    private static final int FLUID_INPUT_SLOT = 0;
    private static final int OUTPUT_SLOT = 1;
    private static final int INPUT_SLOT = 2;

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

    private LazyOptional<IFluidHandler> lazyFluidHandler = LazyOptional.empty();

    protected final ContainerData data;
    protected int progress = 0;
    private int maxProgress = 70;

    private final FluidTank FLUID_TANK = createFluidTank();

    private FluidTank createFluidTank() {
        return new FluidTank(100000) {
            @Override
            protected void onContentsChanged() {
                setChanged();
                if(!level.isClientSide()){
                    level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
                }
            }

            @Override
            public boolean isFluidValid(FluidStack stack) {
                return stack.getFluid() == ModFluids.SOURCE_GASOLINE.get();
            }
        };
    }

    public FlamingFlouriteForgeBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.FLAMING_FLOURITE_FORGE_BE.get(), blockPos, blockState);
        this.data = new ContainerData() {
            @Override
            public int get(int pIndex) {
                return switch (pIndex) {
                    case 0 -> FlamingFlouriteForgeBlockEntity.this.progress;
                    case 1 -> FlamingFlouriteForgeBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int pIndex, int pValue) {
                switch (pIndex) {
                    case 0 -> FlamingFlouriteForgeBlockEntity.this.progress = pValue;
                    case 1 -> FlamingFlouriteForgeBlockEntity.this.maxProgress = pValue;
                }

            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    public FluidStack getFluid() {
        return FLUID_TANK.getFluid();
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemStackHandler.getSlots());
        for (int i = 0; i < itemStackHandler.getSlots(); i++) {
            inventory.setItem(i, itemStackHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Flaming Flourite Forge");
    }

    @Override
    @Nullable
    public AbstractContainerMenu createMenu(int pContainerData, Inventory inventory, Player player) {
        return new FlamingFlouriteForgeMenu(pContainerData, inventory, this, this.data);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER){
            return lazyItemHandler.cast();
        }

        if(cap == ForgeCapabilities.FLUID_HANDLER){
            return lazyFluidHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemStackHandler);
        lazyFluidHandler = LazyOptional.of(() -> FLUID_TANK);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
        lazyFluidHandler.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        tag.put("inventory", itemStackHandler.serializeNBT());
        tag = FLUID_TANK.writeToNBT(tag);
        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        itemStackHandler.deserializeNBT(tag.getCompound("inventory"));
        FLUID_TANK.readFromNBT(tag);
    }



    public void tick(Level level, BlockPos pPos, BlockState pState) {

        boolean isLit = pState.getValue(FlamingFlouriteForgeBlock.LIT);

        fillUpOnFluid();
        
        if (isOutputSlotEmptyOrReceivable() && hasRecipe()) {
            increaseCraftingProcess();
            setChanged(level, pPos, pState);

            if (!isLit) {
                level.setBlock(pPos, pState.setValue(FlamingFlouriteForgeBlock.LIT, true), 3);
            }


            if (hasProgressFinished()) {
                craftItem();
                extractFluid();
                resetProgress();
            }

        } else {
            resetProgress();
            if (isLit) {
                level.setBlock(pPos, pState.setValue(FlamingFlouriteForgeBlock.LIT, false), 3);
            }
        }
    }

    private void extractFluid() {
        this.FLUID_TANK.drain(500, IFluidHandler.FluidAction.EXECUTE);
    }

    private void fillUpOnFluid() {
        if(hasFluidSourceInSlot(FLUID_INPUT_SLOT)){
            transferItemFluidToTank(FLUID_INPUT_SLOT);
        }
    }

    private void transferItemFluidToTank(int fluidInputSlot) {
        this.itemStackHandler.getStackInSlot(fluidInputSlot).getCapability(ForgeCapabilities.FLUID_HANDLER_ITEM).ifPresent(IFluidHandlerItem -> {
            int drainAmount = Math.min(this.FLUID_TANK.getSpace(), 2000);

            FluidStack stack = IFluidHandlerItem.drain(drainAmount, IFluidHandler.FluidAction.SIMULATE);
            if(stack.getFluid() == ModFluids.SOURCE_GASOLINE.get()){
                stack = IFluidHandlerItem.drain(drainAmount, IFluidHandler.FluidAction.EXECUTE);
                fillTankWithFluid(stack, IFluidHandlerItem.getContainer());
            }

        });
    }

    private void fillTankWithFluid(FluidStack stack, ItemStack container) {
        this.FLUID_TANK.fill(new FluidStack(stack.getFluid(), stack.getAmount()), IFluidHandler.FluidAction.EXECUTE);

        this.itemStackHandler.extractItem(FLUID_INPUT_SLOT, 1, false);
        this.itemStackHandler.insertItem(FLUID_INPUT_SLOT, container, false);
    }

    private boolean hasFluidSourceInSlot(int fluidInputSlot) {
        return this.itemStackHandler.getStackInSlot(fluidInputSlot).getCount() > 0 &&
                this.itemStackHandler.getStackInSlot(fluidInputSlot).getCapability(ForgeCapabilities.FLUID_HANDLER_ITEM).isPresent();
    }

    private void craftItem() {

        Optional<FlamingFlouriteForgeRecipe> recipe = getCurrentRecipe();
        ItemStack resultItem = recipe.get().getResultItem(getLevel().registryAccess());

        this.itemStackHandler.extractItem(INPUT_SLOT, 1, false);

        this.itemStackHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(resultItem.getItem(),
                this.itemStackHandler.getStackInSlot(OUTPUT_SLOT).getCount() + resultItem.getCount()));
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private boolean hasProgressFinished() {
        return this.progress >= this.maxProgress;
    }

    private void increaseCraftingProcess() {
        this.progress++;
    }

    private boolean hasRecipe() {

        Optional<FlamingFlouriteForgeRecipe> recipe = getCurrentRecipe();

        if (recipe.isEmpty()) {
            return false;
        }

        ItemStack resultItem = recipe.get().getResultItem(getLevel().registryAccess());

        return canInsertAmountIntoOutputSlot(resultItem.getCount())
                && canInsertItemIntoOutputSlot(resultItem.getItem())
                && hasEnoughFluidToCraft();
    }

    private boolean hasEnoughFluidToCraft() {
        return this.FLUID_TANK.getFluidAmount() >= 500;
    }

    private Optional<FlamingFlouriteForgeRecipe> getCurrentRecipe() {
        SimpleContainer inventory = new SimpleContainer(this.itemStackHandler.getSlots());
        for (int i = 0; i < this.itemStackHandler.getSlots(); i++) {
            inventory.setItem(i, this.itemStackHandler.getStackInSlot(i));
        }
        return this.level.getRecipeManager().getRecipeFor(FlamingFlouriteForgeRecipe.Type.INSTANCE, inventory, level);
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.itemStackHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() || this.itemStackHandler.getStackInSlot(OUTPUT_SLOT).is(item);
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        return this.itemStackHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize() >=
                this.itemStackHandler.getStackInSlot(OUTPUT_SLOT).getCount() + count;
    }

    private boolean isOutputSlotEmptyOrReceivable() {
        return this.itemStackHandler.getStackInSlot(OUTPUT_SLOT).isEmpty() ||
                this.itemStackHandler.getStackInSlot(OUTPUT_SLOT).getCount() < this.itemStackHandler.getStackInSlot(OUTPUT_SLOT).getMaxStackSize();
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        return saveWithoutMetadata();
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        super.onDataPacket(net, pkt);
    }


}


