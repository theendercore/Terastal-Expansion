package com.theendercore.terastal_expansion.misc

import com.cobblemon.mod.common.api.types.tera.TeraType
import com.cobblemon.mod.common.api.types.tera.TeraTypes
import com.cobblemon.mod.common.pokemon.Pokemon
import java.awt.Color

val colorMap = mapOf(
    TeraTypes.NORMAL to Color.WHITE,
    TeraTypes.FIRE to Color.RED,
    TeraTypes.WATER to Color.BLUE,
    TeraTypes.GRASS to Color.GREEN,
    TeraTypes.ELECTRIC to Color.YELLOW,
    TeraTypes.ICE to Color.CYAN,
    TeraTypes.FIGHTING to Color.ORANGE,
    TeraTypes.POISON to Color.MAGENTA,
    TeraTypes.GROUND to Color.DARK_GRAY,
    TeraTypes.FLYING to Color.PINK,
    TeraTypes.PSYCHIC to Color.LIGHT_GRAY,
    TeraTypes.BUG to Color.GREEN,
    TeraTypes.ROCK to Color.GRAY,
    TeraTypes.GHOST to Color.DARK_GRAY,
    TeraTypes.DRAGON to Color.MAGENTA,
    TeraTypes.DARK to Color.BLACK,
    TeraTypes.STEEL to Color.LIGHT_GRAY,
    TeraTypes.FAIRY to Color.PINK,
    TeraTypes.STELLAR to Color.BLUE
)

fun TeraType.getTeraTypeColor(): Color = colorMap[this] ?: Color.BLUE
fun print(any: Any?) = println(any)


fun Pokemon.getTeraState(): Boolean = (this as TeraState).getTeraState()
fun Pokemon.setTeraState(state: Boolean) = (this as TeraState).setTeraState(state)


