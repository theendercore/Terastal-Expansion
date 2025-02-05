package com.theendercore.terastal_expansion.mixin;

import com.cobblemon.mod.common.api.battles.model.PokemonBattle;
import com.theendercore.terastal_expansion.TerastalConst;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;

@Mixin(value = PokemonBattle.class, remap = false)
public class PokemonBattleMixin {

    @Inject(method = "writeShowdownAction", at = @At("HEAD"))
    void x(String[] messages, CallbackInfo ci) {
        Arrays.stream(messages).forEach(TerastalConst.log::info);
    }
}
