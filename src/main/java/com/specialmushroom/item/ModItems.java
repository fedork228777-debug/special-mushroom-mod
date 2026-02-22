package com.specialmushroom.item;

import com.specialmushroom.SpecialMushroomMod;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item FRIED_SPECIAL_MUSHROOM = new FriedSpecialMushroomItem(
            new FabricItemSettings().food(FriedSpecialMushroomItem.FOOD_COMPONENT).maxCount(16)
    );

    public static void registerItems() {
        Registry.register(Registries.ITEM,
                new Identifier(SpecialMushroomMod.MOD_ID, "fried_special_mushroom"),
                FRIED_SPECIAL_MUSHROOM);

        SpecialMushroomMod.LOGGER.info("Items registered!");
    }
}
