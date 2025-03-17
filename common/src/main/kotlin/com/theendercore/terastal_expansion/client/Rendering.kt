package com.theendercore.terastal_expansion.client

import com.cobblemon.mod.common.api.gui.blitk
import com.cobblemon.mod.common.api.types.tera.TeraType
import com.cobblemon.mod.common.api.types.tera.elemental.ElementalTypeTeraType
import com.cobblemon.mod.common.client.gui.TypeIcon
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.mojang.blaze3d.vertex.PoseStack
import com.theendercore.terastal_expansion.TerastalConst.id
import com.theendercore.terastal_expansion.misc.getParticle
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiGraphics

fun teraTypeRenderer(entity: PokemonEntity, terastallizedType: TeraType) {
    if (Minecraft.getInstance().isPaused) return

    val size = entity.getDimensions(entity.pose)
    val world = entity.level()
    val random = world.random
    if (random.nextInt(2) != 0) return

    val color = terastallizedType.getParticle()
    val offset = (size.width + 1.35)

    world.addParticle(
        color,
        entity.x + (random.nextDouble() - 0.5) * offset,
        entity.y + (random.nextDouble() - 0.01) * size.height,
        entity.z + (random.nextDouble() - 0.5) * offset,
        0.0, 0.0, 0.0
    )
}

private val teraResource = id("textures/gui/summary/tera.png")
fun summeryScreenIcon(ctx: GuiGraphics, type: TeraType, matrices: PoseStack, x: Int, y: Int) {
    val scale2 = 1f //0.9375f
    blitk(
        matrixStack = matrices,
        texture = teraResource,
        x = (x - 24) / scale2,
        y = (y + 120) / scale2,
        width = 25,
        height = 25,
        scale = scale2
    )
    if (type is ElementalTypeTeraType) TypeIcon(
        x = x - 10.5,
        y = y + 123.5,
        type = type.type,
        secondaryType = null,
        centeredX = true
    ).render(ctx)
}