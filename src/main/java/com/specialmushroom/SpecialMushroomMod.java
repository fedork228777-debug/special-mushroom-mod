package com.specialmushroom;

import com.specialmushroom.block.ModBlocks;
import com.specialmushroom.item.ModItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SpecialMushroomMod implements ModInitializer {
    public static final String MOD_ID = "specialmushroom";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModBlocks.registerBlocks();
        ModItems.registerItems();
        ModWorldgen.registerWorldgen();
        LOGGER.info("Special Mushroom Mod loaded!");
    }
}
