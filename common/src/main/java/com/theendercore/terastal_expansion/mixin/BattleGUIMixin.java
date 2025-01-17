package com.theendercore.terastal_expansion.mixin;

import com.cobblemon.mod.common.client.gui.battle.BattleGUI;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = BattleGUI.class, remap = false)
public abstract class BattleGUIMixin {


    @Inject(method = "init", at=@At("TAIL"))
    void x(CallbackInfo ci){

    }
}
