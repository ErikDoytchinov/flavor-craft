package net.eDoytchinov.flavorcraft.block.entity;

import net.eDoytchinov.flavorcraft.block.ModBlockEntity;
import net.eDoytchinov.flavorcraft.item.ModItems;
import net.eDoytchinov.flavorcraft.screen.MillingStationMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MillingStationEntity extends net.minecraft.world.level.block.entity.BlockEntity implements MenuProvider {
    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 78;

    public MillingStationEntity(BlockPos pos, BlockState state){
        super(ModBlockEntity.MILLINGSTATION.get(),pos, state);
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> MillingStationEntity.this.progress;
                    case 1 -> MillingStationEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch(index) {
                    case 0 -> MillingStationEntity.this.progress = value;
                    case 1 -> MillingStationEntity.this.maxProgress = value;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    private final ItemStackHandler itemHandler = new ItemStackHandler(2) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();



    //on every tick if there is an item inside proceed to manufacture it
    public static void tick(Level level, BlockPos pos, BlockState state, MillingStationEntity pEntity){
        if(level.isClientSide()) {
            return;
        }

        if(hasRecipe(pEntity)) {
            pEntity.progress++;
            setChanged(level,pos,state);

            if(pEntity.progress >= pEntity.maxProgress) {
                craftItem(pEntity);
            }
        } else {
            pEntity.resetProgress();
            setChanged(level,pos,state);
        }
    }

    private void resetProgress(){
        this.progress = 0;
    }

    private static void craftItem(MillingStationEntity pEntity){
        if(hasRecipe(pEntity)) {
            pEntity.itemHandler.extractItem(0,1,false);
            pEntity.itemHandler.setStackInSlot(1, new ItemStack(ModItems.RICE.get(),
                    pEntity.itemHandler.getStackInSlot(1).getCount() + 1));

            pEntity.resetProgress();
        }
    }

    private static boolean hasRecipe(MillingStationEntity entity){
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for(int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }

        boolean hasRiceInFirstSlot = entity.itemHandler.getStackInSlot(0).getItem() == ModItems.LETTUCE.get();

        return hasRiceInFirstSlot && canInsertAmountIntoOutput(inventory) && canInsertItemIntoOutputSlot(inventory, new ItemStack(ModItems.RICE.get(), 1));
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleContainer inventory, ItemStack stack) {
        return inventory.getItem(1).getItem() == stack.getItem() || inventory.getItem(1).isEmpty();
    }

    private static boolean canInsertAmountIntoOutput(SimpleContainer inventory) {
        return inventory.getItem(1).getMaxStackSize() > inventory.getItem(1).getCount();
    }


    //drops the contents of the block if destroyed
    public void drops(){
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots();i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level,this.worldPosition, inventory);
    }

    //this loads the inventory that has been save to "Inventory" to our itemHandler
    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("Inventory"));
    }

    //this will store the contents of the itemHandler to "Inventory"
    @Override
    protected void saveAdditional(CompoundTag nbt) {
        nbt.put("Inventory", itemHandler.serializeNBT());
        super.saveAdditional(nbt);
    }

    @Nullable
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player){
        return new MillingStationMenu(id, inventory, this, this.data);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER){
            return lazyItemHandler.cast();
        }

        return super.getCapability(cap, side);
    }


    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(()-> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Milling Station");
    }
}