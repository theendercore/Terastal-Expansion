package com.theendercore.terastal_expansion.fabric

import com.theendercore.terastal_expansion.TerastalExpansion
import com.theendercore.terastal_expansion.fabric.net.TerastalFabricNetworkManager
import com.theendercore.terastal_expansion.init.*
import com.theendercore.terastal_expansion.registry.TerastalImplementation
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.biome.v1.BiomeModifications
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.minecraft.core.Registry.register
import net.minecraft.core.registries.BuiltInRegistries.CREATIVE_MODE_TAB
import net.minecraft.resources.ResourceKey
import net.minecraft.tags.BiomeTags
import net.minecraft.tags.TagKey
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.levelgen.GenerationStep
import net.minecraft.world.level.levelgen.placement.PlacedFeature

object TerastalExpansionFabric : ModInitializer, TerastalImplementation {
    override val networkManager = TerastalFabricNetworkManager
    override fun onInitialize() {
        TerastalExpansion.init(this)
        networkManager.registerMessages()

        TerastalExpansion.events()
    }

    override fun registerItems() {
        with(TerastalItems) { register { id, item -> register(registry, id, item) } }
        TerastalTabs.register {
            register(
                CREATIVE_MODE_TAB, it.key, FabricItemGroup.builder()
                    .title(it.displayName).icon(it.displayIconProvider)
                    .displayItems(it.entryCollector).build()
            )
        }
    }

    override fun registerBlocks() = with(TerastalBlocks) { register { id, entry -> register(registry, id, entry) } }
    override fun addFeatureToWorldGen(
        feature: ResourceKey<PlacedFeature>, step: GenerationStep.Decoration, validTag: TagKey<Biome>?
    ) {
        val predicate: (BiomeSelectionContext) -> Boolean =
            if (validTag != null) { it -> it.hasTag(validTag) } else { it -> it.hasTag(BiomeTags.IS_OVERWORLD) }
        BiomeModifications.addFeature(predicate, step, feature)
    }

    override fun registerDataComponents() =
        with(TerastalItemComponents) { register { id, entry -> register(registry, id, entry) } }

    override fun registerParticles() =
        with(TerastalParticles) { register { id, entry -> register(registry, id, entry) } }

}
