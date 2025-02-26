package com.theendercore.terastal_expansion

import com.cobblemon.mod.common.api.events.CobblemonEvents.BATTLE_FAINTED
import com.cobblemon.mod.common.api.events.CobblemonEvents.BATTLE_FLED
import com.cobblemon.mod.common.api.events.CobblemonEvents.BATTLE_VICTORY
import com.cobblemon.mod.common.api.events.CobblemonEvents.POKEMON_SENT_POST
import com.cobblemon.mod.common.api.events.CobblemonEvents.TERASTALLIZATION
import com.theendercore.terastal_expansion.TerastalConst.log
import com.theendercore.terastal_expansion.config.TeraConfigObj
import com.theendercore.terastal_expansion.data.TerastalWorldGen
import com.theendercore.terastal_expansion.item.TeraOrbItem
import com.theendercore.terastal_expansion.item.TeraOrbItem.Companion.refill
import com.theendercore.terastal_expansion.item.TeraOrbItem.Companion.useCharge
import com.theendercore.terastal_expansion.misc.TerastalImplementation
import com.theendercore.terastal_expansion.misc.clearTerastallizedType
import com.theendercore.terastal_expansion.misc.getTerastallizedType
import com.theendercore.terastal_expansion.misc.setTerastallizedType
import net.minecraft.world.entity.player.Player

object TerastalExpansion {
    lateinit var implementation: TerastalImplementation

    @JvmStatic
    fun init(tera: TerastalImplementation) {
        implementation = tera
        tera.registerBlocks()
        tera.registerItems()
        tera.registerDataComponents()
        TeraConfigObj.loadConfig()

        TerastalWorldGen.register()
    }

    fun events() {
        POKEMON_SENT_POST.subscribe {
            it.pokemonEntity.pokemon.teraType = it.pokemon.teraType
            it.pokemonEntity.pokemon.setTerastallizedType(if (it.pokemonEntity.isBattling) it.pokemon.getTerastallizedType() else null)
        }
        TERASTALLIZATION.subscribe { event ->
            event.pokemon.originalPokemon.setTerastallizedType(event.teraType)
            event.pokemon.originalPokemon.getOwnerPlayer()?.let { player ->
                player.inventory.items.find { it.item is TeraOrbItem }?.let { player.useCharge(it) }?: log.info("Failed")
            }
        }
        BATTLE_FLED.subscribe { event -> event.player.pokemonList.forEach { it.originalPokemon.clearTerastallizedType() } }
        BATTLE_FAINTED.subscribe { it.killed.originalPokemon.clearTerastallizedType() }
        BATTLE_VICTORY.subscribe { event ->
            event.losers.forEach { act -> act.pokemonList.forEach { it.originalPokemon.clearTerastallizedType() } }
            event.winners.forEach { act -> act.pokemonList.forEach { it.originalPokemon.clearTerastallizedType() } }
        }
    }

    @JvmStatic
    fun refillTeraOrb(player: Player) = player.inventory.items.forEach { if (it.item is TeraOrbItem) it.refill() }
}