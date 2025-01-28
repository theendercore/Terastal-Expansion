package com.theendercore.terastal_expansion.mixin;

import com.cobblemon.mod.common.api.reactive.SimpleObservable;
import com.cobblemon.mod.common.net.messages.client.PokemonUpdatePacket;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.theendercore.terastal_expansion.client.net.TeraStateUpdatePacket;
import com.theendercore.terastal_expansion.misc.TeraState;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = Pokemon.class, remap = false)
public abstract class PokemonMixin implements TeraState {
    @Shadow
    <T> SimpleObservable<T> registerObservable(SimpleObservable<T> par2, Function1<T, PokemonUpdatePacket<?>> par3) {
        return null;
    }
    @Unique
    @NotNull
    private boolean terastal_expansion$terraState = false;
    @Unique
    @NotNull
    private SimpleObservable<Boolean> _terastal_expansion$_terraState;
    @Inject(method = "<init>", at = @At("TAIL"))
    void x(CallbackInfo ci) {
        _terastal_expansion$_terraState = registerObservable(new SimpleObservable<>(), this::terastal_expansion$terraState);
    }
    @Unique
    private PokemonUpdatePacket<?> terastal_expansion$terraState(boolean x) {
        return new TeraStateUpdatePacket(() -> (Pokemon) (Object) this, x);
    }
    @Override
    public boolean getTeraState() {
        return terastal_expansion$terraState;
    }
    @Override
    public void setTeraState(boolean state) {
        terastal_expansion$terraState = state;
        _terastal_expansion$_terraState.emit(state);
    }
}
