package com.theendercore.terastal_expansion.misc

import net.minecraft.network.chat.Component
import net.minecraft.network.chat.MutableComponent

fun text(key: String, vararg args: Any) = Component.translatable(key, *args)

fun textL(text: String) = Component.literal(text)
fun MutableComponent.appendNull(component: Component?) = this.append(component ?: Component.literal("null"))
