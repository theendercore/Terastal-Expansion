package com.theendercore.terastal_expansion.fabric

import com.theendercore.terastal_expansion.TerastalExpansion
import com.theendercore.terastal_expansion.fabric.net.TerastalFabricNetworkManager
import com.theendercore.terastal_expansion.init.TerastalBlocks
import com.theendercore.terastal_expansion.init.TerastalItems
import com.theendercore.terastal_expansion.init.TerastalTabs
import com.theendercore.terastal_expansion.misc.TerastalImplementation
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.biome.v1.BiomeModifications
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.minecraft.core.Registry
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
        TerastalItems.register { identifier, item -> Registry.register(TerastalItems.registry, identifier, item) }
        TerastalTabs.register {
            Registry.register(
                CREATIVE_MODE_TAB, it.key, FabricItemGroup.builder()
                    .title(it.displayName).icon(it.displayIconProvider)
                    .displayItems(it.entryCollector).build()
            )
        }
    }

    override fun registerBlocks() {
        TerastalBlocks.register { identifier, item -> Registry.register(TerastalBlocks.registry, identifier, item) }
    }

    override fun addFeatureToWorldGen(
        feature: ResourceKey<PlacedFeature>, step: GenerationStep.Decoration, validTag: TagKey<Biome>?
    ) {
        val predicate: (BiomeSelectionContext) -> Boolean =
            if (validTag != null) { it -> it.hasTag(validTag) } else { it -> it.hasTag(BiomeTags.IS_OVERWORLD) }
        BiomeModifications.addFeature(predicate, step, feature)
    }
}
