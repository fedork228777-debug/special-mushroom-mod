package com.specialmushroom;

import com.specialmushroom.block.ModBlocks;
import com.specialmushroom.SpecialMushroomMod;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

/**
 * Registers worldgen: special mushrooms spawn in forest biomes.
 * Call registerWorldgen() from SpecialMushroomMod.onInitialize().
 */
public class ModWorldgen {

    public static final RegistryKey<PlacedFeature> SPECIAL_MUSHROOM_PLACED =
            RegistryKey.of(RegistryKeys.PLACED_FEATURE,
                    new Identifier(SpecialMushroomMod.MOD_ID, "special_mushroom_placed"));

    public static void registerWorldgen() {
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(
                        BiomeKeys.FOREST,
                        BiomeKeys.FLOWER_FOREST,
                        BiomeKeys.BIRCH_FOREST,
                        BiomeKeys.OLD_GROWTH_BIRCH_FOREST,
                        BiomeKeys.DARK_FOREST,
                        BiomeKeys.TAIGA,
                        BiomeKeys.OLD_GROWTH_PINE_TAIGA,
                        BiomeKeys.OLD_GROWTH_SPRUCE_TAIGA,
                        BiomeKeys.MUSHROOM_FIELDS
                ),
                GenerationStep.Feature.VEGETAL_DECORATION,
                SPECIAL_MUSHROOM_PLACED
        );
    }
}
