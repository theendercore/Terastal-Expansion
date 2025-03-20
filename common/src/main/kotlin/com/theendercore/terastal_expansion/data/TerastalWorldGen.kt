package com.theendercore.terastal_expansion.data

import com.theendercore.terastal_expansion.TerastalConst.id
import com.theendercore.terastal_expansion.TerastalExpansion
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.levelgen.GenerationStep.Decoration.UNDERGROUND_ORES
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature
import net.minecraft.world.level.levelgen.placement.PlacedFeature

@Suppress("SameParameterValue")
object TerastalWorldGen {
    val TERA_SHARD_GEODE_CFG = cfgFeature("tera_shard_geode")
    val TERA_SHARD_GEODE = placedFeature("tera_shard_geode")

    fun register() = TerastalExpansion.implementation.addFeatureToWorldGen(TERA_SHARD_GEODE, UNDERGROUND_ORES)
    private fun cfgFeature(name: String): ResourceKey<ConfiguredFeature<*, *>> =
        ResourceKey.create(Registries.CONFIGURED_FEATURE, id(name))

    private fun placedFeature(name: String): ResourceKey<PlacedFeature> =
        ResourceKey.create(Registries.PLACED_FEATURE, id(name))
}