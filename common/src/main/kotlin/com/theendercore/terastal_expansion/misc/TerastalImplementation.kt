package com.theendercore.terastal_expansion.misc

import com.cobblemon.mod.common.NetworkManager

interface TerastalImplementation {
    val networkManager: NetworkManager
    fun registerItems()
    fun registerBlocks()
}