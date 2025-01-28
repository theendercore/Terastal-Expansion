package com.theendercore.terastal_expansion

import com.cobblemon.mod.common.api.events.CobblemonEvents.POKEMON_SENT_PRE
import com.theendercore.terastal_expansion.TerastalConst.log
import com.theendercore.terastal_expansion.misc.TerastalImplementation
import com.theendercore.terastal_expansion.misc.getTeraState
import com.theendercore.terastal_expansion.misc.setTeraState

object TerastalExpansion {
    @JvmStatic
    fun init(tera: TerastalImplementation) {
        tera.registerBlocks()
        tera.registerItems()
    }

    fun events() {
        POKEMON_SENT_PRE.subscribe {
            if (!it.pokemon.getTeraState()) it.pokemon.setTeraState(true)
            else log.info("Pokemon is already In The Tera State")
        }
//        POKEMON_SENT_POST.subscribe { }
    }
}