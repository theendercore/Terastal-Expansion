package com.theendercore.terastal_expansion.mixin.client;

import com.cobblemon.mod.common.client.gui.summary.Summary;
import com.cobblemon.mod.common.pokemon.Pokemon;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.cobblemon.mod.common.client.render.RenderHelperKt.drawScaledText;
import static com.theendercore.terastal_expansion.misc.MixinKt.getTerastallizedType;

@Mixin(value = Summary.class, remap = false)
public abstract class SummaryMixin extends Screen {

    @Shadow
    public Pokemon selectedPokemon;

    protected SummaryMixin(Component title) {
        super(title);
    }


    @Inject(method = "render", at = @At("TAIL"))
    void x(GuiGraphics ctx, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        var x = (width - Summary.BASE_WIDTH) / 2;
        var y = (height - Summary.BASE_HEIGHT) / 2;
        var text = Component.literal("Tera : ");
        var text2 = Component.literal("Tera state : ");
        drawScaledText(
                ctx, text.append(this.selectedPokemon.getTeraType().getDisplayName()).getVisualOrderText(),
                x + 39, y + 148,
                1f, 1f, 1f, 0xffffff,
                true, true
        );
        drawScaledText(
                ctx, text2.append("" + getTerastallizedType(this.selectedPokemon)).getVisualOrderText(),
                x + 39, y + 158,
                1f, 1f, 1f, 0xffffff,
                true, true
        );
    }
}
