package com.theendercore.terastal_expansion.mixin;

import com.cobblemon.mod.common.api.battles.model.PokemonBattle;
import com.cobblemon.mod.common.battles.runner.graal.GraalShowdownService;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

import static com.theendercore.terastal_expansion.misc.CustomLogKt.addLog;

@Mixin(value = GraalShowdownService.class, remap = false)
public class GraalShowdownServiceMixin {
    @Inject(method = "startBattle", at = @At("HEAD"))
    void dumpStartBattle(PokemonBattle battle, String[] messages, CallbackInfo ci) {
        addLog((Object[]) messages);
    }

    @Inject(method = "send", at = @At("HEAD"))
    void dumpSendMessage(UUID battleId, String[] messages, CallbackInfo ci){
        addLog((Object[]) messages);
    }
}
