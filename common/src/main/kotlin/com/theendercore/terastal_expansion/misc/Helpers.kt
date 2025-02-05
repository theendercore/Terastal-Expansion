package com.theendercore.terastal_expansion.misc

import com.cobblemon.mod.common.api.types.tera.TeraType
import com.cobblemon.mod.common.api.types.tera.TeraTypes
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.cobblemon.mod.common.pokemon.Pokemon
import com.cobblemon.mod.common.util.math.DoubleRange
import com.cobblemon.mod.common.util.math.remap
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.serialization.JsonOps
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.Font
import net.minecraft.client.particle.SingleQuadParticle
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.entity.EntityRenderDispatcher
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.MutableComponent
import net.minecraft.util.Mth
import org.joml.Quaternionf
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

fun jsonToText(json: JsonElement, nesting: Int = 0): List<Component> = buildList {
    when (json) {
        is JsonObject -> {
            for ((key, value) in json.entrySet()) {
                if (value is JsonObject) {
                    if (value.toString().length < 33) text("$key: $value ", nesting)
                    else {
                        text("$key: {", nesting)
                        addAll(jsonToText(value, nesting + 1))
                        text("}", nesting)
                    }
                } else if (value is JsonArray) {
                    val list = jsonToText(value, nesting + 1).toMutableList()
                    if (list.size > 1) {
                        text("$key: [", nesting)
                        addAll(list.drop(1))
                    } else add(makeText("$key: ", nesting).append(list.firstOrNull() ?: makeText("null", 0)))
                } else text("$key: $value", nesting)
            }
        }

        is JsonArray -> {
            if (json.isEmpty) text("[]", 0)
            else if (json.size() < 6 && json.all { it is JsonPrimitive })
                text("[ ${json.joinToString(", ") { it.toString() }} ]", 0)
            else {
                text("[", 0)
                for (value in json) {
                    addAll(jsonToText(value, nesting + 1))
                    text(",", 0)
                }
                removeLast()
                text("]", nesting)
            }
        }

        else -> text(json.toString(), 0)
    }
}

fun MutableCollection<Component>.text(str: String, nesting: Int = 0) = this.add(makeText(str, nesting))

fun makeText(str: String, nesting: Int = 0): MutableComponent =
    Component.literal("  ".repeat(nesting)).append(Component.translatable(str))

fun String.repeat(times: Int): String = this.repeatTimes(times)
private fun String.repeatTimes(times: Int): String = buildString { repeat(times) { append(this@repeatTimes) } }


var client: Minecraft? = null
fun renderPokemonDebugText(
    stack: PoseStack, buffer: MultiBufferSource, dispatch: EntityRenderDispatcher, entity: PokemonEntity
) {
    if (client == null) client = Minecraft.getInstance()
    client?.let {
        val x = 0f//entity.x.toFloat()
        val y = 0f //(entity.y + entity.bbHeight + 10f).toFloat()
        val pokemon = entity.pokemon


        val json = Pokemon.CODEC.encodeStart(JsonOps.INSTANCE, pokemon).getOrThrow()
        val color = 0xffffff

        val scale = 1.0
        val sizeScale = Mth.lerp(scale.remap(DoubleRange(0.65, 1.5), DoubleRange(0.0, 1.0)), 0.5, 1.0)
        val offsetScale = Mth.lerp(scale.remap(DoubleRange(0.65, 1.5), DoubleRange(0.0, 1.0)), 0.0, 1.0)
        val entityHeight = entity.boundingBox.ysize + 0.5f


        stack.pushPose()
        stack.translate(0.0, entityHeight, 0.0)
        Quaternionf().let {
            SingleQuadParticle.FacingCameraMode.LOOKAT_Y.setRotation(it, dispatch.camera, 1f)
            stack.mulPose(it)
        }

        val textList = jsonToText(filterJson(json)).reversed().toMutableList()
        textList.add(makeText("TeraState: " + entity.pokemon.getTerastallizedType()))
        textList.add(makeText("Types: " + pokemon.types.joinToString(", ", "[ ", " ]") { it.name }))
        var len = 1
        for (component in textList) {
            if (component.string.length < len) continue
            len = component.string.length
        }
        stack.translate(-((len / 11.0) / 2), 0.0 + (offsetScale / 2), -(sizeScale / 2))
        stack.scale((0.025 * sizeScale).toFloat(), (-0.025 * sizeScale).toFloat(), (1 * sizeScale).toFloat())

        textList.forEachIndexed { idx, text ->
            it.font.drawInBatch(
                text,
                x, y - (idx * 10),
                color, false,
                stack.last().pose(), buffer, Font.DisplayMode.NORMAL,
                0x000000, 1
            )
        }
        stack.popPose()
    }
}

val debugList = listOf("TeraType")
fun filterJson(json: JsonElement): JsonElement {
    if (json !is JsonObject) {
        println("Not an object!")
        return json
    }
    val newJson = JsonObject()
    json.asJsonObject.asMap().forEach { (key, value) -> if (debugList.contains(key)) newJson.add(key, value) }
    return newJson
}
