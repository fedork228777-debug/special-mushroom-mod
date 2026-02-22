package com.specialmushroom.item;

import com.specialmushroom.effect.MushroomTripEffect;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.Random;

public class FriedSpecialMushroomItem extends Item {

    public static final FoodComponent FOOD_COMPONENT = new FoodComponent.Builder()
            .hunger(4)
            .saturationModifier(0.3f)
            .statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 200, 1), 1.0f)
            .build();

    public FriedSpecialMushroomItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        ItemStack result = super.finishUsing(stack, world, user);

        if (!world.isClient()) {
            // Apply nausea (already in food component, but extend it)
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 300, 1));

            // Pick a random visual effect (1-4)
            Random random = new Random();
            int effectType = random.nextInt(5);

            switch (effectType) {
                case 0:
                    // Blindness - слепота
                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, 200, 0));
                    break;
                case 1:
                    // Night vision as "rainbow" simulation + glowing
                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 400, 0));
                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 400, 0));
                    break;
                case 2:
                    // Darkness (1.19+) - ЧБ эффект / эффект тьмы
                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.DARKNESS, 300, 1));
                    break;
                case 3:
                    // Levitation - изменение пространства (левитация)
                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 100, 0));
                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOW_FALLING, 200, 0));
                    break;
                case 4:
                    // Confusion mix - тошнота + головокружение
                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 400, 2));
                    user.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 200, 0));
                    break;
            }

            // Always add a short confusion effect (waves / distortion)
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 100, 1));
        }

        return result;
    }
}
