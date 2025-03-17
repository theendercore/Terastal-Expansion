package com.theendercore.terastal_expansion.item

import com.cobblemon.mod.common.api.item.PokemonSelectingItem
import com.cobblemon.mod.common.api.types.tera.TeraType
import com.cobblemon.mod.common.item.CobblemonItem
import com.cobblemon.mod.common.pokemon.Pokemon
import com.cobblemon.mod.common.util.sendParticlesServer
import com.theendercore.terastal_expansion.misc.getParticle
import com.theendercore.terastal_expansion.misc.text
import net.minecraft.server.level.ServerLevel
import net.minecraft.server.level.ServerPlayer
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.phys.Vec3

class TeraGemItem(val type: TeraType) : CobblemonItem(Properties()), PokemonSelectingItem {
    override val bagItem = null
    override fun canUseOnPokemon(pokemon: Pokemon) = !pokemon.isBattleClone() && pokemon.teraType != type
    override fun applyToPokemon(player: ServerPlayer, stack: ItemStack, pokemon: Pokemon)
            : InteractionResultHolder<ItemStack> {
        if (pokemon.teraType == type) return InteractionResultHolder.fail(stack)
        pokemon.entity?.playSound(SoundEvents.AMETHYST_BLOCK_CHIME, 10F, 10F)
        if (!player.isCreative) stack.shrink(1)
        pokemon.teraType = type
        player.sendSystemMessage(
            text("Your Pokemon's Tera Type has changed to %s!", pokemon.teraType.displayName),
            true
        )
        pokemon.entity?.let {
            if (player.level() is ServerLevel) repeat(35) { _ ->
                player.level().sendParticlesServer(
                    type.getParticle(), it.position(), 1,
                    Vec3(it.bbWidth / 1.6, it.eyeHeight / 2.0, it.bbWidth / 1.6),
                    4.0
                )
            }
        }
        return InteractionResultHolder.success(stack)
    }

    override fun use(world: Level, user: Player, hand: InteractionHand): InteractionResultHolder<ItemStack> {
        return if (user is ServerPlayer) use(user, user.getItemInHand(hand))
        else InteractionResultHolder.pass(user.getItemInHand(hand))
    }
}