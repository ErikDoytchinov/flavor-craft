package net.eDoytchinov.flavorcraft.block.entity;

import net.eDoytchinov.flavorcraft.block.BlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class MillingStationEntity extends net.minecraft.world.level.block.entity.BlockEntity {
    public static final int MAX_PROGRESS = 100;
    public int progress;

    public MillingStationEntity(BlockPos pos, BlockState state){
        super(BlockEntity.MILLINGSTATION.get(),pos, state);
    }

    public void tick(){
        progress++;
        if(progress > MAX_PROGRESS){
            progress = 0;
            var pig = new Pig(EntityType.PIG,this.level);
            pig.setPos(this.worldPosition.getX(), this.worldPosition.getY() + 1.5D, this.worldPosition.getZ());
            this.level.addFreshEntity(pig);
        }
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        this.progress = nbt.getInt("Progress");
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        super.saveAdditional(nbt);
        nbt.putInt("Progress", this.progress);
    }
}