package com.theendercore.terastal_expansion.mixin.client;

import com.cobblemon.mod.common.battles.ShowdownMoveset;
import com.cobblemon.mod.common.client.gui.battle.subscreen.BattleGimmickButton;
import com.cobblemon.mod.common.client.gui.battle.subscreen.BattleMoveSelection;
import com.theendercore.terastal_expansion.client.ui.TerastalButton;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = BattleGimmickButton.Companion.class, remap = false)
public class BattleGimmickButtonMixin {


    @Inject(method = "create", at = @At("RETURN"), cancellable = true)
    void x(ShowdownMoveset.Gimmick gimmick, BattleMoveSelection moveSelection, float x, float y, CallbackInfoReturnable<BattleGimmickButton> cir) {
        if(gimmick == ShowdownMoveset.Gimmick.TERASTALLIZATION) cir.setReturnValue(new TerastalButton(moveSelection, x, y));
    }
}
