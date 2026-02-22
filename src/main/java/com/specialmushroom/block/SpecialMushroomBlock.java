package com.specialmushroom.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.MushroomPlantBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;

public class SpecialMushroomBlock extends MushroomPlantBlock {

    public SpecialMushroomBlock(Settings settings) {
        super(settings, null); // no huge mushroom feature
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        // Can grow on grass, podzol, dirt, mycelium - standard forest floors
        Block block = floor.getBlock();
        return block == Blocks.GRASS_BLOCK
                || block == Blocks.PODZOL
                || block == Blocks.DIRT
                || block == Blocks.COARSE_DIRT
                || block == Blocks.MYCELIUM
                || block == Blocks.ROOTED_DIRT
                || super.canPlantOnTop(floor, world, pos);
    }
}
