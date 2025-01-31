package com.theendercore.terastal_expansion.mixin.client;

import com.cobblemon.mod.common.client.battle.SingleActionRequest;
import com.cobblemon.mod.common.client.gui.battle.BattleGUI;
import com.cobblemon.mod.common.client.gui.battle.subscreen.BattleActionSelection;
import com.cobblemon.mod.common.client.gui.battle.subscreen.BattleMoveSelection;
import com.cobblemon.mod.common.client.gui.battle.subscreen.BattleShiftButton;
import net.minecraft.network.chat.MutableComponent;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(value = BattleMoveSelection.class, remap = false)
public abstract class BattleMoveSelectionMixin extends BattleActionSelection {
    public BattleMoveSelectionMixin(@NotNull BattleGUI battleGUI, @NotNull SingleActionRequest request, int x, int y, int width, int height, @NotNull MutableComponent name) {
        super(battleGUI, request, x, y, width, height, name);
    }

    @Redirect(method = "mousePrimaryClicked", at = @At(value = "INVOKE", target = "Lcom/cobblemon/mod/common/client/gui/battle/subscreen/BattleShiftButton;isHovered(DD)Z"))
    boolean x(BattleShiftButton instance, double mouseX, double mouseY) {
        var ap = this.getRequest().getActivePokemon();
        return instance.isHovered(mouseX, mouseY) && ap.getFormat().getBattleType().getSlotsPerActor() == 3 && (ap.getPNX().charAt(2) == 'a' || ap.getPNX().charAt(2) == 'c');
    }
}
