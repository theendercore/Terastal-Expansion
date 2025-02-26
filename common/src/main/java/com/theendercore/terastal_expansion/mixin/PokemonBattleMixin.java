package com.theendercore.terastal_expansion.mixin;

import com.cobblemon.mod.common.api.battles.model.PokemonBattle;
import com.cobblemon.mod.common.api.battles.model.actor.BattleActor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.theendercore.terastal_expansion.misc.MixinKt.setTerastallizedType;


@Mixin(value = PokemonBattle.class, remap = false)
public class PokemonBattleMixin {

    @Inject(method = "end", at = @At(value = "INVOKE", target = "Lcom/cobblemon/mod/common/api/battles/model/PokemonBattle;sendUpdate(Lcom/cobblemon/mod/common/api/net/NetworkPacket;)V"))
    void removeTeraOnBattleEnd(CallbackInfo ci) {
        this.getActors().forEach(actor -> actor.getPokemonList().forEach(poke -> setTerastallizedType(poke.getOriginalPokemon(), null)));
    }

    @Shadow
    public Iterable<BattleActor> getActors() {
        return null;
    }
}
