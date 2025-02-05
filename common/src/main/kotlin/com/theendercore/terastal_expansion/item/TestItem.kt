package com.theendercore.terastal_expansion.item

import com.cobblemon.mod.common.api.item.PokemonSelectingItem
import com.cobblemon.mod.common.item.CobblemonItem
import com.cobblemon.mod.common.pokemon.Pokemon
import com.theendercore.terastal_expansion.misc.getTerastallizedType
import com.theendercore.terastal_expansion.misc.makeText
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level

class TestItem : CobblemonItem(Properties()), PokemonSelectingItem {
    override val bagItem = null
    override fun canUseOnPokemon(pokemon: Pokemon) = true

    override fun applyToPokemon(player: ServerPlayer, stack: ItemStack, pokemon: Pokemon)
            : InteractionResultHolder<ItemStack> {
        player.sendSystemMessage(makeText("TeraType : ").append(pokemon.teraType.displayName))
        player.sendSystemMessage(makeText("TeraState: " + pokemon.getTerastallizedType()))

        return InteractionResultHolder.success(stack)
    }

    override fun use(world: Level, user: Player, hand: InteractionHand): InteractionResultHolder<ItemStack> {
        return if (user is ServerPlayer) use(user, user.getItemInHand(hand))
        else InteractionResultHolder.success(user.getItemInHand(hand))
    }
}