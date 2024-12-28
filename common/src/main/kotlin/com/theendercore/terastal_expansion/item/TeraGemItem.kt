package com.theendercore.terastal_expansion.item

import com.cobblemon.mod.common.CobblemonSounds
import com.cobblemon.mod.common.api.item.PokemonSelectingItem
import com.cobblemon.mod.common.api.types.tera.TeraType
import com.cobblemon.mod.common.item.CobblemonItem
import com.cobblemon.mod.common.pokemon.Pokemon
import com.cobblemon.mod.common.util.sendParticlesServer
import net.minecraft.core.particles.DustParticleOptions
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerLevel
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.phys.Vec3
import org.joml.Vector3f

class TeraGemItem(val type: TeraType) : CobblemonItem(Properties()), PokemonSelectingItem {


    override val bagItem = null
    override fun canUseOnPokemon(pokemon: Pokemon) = !pokemon.isBattleClone() && pokemon.teraType != type

    override fun applyToPokemon(player: ServerPlayer, stack: ItemStack, pokemon: Pokemon)
            : InteractionResultHolder<ItemStack> {
        if (pokemon.teraType == type) return InteractionResultHolder.pass(stack)
        pokemon.entity?.playSound(CobblemonSounds.EVOLUTION_NOTIFICATION, 1F, 1F)
        if (!player.isCreative) stack.shrink(1)
        pokemon.teraType = type
        val text = Component.translatable("Your cobblemons tera type changes to %s", pokemon.teraType.displayName)
        player.sendSystemMessage(text, true)
        pokemon.entity?.let {
            if (player.level() is ServerLevel) player.level().sendParticlesServer(
                DustParticleOptions(Vector3f(1f, 1f, 1f), 1f),
                it.position(),
                45,
                Vec3(it.bbWidth / 2.0, it.eyeHeight / 2.0, it.bbWidth / 2.0),
                4.0
            )
        }
        return InteractionResultHolder.success(stack)
    }

    override fun use(world: Level, user: Player, hand: InteractionHand): InteractionResultHolder<ItemStack> {
        return if (user is ServerPlayer) use(user, user.getItemInHand(hand))
        else InteractionResultHolder.success(user.getItemInHand(hand))
    }
}