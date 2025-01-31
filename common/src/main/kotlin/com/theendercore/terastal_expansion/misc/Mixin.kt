package com.theendercore.terastal_expansion.misc

import com.cobblemon.mod.common.pokemon.Pokemon


fun Pokemon.getTeraState(): Boolean = (this as TeraState).getTeraState()
fun Pokemon.setTeraState(state: Boolean) = (this as TeraState).setTeraState(state)

