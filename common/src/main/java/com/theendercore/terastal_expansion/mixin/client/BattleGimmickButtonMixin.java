package com.theendercore.terastal_expansion.mixin.client;

import com.cobblemon.mod.common.battles.ShowdownMoveset;
import com.cobblemon.mod.common.client.gui.battle.subscreen.BattleGimmickButton;
import com.cobblemon.mod.common.client.gui.battle.subscreen.BattleMoveSelection;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.theendercore.terastal_expansion.client.ui.TerastalButton;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = BattleGimmickButton.Companion.class, remap = false)
public class BattleGimmickButtonMixin {
    @ModifyReturnValue(method = "create", at = @At("RETURN"))
    BattleGimmickButton addCustomTeraButton(BattleGimmickButton original, ShowdownMoveset.Gimmick gimmick, BattleMoveSelection moveSelection, float x, float y) {
        return (gimmick == ShowdownMoveset.Gimmick.TERASTALLIZATION) ? new TerastalButton(moveSelection, x, y) : original;
    }
}
