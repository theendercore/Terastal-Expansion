package com.theendercore.terastal_expansion.misc

import com.cobblemon.mod.common.api.types.tera.TeraType
import com.cobblemon.mod.common.api.types.tera.TeraTypes
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.cobblemon.mod.common.pokemon.Pokemon
import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.serialization.JsonOps
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.Font
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.MutableComponent
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

fun jsonToText(json: JsonElement, nesting: Int = 0): List<Component> = buildList {
    when (json) {
        is JsonObject -> {
            for ((key, value) in json.entrySet().filter { it.key.toString() == "TeraType" }) {
                if (value is JsonObject) {
                    if (value.toString().length < 33) text(nesting, "$key: $value ")
                    else {
                        text(nesting, "$key: {")
                        addAll(jsonToText(value, nesting + 1))
                        text(nesting, "}")
                    }
                } else if (value is JsonArray) {
                    val list = jsonToText(value, nesting + 1).toMutableList()
                    if (list.size > 1) {
                        text(nesting, "$key: [")
                        addAll(list.drop(1))
                    } else add(makeText(nesting, "$key: ").append(list.firstOrNull() ?: makeText(0, "null")))
                } else text(nesting, "$key: $value")
            }
        }

        is JsonArray -> {
            if (json.isEmpty) text(0, "[]")
            else if (json.size() < 6 && json.all { it is JsonPrimitive })
                text(0, "[ ${json.joinToString(", ") { it.toString() }} ]")
            else {
                text(nesting, "[")
                for (value in json) {
                    addAll(jsonToText(value, nesting + 1))
                    text(nesting, ",")
                }
                removeLast()
                text(nesting, "]")
            }
        }

        else -> text(nesting, json.toString())
    }
}

fun MutableCollection<Component>.text(nesting: Int, str: String) = this.add(makeText(nesting, str))

fun makeText(nesting: Int, str: String): MutableComponent =
    Component.literal("  ".repeat(nesting)).append(Component.translatable(str))

fun String.repeat(times: Int): String = this.repeatTimes(times)
private fun String.repeatTimes(times: Int): String = buildString { repeat(times) { append(this@repeatTimes) } }


var client: Minecraft? = null
fun renderPokemonDebugText(
    stack: PoseStack,
    buffer: MultiBufferSource,
    entity: PokemonEntity
) {
    if (client == null) client = Minecraft.getInstance()
    client?.let {
        val x = -60f//entity.x.toFloat()
        val y = -20f //(entity.y + entity.bbHeight + 10f).toFloat()
        val pokemon = entity.pokemon
        stack.pushPose()
        val json = Pokemon.CODEC.encodeStart(JsonOps.INSTANCE, pokemon).getOrThrow()
        val color = 0xffffff
        jsonToText(json).reversed().forEachIndexed { idx, text ->
            it.font.drawInBatch(
                text,
                x, y - (idx * 10),
                color,
                false,
                stack.last().pose(),
                buffer,
                Font.DisplayMode.NORMAL,
                0x000000,
                1
            )
            /*

            asdkagsdjkdgfhsdghfdhasgdhasdhgashdghagsdh
            testy
             */

        }
        stack.popPose()
    }
}