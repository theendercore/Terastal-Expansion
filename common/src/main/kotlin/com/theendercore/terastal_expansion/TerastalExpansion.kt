package com.theendercore.terastal_expansion

import com.cobblemon.mod.common.api.events.CobblemonEvents.POKEMON_SENT_POST
import com.cobblemon.mod.common.net.messages.client.pokemon.update.TeraTypeUpdatePacket
import com.theendercore.terastal_expansion.misc.TerastalImplementation

object TerastalExpansion {
    @JvmStatic
    fun init(tera: TerastalImplementation) {
        tera.registerBlocks()
        tera.registerItems()
    }

    fun events() {
        POKEMON_SENT_POST.subscribe {
            it.pokemon.notify(TeraTypeUpdatePacket({ println("updating pokemon: ${it.pokemon.teraType.id}");it.pokemon }, it.pokemon.teraType))
        }
    }
}