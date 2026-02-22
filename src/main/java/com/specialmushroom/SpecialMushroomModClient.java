package com.specialmushroom;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.entity.effect.StatusEffects;

/**
 * Client-side handler for trippy visual effects when the player
 * has eaten a Fried Special Mushroom.
 *
 * Effects applied:
 *  - Rainbow overlay   — when NIGHT_VISION is active
 *  - B&W / desaturate  — when DARKNESS is active
 *  - Wave distortion   — when NAUSEA is active (vanilla already does this,
 *                        but we amplify with a colour tint overlay)
 *  - Space distortion  — when LEVITATION is active (zoom pulsing overlay)
 *  - Blindness overlay — handled entirely by vanilla
 */
public class SpecialMushroomModClient implements ClientModInitializer {

    // Tick counter for animations
    private static long tickCount = 0;

    @Override
    public void onInitializeClient() {
        HudRenderCallback.EVENT.register(SpecialMushroomModClient::renderTripEffects);
    }

    private static void renderTripEffects(DrawContext context, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;

        var player = client.player;
        tickCount++;

        int screenW = client.getWindow().getScaledWidth();
        int screenH = client.getWindow().getScaledHeight();

        // === RAINBOW EFFECT (Night Vision active) ===
        if (player.hasStatusEffect(StatusEffects.NIGHT_VISION)) {
            // Cycle through hues over time
            float hue = (tickCount % 360) / 360.0f;
            int rgb = hsvToRgb(hue, 0.9f, 1.0f);
            int r = (rgb >> 16) & 0xFF;
            int g = (rgb >> 8) & 0xFF;
            int b = rgb & 0xFF;
            // Semi-transparent rainbow overlay
            int color = (60 << 24) | (r << 16) | (g << 8) | b;
            context.fill(0, 0, screenW, screenH, color);
        }

        // === B&W / DARKNESS TINT (Darkness active) ===
        if (player.hasStatusEffect(StatusEffects.DARKNESS)) {
            // Pulsing black-and-white desaturation overlay
            float pulse = (float)(Math.sin(tickCount * 0.1) * 0.5 + 0.5);
            int alpha = (int)(pulse * 80) + 20;
            int gray = 0x888888;
            int color = (alpha << 24) | gray;
            context.fill(0, 0, screenW, screenH, color);
        }

        // === WAVE / COLOR SHIFT (Nausea amplifier overlay) ===
        if (player.hasStatusEffect(StatusEffects.NAUSEA)) {
            // Alternating color tint to enhance the wave illusion
            double wave = Math.sin(tickCount * 0.15);
            int alpha = 35;
            int color;
            if (wave > 0.3) {
                color = (alpha << 24) | 0xFF6600; // orange tint
            } else if (wave < -0.3) {
                color = (alpha << 24) | 0x0033FF; // blue tint
            } else {
                color = (alpha << 24) | 0x00FF66; // green tint
            }
            context.fill(0, 0, screenW, screenH, color);
        }

        // === SPACE DISTORTION (Levitation active) ===
        if (player.hasStatusEffect(StatusEffects.LEVITATION)) {
            // Pulsing purple vignette
            double pulse = Math.abs(Math.sin(tickCount * 0.08));
            int alpha = (int)(pulse * 90) + 10;
            int color = (alpha << 24) | 0x9900CC; // purple
            // Draw vignette edges
            int edge = screenW / 8;
            context.fill(0, 0, edge, screenH, color);
            context.fill(screenW - edge, 0, screenW, screenH, color);
            context.fill(edge, 0, screenW - edge, screenH / 8, color);
            context.fill(edge, screenH - screenH / 8, screenW - edge, screenH, color);
        }
    }

    /**
     * Convert HSV to RGB int (0xRRGGBB)
     */
    private static int hsvToRgb(float h, float s, float v) {
        int i = (int)(h * 6);
        float f = h * 6 - i;
        float p = v * (1 - s);
        float q = v * (1 - f * s);
        float t = v * (1 - (1 - f) * s);
        float r, g, b;
        switch (i % 6) {
            case 0: r = v; g = t; b = p; break;
            case 1: r = q; g = v; b = p; break;
            case 2: r = p; g = v; b = t; break;
            case 3: r = p; g = q; b = v; break;
            case 4: r = t; g = p; b = v; break;
            default: r = v; g = p; b = q; break;
        }
        return ((int)(r * 255) << 16) | ((int)(g * 255) << 8) | (int)(b * 255);
    }
}
