package com.theendercore.terastal_expansion.registry

import com.cobblemon.mod.common.NetworkManager
import net.minecraft.resources.ResourceKey
import net.minecraft.tags.TagKey
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.levelgen.GenerationStep
import net.minecraft.world.level.levelgen.placement.PlacedFeature
import java.io.File

interface TerastalImplementation {
    val networkManager: NetworkManager
    fun registerItems()
    fun registerBlocks()
    fun registerDataComponents()
    fun registerParticles()
    fun getConfigFile(path: String): File
    fun addFeatureToWorldGen(
        feature: ResourceKey<PlacedFeature>, step: GenerationStep.Decoration, validTag: TagKey<Biome>? = null
    )
}