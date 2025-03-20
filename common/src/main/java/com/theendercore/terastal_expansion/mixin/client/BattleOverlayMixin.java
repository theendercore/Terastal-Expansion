package com.theendercore.terastal_expansion.mixin.client;

import com.cobblemon.mod.common.api.pokedex.PokedexEntryProgress;
import com.cobblemon.mod.common.client.battle.ActiveClientBattlePokemon;
import com.cobblemon.mod.common.client.gui.battle.BattleOverlay;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Debug(export = true)
@Mixin(value = BattleOverlay.class, remap = false)
public abstract class BattleOverlayMixin {

    @Inject(method = "drawTile", at = @At("TAIL"))
    void renderUI(GuiGraphics ctx, float tickDelta, ActiveClientBattlePokemon pokemon, boolean left, int rank, PokedexEntryProgress dexState, boolean hasCommand, boolean isHovered, boolean isCompact,
                  CallbackInfo ci, @Local(ordinal = 1) float x, @Local(ordinal = 3) int y) {


//        battleOverlayIcon(ctx, pokemon, (int) x, y, left, isCompact);
    }
}
