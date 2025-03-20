package com.theendercore.terastal_expansion.mixin;

import com.cobblemon.mod.common.battles.ShowdownActionRequest;
import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import com.theendercore.terastal_expansion.item.TeraOrbItem;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import static com.cobblemon.mod.common.util.MiscUtilsKt.cobblemonResource;
import static com.cobblemon.mod.common.util.PlayerExtensionsKt.hasKeyItem;
import static com.theendercore.terastal_expansion.item.TeraOrbItem.canUse;

@Mixin(value = ShowdownActionRequest.class, remap = false)
public abstract class ShowdownActionRequestMixin {
    @ModifyExpressionValue(method = "sanitize", at = @At(value = "INVOKE", target = "Lcom/cobblemon/mod/common/util/PlayerExtensionsKt;hasKeyItem(Lnet/minecraft/server/level/ServerPlayer;Lnet/minecraft/resources/ResourceLocation;)Z"))
    boolean canUseOrbBattleCheck(boolean original, @Local ServerPlayer player, @Local ResourceLocation key) {
        var canUseOrb = player.getInventory().items.stream()
                .anyMatch(item -> item.getItem() instanceof TeraOrbItem && canUse(item));
        return hasKeyItem(player, key) || (key.equals(cobblemonResource("tera_orb")) && canUseOrb);
    }
}
