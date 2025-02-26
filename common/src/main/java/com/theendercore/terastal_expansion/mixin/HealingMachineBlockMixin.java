package com.theendercore.terastal_expansion.mixin;

import com.cobblemon.mod.common.block.HealingMachineBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.theendercore.terastal_expansion.TerastalExpansion.refillTeraOrb;

@Mixin(HealingMachineBlock.class)
public class HealingMachineBlockMixin {

    @Inject(method = "useWithoutItem", at = @At("RETURN"))
    void restoreTeraOrbCharge(BlockState blockState, Level world, BlockPos blockPos, Player player, BlockHitResult hit, CallbackInfoReturnable<InteractionResult> cir){
        if (!world.isClientSide) refillTeraOrb(player);
    }
}
