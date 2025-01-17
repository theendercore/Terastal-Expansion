package com.theendercore.terastal_expansion.mixin;

import com.cobblemon.mod.common.battles.ShowdownActionRequest;
import com.cobblemon.mod.common.battles.ShowdownMoveset;
import com.cobblemon.mod.common.client.battle.ActiveClientBattlePokemon;
import com.cobblemon.mod.common.client.battle.SingleActionRequest;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Debug(export = true)
@Mixin(value = SingleActionRequest.Companion.class, remap = false)
public abstract class SingleActionRequestMixin {


    @Inject(method = "composeFrom$lambda$0", at = @At(value = "RETURN"), cancellable = true)
    private static void darkMagic(ShowdownActionRequest request, ActiveClientBattlePokemon targetable, ShowdownMoveset moveSet, boolean forceSwitch, CallbackInfoReturnable<SingleActionRequest> cir) {
        var newMoves = moveSet;
        if (moveSet.getGimmicks().isEmpty()){
            newMoves.setCanTerastallize(String.valueOf(true));
        }
        cir.setReturnValue(new SingleActionRequest(targetable, request.getSide(), newMoves, forceSwitch, !request.getNoCancel()));
    }
}
