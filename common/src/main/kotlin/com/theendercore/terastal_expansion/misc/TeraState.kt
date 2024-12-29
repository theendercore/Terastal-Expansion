package com.theendercore.terastal_expansion.misc

import com.cobblemon.mod.common.NetworkManager

interface TeraState {
    fun getTeraState(): Boolean
    fun setTeraState(state: Boolean)

}