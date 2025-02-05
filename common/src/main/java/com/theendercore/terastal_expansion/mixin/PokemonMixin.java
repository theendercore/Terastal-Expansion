package com.theendercore.terastal_expansion.mixin;

import com.cobblemon.mod.common.api.reactive.SimpleObservable;
import com.cobblemon.mod.common.api.types.tera.TeraType;
import com.cobblemon.mod.common.net.messages.client.PokemonUpdatePacket;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.theendercore.terastal_expansion.client.net.HasTerastallizedStateUpdatePacket;
import com.theendercore.terastal_expansion.misc.HasTerastallizedType;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = Pokemon.class, remap = false)
public abstract class PokemonMixin implements HasTerastallizedType {

    @Shadow
    public <T> SimpleObservable<T> registerObservable(SimpleObservable<T> par2, Function1<T, PokemonUpdatePacket<?>> par3) {
        return null;
    }

    @Unique
    private TeraType terastal_expansion$terastallizedType = null;

    @Unique
    @NotNull
    private SimpleObservable<TeraType> _terastal_expansion$_terastallizedType;
    @Inject(method = "<init>", at = @At("TAIL"))
    void x(CallbackInfo ci) {
        _terastal_expansion$_terastallizedType = registerObservable(new SimpleObservable<>(), this::terastal_expansion$terraState);
    }

    @Unique
    private PokemonUpdatePacket<?> terastal_expansion$terraState(TeraType x) {
        return new HasTerastallizedStateUpdatePacket(() -> (Pokemon) (Object) this, x);
    }

    @Override
    public @Nullable TeraType terastal$getTerastallizedType() {
        return terastal_expansion$terastallizedType;
    }

    @Override
    public void terastal$setTerastallizedType(@Nullable TeraType state) {
        terastal_expansion$terastallizedType = state;
        _terastal_expansion$_terastallizedType.emit(state);
    }
}
