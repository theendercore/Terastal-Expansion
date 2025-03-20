package com.theendercore.terastal_expansion.misc

import com.cobblemon.mod.common.api.types.tera.TeraType

interface HasTerastallizedType {
    fun `terastal_expansion$getTerastallizedType`(): TeraType?
    fun `terastal_expansion$setTerastallizedType`(state: TeraType?)
}