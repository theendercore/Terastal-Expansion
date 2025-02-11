package com.theendercore.terastal_expansion

import com.cobblemon.mod.common.api.events.CobblemonEvents.BATTLE_FAINTED
import com.cobblemon.mod.common.api.events.CobblemonEvents.BATTLE_FLED
import com.cobblemon.mod.common.api.events.CobblemonEvents.BATTLE_VICTORY
import com.cobblemon.mod.common.api.events.CobblemonEvents.POKEMON_SENT_POST
import com.cobblemon.mod.common.api.events.CobblemonEvents.TERASTALLIZATION
import com.theendercore.terastal_expansion.data.TerastalWorldGen
import com.theendercore.terastal_expansion.misc.TerastalImplementation
import com.theendercore.terastal_expansion.misc.clearTerastallizedType
import com.theendercore.terastal_expansion.misc.getTerastallizedType
import com.theendercore.terastal_expansion.misc.setTerastallizedType

object TerastalExpansion {
    lateinit var implementation: TerastalImplementation
    @JvmStatic
    fun init(tera: TerastalImplementation) {
        implementation = tera
        tera.registerBlocks()
        tera.registerItems()

        TerastalWorldGen.register()
    }

    fun events() {
        POKEMON_SENT_POST.subscribe {
            it.pokemonEntity.pokemon.teraType = it.pokemon.teraType
            it.pokemonEntity.pokemon.setTerastallizedType(it.pokemon.getTerastallizedType())
        }

        TERASTALLIZATION.subscribe { it.pokemon.originalPokemon.setTerastallizedType(it.teraType) }
        BATTLE_FLED.subscribe { event -> event.player.pokemonList.forEach { it.originalPokemon.clearTerastallizedType() } }
        BATTLE_FAINTED.subscribe { it.killed.originalPokemon.clearTerastallizedType() }
        BATTLE_VICTORY.subscribe { event ->
            event.losers.forEach { act -> act.pokemonList.forEach { it.originalPokemon.clearTerastallizedType() } }
            event.winners.forEach { act -> act.pokemonList.forEach { it.originalPokemon.clearTerastallizedType() } }
        }
    }


}