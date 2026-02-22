package com.specialmushroom.block;

import com.specialmushroom.SpecialMushroomMod;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block SPECIAL_MUSHROOM = new SpecialMushroomBlock(
            FabricBlockSettings.copyOf(Blocks.RED_MUSHROOM)
    );

    public static void registerBlocks() {
        Registry.register(Registries.BLOCK,
                new Identifier(SpecialMushroomMod.MOD_ID, "special_mushroom"),
                SPECIAL_MUSHROOM);

        Registry.register(Registries.ITEM,
                new Identifier(SpecialMushroomMod.MOD_ID, "special_mushroom"),
                new BlockItem(SPECIAL_MUSHROOM, new FabricItemSettings()));

        SpecialMushroomMod.LOGGER.info("Blocks registered!");
    }
}
