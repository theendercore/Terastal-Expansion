package com.theendercore.terastal_expansion

import net.minecraft.util.Identifier
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object TeraConst {
    const val MOD_ID = "terastal_expansion"
    const val MOD_NAME = "TerastalExpansion"

    @JvmField
    val log: Logger = LoggerFactory.getLogger(MOD_NAME)
    fun id(path: String) = Identifier(MOD_ID, path)
}