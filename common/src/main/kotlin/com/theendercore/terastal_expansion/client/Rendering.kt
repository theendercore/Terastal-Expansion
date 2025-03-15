package com.theendercore.terastal_expansion.client

import com.cobblemon.mod.common.api.types.tera.TeraType
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.theendercore.terastal_expansion.misc.getTeraTypeColor
import net.minecraft.client.Minecraft

fun renderTeraType(entity: PokemonEntity, terastallizedType: TeraType) {
    if (Minecraft.getInstance().isPaused) return

    val size = entity.getDimensions(entity.pose)
    val world = entity.level()
    val random = world.random
    if (random.nextInt(2) != 0) return

    val color = terastallizedType.getTeraTypeColor()
    val offset = (size.width + 1.35)

    world.addParticle(
        color,
        entity.x + (random.nextDouble() - 0.5) * offset,
        entity.y + (random.nextDouble() - 0.01) * size.height,
        entity.z + (random.nextDouble() - 0.5) * offset,
        0.0, 0.0, 0.0
    )

}