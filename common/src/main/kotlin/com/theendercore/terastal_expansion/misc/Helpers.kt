package com.theendercore.terastal_expansion.misc

import com.cobblemon.mod.common.api.types.ElementalTypes
import com.cobblemon.mod.common.api.types.tera.TeraType
import com.cobblemon.mod.common.api.types.tera.elemental.ElementalTypeTeraType
import com.cobblemon.mod.common.api.types.tera.gimmick.StellarTeraType
import com.cobblemon.mod.common.pokemon.Pokemon
import com.cobblemon.mod.common.util.math.toRGB
import com.theendercore.terastal_expansion.init.TerastalParticles
import net.minecraft.core.particles.ColorParticleOption
import net.minecraft.network.chat.Component

fun text(key: String, vararg args: Any) = Component.translatable(key, *args)

fun TeraType.getParticle(): ColorParticleOption = when (this) {
    is ElementalTypeTeraType -> type.hue.toRGB()
    is StellarTeraType -> ElementalTypes.all().random().hue.toRGB()
    else -> Triple(0f, 0f, 0f)
}.let { particle(it.first, it.second, it.third) }

fun particle(red: Number, green: Number, blue: Number) =
    ColorParticleOption.create(TerastalParticles.TERASTAL_PARTICLE_TYPE, red.toFloat(), green.toFloat(), blue.toFloat())

// Mixin
fun Pokemon.getTerastallizedType(): TeraType? =
    (this as HasTerastallizedType).`terastal_expansion$getTerastallizedType`()

fun Pokemon.setTerastallizedType(state: TeraType?) =
    (this as HasTerastallizedType).`terastal_expansion$setTerastallizedType`(state)

fun Pokemon.clearTerastallizedType() = this.setTerastallizedType(null)

