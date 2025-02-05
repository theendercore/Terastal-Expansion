package com.theendercore.terastal_expansion.misc

import com.cobblemon.mod.common.api.types.tera.TeraType

interface HasTerastallizedState {
    fun `terastal$getTerastallizedType`(): TeraType?
    fun `terastal$setTerastallizedType`(state: TeraType?)
}