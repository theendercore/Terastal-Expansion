package com.theendercore.terastal_expansion.item

import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.cobblemon.mod.common.item.CobblemonItem
import com.theendercore.terastal_expansion.misc.getTerastallizedType
import com.theendercore.terastal_expansion.misc.setTerastallizedType
import com.theendercore.terastal_expansion.misc.text
import net.minecraft.ChatFormatting
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag

class TerastallizerItem : CobblemonItem(Properties()) {
    override fun interactLivingEntity(
        stack: ItemStack, player: Player, entity: LivingEntity, usedHand: InteractionHand,
    ): InteractionResult {
        if (player !is ServerPlayer) return InteractionResult.PASS
        if (entity is PokemonEntity) entity.pokemon.setTerastallizedType(
            if (entity.pokemon.getTerastallizedType() != null) null else entity.pokemon.teraType
        )
        return super.interactLivingEntity(stack, player, entity, usedHand)
    }

    override fun appendHoverText(stack: ItemStack, c: TooltipContext, list: MutableList<Component>, flag: TooltipFlag) {
        super.appendHoverText(stack, c, list, flag)
        list.addLast(text("Debug Item!").withStyle(ChatFormatting.RED))
    }
}