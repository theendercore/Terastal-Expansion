package com.theendercore.terastal_expansion

import com.theendercore.terastal_expansion.misc.TeraImplementation

object TerastalExpansion {
    @JvmStatic
    fun init(impl: TeraImplementation) {
        impl.registerItems()
    }
}