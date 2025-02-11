package com.theendercore.terastal_expansion.data

import com.theendercore.terastal_expansion.TerastalConst.id
import com.theendercore.terastal_expansion.TerastalExpansion
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.levelgen.GenerationStep.Decoration.UNDERGROUND_ORES

object TerastalWorldGen {
    val TERA_SHARD_GEAODE_CFG = cfgFeature("tera_shard_geode")
    val TERA_SHARD_GEAODE = placedFeature("tera_shard_geode")

    fun register() {
        TerastalExpansion.implementation.addFeatureToWorldGen(TERA_SHARD_GEAODE, UNDERGROUND_ORES)
    }

    fun cfgFeature(name: String) = ResourceKey.create(Registries.CONFIGURED_FEATURE, id(name))
    fun placedFeature(name: String) = ResourceKey.create(Registries.PLACED_FEATURE, id(name))
}