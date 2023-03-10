package com.github.sniffity.sniffityscreatures.block;

import com.github.sniffity.sniffityscreatures.registry.SCBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class BlockEntityWolfStatue extends BlockEntity {

    public boolean canTransformVillager = false;

    public BlockEntityWolfStatue(BlockPos pos, BlockState state) {
        super(SCBlockEntities.WOLF_STATUE_BE.get(), pos, state);
    }


    @Override
    public void load(CompoundTag compoundNBT) {
        super.load(compoundNBT);

        if(compoundNBT.contains("canTransformVillager"))
            this.canTransformVillager = compoundNBT.getBoolean("canTransformVillager");
    }

    @Override
    public void saveAdditional(CompoundTag compoundNBT) {
        super.saveAdditional(compoundNBT);
        compoundNBT.putBoolean("canTransformVillager", canTransformVillager);
    }


    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, BlockEntityWolfStatue pBlockEntity) {
        if (pBlockEntity.canTransformVillager) {
            if (pBlockEntity.getLevel() != null) {
                List<Villager> villagerList = pBlockEntity.getLevel().getEntitiesOfClass(Villager.class,pBlockEntity.getRenderBoundingBox().inflate(20));
                if (!villagerList.isEmpty()) {
                    //remove Villager children from the list
                    for (int i = 0; i < villagerList.size();i++) {
                        Villager testVillager = villagerList.get(i);
                        if (testVillager.getAge()<0) {
                            villagerList.remove(testVillager);
                        }
                    }
                    //add the Werewolf tag to a random Villager
                    Villager werewolf = villagerList.get(pBlockEntity.getLevel().getRandom().nextInt(villagerList.size()));{
                        werewolf.addTag("Werewolf");
                        System.out.println("Created a Werewolf at: "+"X: "+werewolf.position().x+" Y: "+werewolf.position().y+" Z: " + werewolf.position().z);
                        pBlockEntity.canTransformVillager = false;
                        setChanged(pLevel,pPos,pState);
                    }
                }
            }
        }
    }
}