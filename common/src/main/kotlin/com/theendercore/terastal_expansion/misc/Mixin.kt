package com.theendercore.terastal_expansion.misc

import com.cobblemon.mod.common.api.types.tera.TeraType
import com.cobblemon.mod.common.pokemon.Pokemon


fun Pokemon.getTerastallizedType(): TeraType? = (this as HasTerastallizedType).`terastal_expansion$getTerastallizedType`()
fun Pokemon.setTerastallizedType(state: TeraType?) =
    (this as HasTerastallizedType).`terastal_expansion$setTerastallizedType`(state)
fun Pokemon.clearTerastallizedType() = this.setTerastallizedType(null)

