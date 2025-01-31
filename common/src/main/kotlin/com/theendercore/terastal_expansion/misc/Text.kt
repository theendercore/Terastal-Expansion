package com.theendercore.terastal_expansion.misc

import net.minecraft.network.chat.Component

fun text(key: String, vararg args: Any) = Component.translatable(key, *args)

fun textL(text: String) = Component.literal(text)