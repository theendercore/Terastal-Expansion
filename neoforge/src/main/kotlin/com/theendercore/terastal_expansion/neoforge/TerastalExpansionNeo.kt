package com.theendercore.terastal_expansion.neoforge

import com.theendercore.terastal_expansion.TerastalExpansion
import com.theendercore.terastal_expansion.TerastalExpansion.init
import com.theendercore.terastal_expansion.init.TerastalBlocks
import com.theendercore.terastal_expansion.init.TerastalItems
import com.theendercore.terastal_expansion.init.TerastalTabs
import com.theendercore.terastal_expansion.misc.TerastalImplementation
import com.theendercore.terastal_expansion.neoforge.client.TerastalExpansionNeoClient
import com.theendercore.terastal_expansion.neoforge.net.TerastalNeoNetWorkManager
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.tags.TagKey
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.levelgen.GenerationStep
import net.minecraft.world.level.levelgen.placement.PlacedFeature
import net.neoforged.api.distmarker.Dist
import net.neoforged.fml.common.Mod
import net.neoforged.fml.loading.FMLEnvironment
import net.neoforged.neoforge.registries.RegisterEvent
import thedarkcolour.kotlinforforge.neoforge.forge.MOD_BUS

@Mod("terastal_expansion")
class TerastalExpansionNeo : TerastalImplementation {
    override val networkManager = TerastalNeoNetWorkManager

    init {
        with(MOD_BUS) {
            init(this@TerastalExpansionNeo)
//            addListener(TerastalBiomeModifiers::register)
            addListener(networkManager::registerMessages)
        }
        TerastalExpansion.events()
        if (FMLEnvironment.dist == Dist.CLIENT) TerastalExpansionNeoClient.init()
    }

    override fun registerItems() = MOD_BUS.addListener<RegisterEvent> {
        it.register(TerastalItems.resourceKey) { helper -> TerastalItems.register(helper::register) }
        it.register(Registries.CREATIVE_MODE_TAB) { helper ->
            TerastalTabs.register { holder ->
                val itemGroup = CreativeModeTab.builder()
                    .title(holder.displayName)
                    .icon(holder.displayIconProvider)
                    .displayItems(holder.entryCollector)
                    .build()
                helper.register(holder.key, itemGroup)
                itemGroup
            }
        }
    }

    override fun registerBlocks() = MOD_BUS.addListener<RegisterEvent> {
        it.register(TerastalBlocks.resourceKey) { helper -> TerastalBlocks.register(helper::register) }
    }

    override fun addFeatureToWorldGen(feature: ResourceKey<PlacedFeature>, step: GenerationStep.Decoration, validTag: TagKey<Biome>?) {
    }
}
