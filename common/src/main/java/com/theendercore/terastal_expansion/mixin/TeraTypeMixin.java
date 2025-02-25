package com.theendercore.terastal_expansion.mixin;

import com.cobblemon.mod.common.api.types.tera.elemental.ElementalTypeTeraType;
import com.cobblemon.mod.common.api.types.tera.gimmick.StellarTeraType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin({ElementalTypeTeraType.class, StellarTeraType.class})
public class TeraTypeMixin {

    @Inject(method = "showdownId", at = @At("RETURN"), remap = false, cancellable = true)
    void x(CallbackInfoReturnable<String> cir) {
        var x = cir.getReturnValue();
        cir.setReturnValue((x.charAt(0) + "").toUpperCase() + x.substring(1));
    }
}
