package com.theendercore.terastal_expansion.item

import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.cobblemon.mod.common.item.CobblemonItem
import com.theendercore.terastal_expansion.misc.getTerastallizedType
import com.theendercore.terastal_expansion.misc.makeText
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack

class TestItem : CobblemonItem(Properties()) {
    override fun interactLivingEntity(
        stack: ItemStack, player: Player, entity: LivingEntity, usedHand: InteractionHand
    ): InteractionResult {
        if (player !is ServerPlayer) return InteractionResult.PASS
        if (entity is PokemonEntity) {
            val pokemon = entity.pokemon
            player.sendSystemMessage(makeText("TeraType : ").append(pokemon.teraType.displayName))
            player.sendSystemMessage(makeText("TeraState: " + pokemon.getTerastallizedType()))
            player.sendSystemMessage(makeText("Item: " + pokemon.heldItem()))
            return InteractionResult.SUCCESS_NO_ITEM_USED
        }
        return super.interactLivingEntity(stack, player, entity, usedHand)
    }
}