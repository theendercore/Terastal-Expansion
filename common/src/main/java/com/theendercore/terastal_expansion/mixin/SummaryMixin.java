package com.theendercore.terastal_expansion.mixin;

import com.cobblemon.mod.common.client.gui.summary.Summary;
import com.cobblemon.mod.common.pokemon.Pokemon;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.cobblemon.mod.common.client.render.RenderHelperKt.drawScaledText;

@Mixin(value = Summary.class, remap = false)
public abstract class SummaryMixin extends Screen {

    @Shadow
    public Pokemon selectedPokemon;

    protected SummaryMixin(Text title) {
        super(title);
    }

    @Inject(method = "render", at = @At("TAIL"))
    void x(DrawContext ctx, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        var x = (width - Summary.BASE_WIDTH) / 2;
        var y = (height - Summary.BASE_HEIGHT) / 2;
        drawScaledText(
                ctx, this.selectedPokemon.getTeraType().getDisplayName().asOrderedText(),
                x + 60, y - 10,
                1f, 1f, 1f, 0xffffff,
                true, true
        );
    }
}
