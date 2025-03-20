package com.theendercore.terastal_expansion.mixin.client;

import com.cobblemon.mod.common.client.gui.summary.Summary;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.theendercore.terastal_expansion.client.RenderingKt.summeryScreenIcon;

@Mixin(value = Summary.class, remap = false)
public abstract class SummaryMixin {
    @Shadow
    public Pokemon selectedPokemon;

    @Inject(method = "render", at = @At("TAIL"))
    void renderUI(GuiGraphics ctx, int mouseX, int mouseY, float delta,
                  CallbackInfo ci, @Local(ordinal = 2) int x, @Local(ordinal = 3) int y, @Local PoseStack matrices) {
        var type = this.selectedPokemon.getTeraType();

        summeryScreenIcon(ctx, type, matrices, x, y);
    }
}
