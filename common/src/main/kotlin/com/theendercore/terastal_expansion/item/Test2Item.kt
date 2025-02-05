package com.theendercore.terastal_expansion.item

import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.cobblemon.mod.common.item.CobblemonItem
import com.mojang.serialization.JsonOps
import com.theendercore.terastal_expansion.misc.textL
import net.minecraft.nbt.CompoundTag
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack

class Test2Item : CobblemonItem(Properties()) {
    override fun interactLivingEntity(
        stack: ItemStack, player: Player, entity: LivingEntity, usedHand: InteractionHand
    ): InteractionResult {
        if (entity is PokemonEntity) {
//            val json = Pokemon.CODEC.encodeStart(JsonOps.INSTANCE, entity.pokemon).getOrThrow()
//            jsonToText(json).forEach(player::sendSystemMessage)

            val nbt = CompoundTag()
            entity.saveWithoutId(nbt)
            val json = CompoundTag.CODEC.encodeStart(JsonOps.INSTANCE, nbt).getOrThrow()
            if (player !is ServerPlayer) player.sendSystemMessage(textL(json.toString()))
            else player.sendSystemMessage(textL("[S]-$json"))
//            val pokemon = entity.pokemon
//            listOf(
//                "ShoulderedState : ${pokemon.state.name}",
//                "PersistentStatusContainer: ${pokemon.status}",
//                "Ball: ${pokemon.caughtBall.name}",
//                "Fainted Timer : ${pokemon.faintedTimer}",
//                "HealTimer : ${pokemon.healTimer}",
////                "Evo controler : ${pokemon.evelu}",
//                "Shiny : ${pokemon.shiny}",
//                "Nature : ${pokemon.nature.displayName}",
//                "Minted Nature : ${pokemon.mintedNature}",
//                "Held Item : ${pokemon.heldItem()}",
//                "PersistentData : ${pokemon.persistentData}",
//                "tetheringId : ${pokemon.tetheringId}",
//                "teraType : ${pokemon.teraType.displayName.string}",
//                "dmaxLevel : ${pokemon.dmaxLevel}",
//                "gmaxFactor : ${pokemon.gmaxFactor}",
//                "tradeable : ${pokemon.tradeable}",
//            ).forEach {
//                if (player !is ServerPlayer) player.sendSystemMessage(textL(it))
//                else player.sendSystemMessage(textL("[Server]-$it"))
//            }
//
//            println(" Hello ")
//            val x = pokemon.state
//            if (x is SentOutState) {
//                player.sendSystemMessage(textL("Test : ${x.entity?.pokemon?.nature?.displayName}"))
//            }


            return InteractionResult.SUCCESS_NO_ITEM_USED
        }
        return super.interactLivingEntity(stack, player, entity, usedHand)
    }

}