package com.theendercore.terastal_expansion.client

import com.cobblemon.mod.common.api.types.tera.TeraType
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.theendercore.terastal_expansion.misc.getTeraTypeColor
import com.theendercore.terastal_expansion.misc.toVec3f
import net.minecraft.client.Minecraft
import net.minecraft.core.particles.DustParticleOptions

fun renderTeraType(entity: PokemonEntity, terastallizedType: TeraType) {
//    val size = entity.getDimensions(entity.pose)
    val color = terastallizedType.getTeraTypeColor()
    Minecraft.getInstance().particleEngine.createTrackingEmitter(entity, DustParticleOptions(color.toVec3f(), 1f))

}