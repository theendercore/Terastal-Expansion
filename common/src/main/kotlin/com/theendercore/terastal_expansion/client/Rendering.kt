package com.theendercore.terastal_expansion.client

import com.cobblemon.mod.common.api.gui.blitk
import com.cobblemon.mod.common.api.types.tera.TeraType
import com.cobblemon.mod.common.api.types.tera.elemental.ElementalTypeTeraType
import com.cobblemon.mod.common.api.types.tera.gimmick.StellarTeraType
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.math.Axis
import com.theendercore.terastal_expansion.TerastalConst.id
import com.theendercore.terastal_expansion.misc.getHat
import com.theendercore.terastal_expansion.misc.getParticle
import com.theendercore.terastal_expansion.misc.getTerastallizedType
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.util.Mth
import net.minecraft.world.item.ItemDisplayContext
import net.minecraft.world.item.Items

fun particleSpawner(entity: PokemonEntity, terastallizedType: TeraType) {
    val mc = Minecraft.getInstance()
    if (mc.isPaused) return

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

fun renderHat(
    entity: PokemonEntity, partialTicks: Float, stack: PoseStack, buffer: MultiBufferSource, packedLight: Int
) {
    val mc = Minecraft.getInstance()
    val size = entity.getDimensions(entity.pose)
    val teraType = entity.pokemon.getTerastallizedType()
    stack.pushPose()
    stack.translate(0f, size.height + .75f, 0f)
    stack.rotateAround(
        Axis.YN.rotationDegrees(Mth.lerp(partialTicks, entity.yBodyRotO, entity.yBodyRot)), 0f, 0f, 0f
    )
    mc.itemRenderer.renderStatic(
        teraType?.getHat() ?: Items.AIR.defaultInstance,
        ItemDisplayContext.FIXED,
        packedLight, -1,
        stack,
        buffer,
        mc.level,
        0
    )
    stack.popPose()
}


private val teraResource = id("textures/gui/summary/tera.png")
private val teraTypesResource = id("textures/gui/tera_types.png")
fun summeryScreenIcon(ctx: GuiGraphics, type: TeraType, matrices: PoseStack, x: Int, y: Int) {
    val scale2 = 1f
    blitk(
        matrixStack = matrices,
        texture = teraResource,
        x = (x - 24) / scale2,
        y = (y + 120) / scale2,
        width = 25,
        height = 25,
        scale = scale2
    )
    render(ctx, x - 10.5f, y + 122f, type)
}

fun render(ctx: GuiGraphics, x: Float, y: Float, type: TeraType) {
    val width = 46
    val height = 52
    val scale = 0.4f
    val offsetX = (width / 2) * scale

    blitk(
        matrixStack = ctx.pose(),
        texture = teraTypesResource,
        x = (x - offsetX) / scale,
        y = y / scale,
        height = height,
        width = width,
        uOffset = width * type.xMultiplier() + 0.1f,
        textureWidth = width * 19,
        blend = true,
        scale = scale
    )
}

fun TeraType.xMultiplier() = when (this) {
    is ElementalTypeTeraType -> this.type.textureXMultiplier + 1
    is StellarTeraType -> 0
    else -> error("Unknown tera type! $this")
}
