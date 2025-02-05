package com.theendercore.terastal_expansion

import com.cobblemon.mod.common.api.events.CobblemonEvents.BATTLE_FAINTED
import com.cobblemon.mod.common.api.events.CobblemonEvents.BATTLE_FLED
import com.cobblemon.mod.common.api.events.CobblemonEvents.BATTLE_VICTORY
import com.cobblemon.mod.common.api.events.CobblemonEvents.TERASTALLIZATION
import com.theendercore.terastal_expansion.misc.TerastalImplementation
import com.theendercore.terastal_expansion.misc.clearTerastallizedType
import com.theendercore.terastal_expansion.misc.setTerastallizedType

object TerastalExpansion {
    @JvmStatic
    fun init(tera: TerastalImplementation) {
        tera.registerBlocks()
        tera.registerItems()
    }

    fun events() {
        TERASTALLIZATION.subscribe { it.pokemon.originalPokemon.setTerastallizedType(it.teraType) }
        BATTLE_FLED.subscribe { event -> event.player.pokemonList.forEach { it.originalPokemon.clearTerastallizedType() } }
        BATTLE_FAINTED.subscribe { it.killed.originalPokemon.clearTerastallizedType() }
        BATTLE_VICTORY.subscribe { event ->
            event.losers.forEach { actor -> actor.pokemonList.forEach { it.originalPokemon.clearTerastallizedType() } }
            event.winners.forEach { actor -> actor.pokemonList.forEach { it.originalPokemon.clearTerastallizedType() } }
        }
    }
}