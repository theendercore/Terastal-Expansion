package com.theendercore.terastal_expansion.mixin.client;

import com.cobblemon.mod.common.client.gui.summary.Summary;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.cobblemon.mod.common.client.render.RenderHelperKt.drawScaledText;
import static com.theendercore.terastal_expansion.client.RenderingKt.summeryScreenIcon;
import static com.theendercore.terastal_expansion.misc.MixinKt.getTerastallizedType;

@Mixin(value = Summary.class, remap = false)
public abstract class SummaryMixin extends Screen {

    @Shadow
    public Pokemon selectedPokemon;

    @Shadow @Final private static float SCALE;

    protected SummaryMixin(Component title) {
        super(title);
    }


    @Inject(method = "render", at = @At("TAIL"))
    void x(GuiGraphics ctx, int mouseX, int mouseY, float delta,
           CallbackInfo ci, @Local(ordinal = 2) int x, @Local(ordinal = 3) int y, @Local PoseStack matrices) {
        var text = Component.literal("Tera : ");
        var text2 = Component.literal("Terastallized Type : ");
        var state = getTerastallizedType(this.selectedPokemon);
        if (state != null) text2.append(state.getDisplayName());
        else text2.append("None");
        var type = this.selectedPokemon.getTeraType();
        drawScaledText(
                ctx, text.append(type.getDisplayName()).getVisualOrderText(),
                x + 39, y + 148,
                1f, 1f, 1f, 0xffffff,
                true, true
        );
        drawScaledText(
                ctx, text2.getVisualOrderText(),
                x + 39, y + 158,
                1f, 1f, 1f, 0xffffff,
                true, true
        );

        summeryScreenIcon(ctx, type, matrices, x, y);
    }
}
