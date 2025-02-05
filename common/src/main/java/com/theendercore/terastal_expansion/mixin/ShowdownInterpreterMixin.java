package com.theendercore.terastal_expansion.mixin;

import com.cobblemon.mod.common.battles.ShowdownInterpreter;
import com.theendercore.terastal_expansion.TerastalConst;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Mixin(value = ShowdownInterpreter.class, remap = false)
public class ShowdownInterpreterMixin {

    @Inject(method = "interpretMessage", at = @At("HEAD"))
    void x(UUID battleId, String message, CallbackInfo ci){
        TerastalConst.log.info("Server msg: [{}] - {}", battleId, message);
    }
}
