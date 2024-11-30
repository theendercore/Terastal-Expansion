package com.theendercore.terastal_expansion

import com.theendercore.terastal_expansion.misc.TerastalImplementation

object TerastalExpansion {
    @JvmStatic
    fun init(tera: TerastalImplementation) {
        tera.registerBlocks()
        tera.registerItems()
    }
}