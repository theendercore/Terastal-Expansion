package com.theendercore.terastal_expansion.mixin;

import com.cobblemon.mod.common.api.types.tera.elemental.ElementalTypeTeraType;
import com.cobblemon.mod.common.api.types.tera.gimmick.StellarTeraType;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = {ElementalTypeTeraType.class, StellarTeraType.class}, remap = false)
public class TeraTypeMixin {
    @ModifyReturnValue(method = "showdownId", at = @At("RETURN"))
    String fixShowdownId(String id) {
        return (id.charAt(0) + "").toUpperCase() + id.substring(1);
    }
}
