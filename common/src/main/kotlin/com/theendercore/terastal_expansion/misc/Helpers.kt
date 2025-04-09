package com.theendercore.terastal_expansion.misc

import com.cobblemon.mod.common.api.types.ElementalTypes
import com.cobblemon.mod.common.api.types.tera.TeraType
import com.cobblemon.mod.common.api.types.tera.TeraTypes
import com.cobblemon.mod.common.api.types.tera.elemental.ElementalTypeTeraType
import com.cobblemon.mod.common.api.types.tera.gimmick.StellarTeraType
import com.cobblemon.mod.common.pokemon.Pokemon
import com.cobblemon.mod.common.util.math.toRGB
import com.theendercore.terastal_expansion.init.TerastalItems
import com.theendercore.terastal_expansion.init.TerastalParticles
import net.minecraft.core.particles.ColorParticleOption
import net.minecraft.network.chat.Component

fun text(key: String, vararg args: Any) = Component.translatable(key, *args)

fun TeraType.getParticle(): ColorParticleOption = when (this) {
    is ElementalTypeTeraType -> type.hue.toRGB()
    is StellarTeraType -> ElementalTypes.all().random().hue.toRGB()
    else -> Triple(0f, 0f, 0f)
}.let { particle(it.first, it.second, it.third) }

val hatMap = mapOf(
    TeraTypes.NORMAL to TerastalItems.NORMAL_TERA_HAT.defaultInstance,
    TeraTypes.FIRE to TerastalItems.FIRE_TERA_HAT.defaultInstance,
    TeraTypes.WATER to TerastalItems.WATER_TERA_HAT.defaultInstance,
    TeraTypes.ELECTRIC to TerastalItems.ELECTRIC_TERA_HAT.defaultInstance,
    TeraTypes.GRASS to TerastalItems.GRASS_TERA_HAT.defaultInstance,
    TeraTypes.ICE to TerastalItems.ICE_TERA_HAT.defaultInstance,
    TeraTypes.FIGHTING to TerastalItems.FIGHTING_TERA_HAT.defaultInstance,
    TeraTypes.POISON to TerastalItems.POISON_TERA_HAT.defaultInstance,
    TeraTypes.GROUND to TerastalItems.GROUND_TERA_HAT.defaultInstance,
    TeraTypes.FLYING to TerastalItems.FLYING_TERA_HAT.defaultInstance,
    TeraTypes.PSYCHIC to TerastalItems.PSYCHIC_TERA_HAT.defaultInstance,
    TeraTypes.BUG to TerastalItems.BUG_TERA_HAT.defaultInstance,
    TeraTypes.ROCK to TerastalItems.ROCK_TERA_HAT.defaultInstance,
    TeraTypes.GHOST to TerastalItems.GHOST_TERA_HAT.defaultInstance,
    TeraTypes.DRAGON to TerastalItems.DRAGON_TERA_HAT.defaultInstance,
    TeraTypes.DARK to TerastalItems.DARK_TERA_HAT.defaultInstance,
    TeraTypes.STEEL to TerastalItems.STEEL_TERA_HAT.defaultInstance,
    TeraTypes.FAIRY to TerastalItems.FAIRY_TERA_HAT.defaultInstance,
    TeraTypes.STELLAR to TerastalItems.STELLAR_TERA_HAT.defaultInstance,
)

fun TeraType.getHat() = hatMap[this] ?: error("Unknown tera type! $this")

fun particle(red: Number, green: Number, blue: Number) =
    ColorParticleOption.create(TerastalParticles.TERASTAL_PARTICLE_TYPE, red.toFloat(), green.toFloat(), blue.toFloat())

// Mixin
fun Pokemon.getTerastallizedType(): TeraType? =
    (this as HasTerastallizedType).`terastal_expansion$getTerastallizedType`()

fun Pokemon.setTerastallizedType(state: TeraType?) =
    (this as HasTerastallizedType).`terastal_expansion$setTerastallizedType`(state)

fun Pokemon.clearTerastallizedType() = this.setTerastallizedType(null)

