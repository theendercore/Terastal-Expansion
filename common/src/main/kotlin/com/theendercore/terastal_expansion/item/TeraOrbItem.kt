package com.theendercore.terastal_expansion.item

import com.cobblemon.mod.common.CobblemonSounds
import com.cobblemon.mod.common.api.item.PokemonSelectingItem
import com.cobblemon.mod.common.item.CobblemonItem
import com.cobblemon.mod.common.pokemon.Pokemon
import com.theendercore.terastal_expansion.client.net.TeraStateUpdatePacket
import com.theendercore.terastal_expansion.misc.getTeraState
import com.theendercore.terastal_expansion.misc.setTeraState
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level

class TeraOrbItem : CobblemonItem(Properties().stacksTo(1)), PokemonSelectingItem {
    override val bagItem = null
    override fun canUseOnPokemon(pokemon: Pokemon) = true

    override fun applyToPokemon(player: ServerPlayer, stack: ItemStack, pokemon: Pokemon)
            : InteractionResultHolder<ItemStack> {
        pokemon.entity?.playSound(CobblemonSounds.FISHING_ROD_CAST, 1F, 1F)
        val state = !pokemon.getTeraState()
        pokemon.setTeraState(state)
        pokemon.notify(TeraStateUpdatePacket({ pokemon }, state))
        return InteractionResultHolder.success(stack)
    }

    override fun use(world: Level, user: Player, hand: InteractionHand): InteractionResultHolder<ItemStack> {
        return if (user is ServerPlayer) use(user, user.getItemInHand(hand))
        else InteractionResultHolder.success(user.getItemInHand(hand))
    }
}
