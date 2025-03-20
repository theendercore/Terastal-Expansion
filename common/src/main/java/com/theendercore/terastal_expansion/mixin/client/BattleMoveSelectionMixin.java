package com.theendercore.terastal_expansion.mixin.client;

import com.cobblemon.mod.common.client.battle.SingleActionRequest;
import com.cobblemon.mod.common.client.gui.battle.BattleGUI;
import com.cobblemon.mod.common.client.gui.battle.subscreen.BattleActionSelection;
import com.cobblemon.mod.common.client.gui.battle.subscreen.BattleMoveSelection;
import com.cobblemon.mod.common.client.gui.battle.subscreen.BattleShiftButton;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.network.chat.MutableComponent;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = BattleMoveSelection.class, remap = false)
public abstract class BattleMoveSelectionMixin extends BattleActionSelection {
    public BattleMoveSelectionMixin(@NotNull BattleGUI battleGUI, @NotNull SingleActionRequest request, int x, int y, int width, int height, @NotNull MutableComponent name) {
        super(battleGUI, request, x, y, width, height, name);
    }

    @Final
    @Shadow
    private BattleShiftButton shiftButton;


    @ModifyExpressionValue(method = "mousePrimaryClicked", at = @At(value = "INVOKE", target = "Lcom/cobblemon/mod/common/client/gui/battle/subscreen/BattleShiftButton;isHovered(DD)Z"))
    boolean fixShiftBug(boolean original, double mouseX, double mouseY) {
        var ap = this.getRequest().getActivePokemon();
        return shiftButton.isHovered(mouseX, mouseY) && ap.getFormat().getBattleType().getSlotsPerActor() == 3 && (ap.getPNX().charAt(2) == 'a' || ap.getPNX().charAt(2) == 'c');
    }
}
