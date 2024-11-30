package com.theendercore.terastal_expansion.fabric

import com.theendercore.terastal_expansion.TerastalExpansion
import com.theendercore.terastal_expansion.init.TerastalBlocks
import com.theendercore.terastal_expansion.init.TerastalItems
import com.theendercore.terastal_expansion.init.TerastalTabs
import com.theendercore.terastal_expansion.misc.TerastalImplementation
import net.fabricmc.api.ModInitializer
import net.minecraft.registry.Registry
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.minecraft.registry.Registries

object TerastalExpansionFabric : ModInitializer, TerastalImplementation {
    override fun onInitialize() {
        TerastalExpansion.init(this)
    }

    override fun registerItems() {
        TerastalItems.register { identifier, item -> Registry.register(TerastalItems.registry, identifier, item) }
        TerastalTabs.register { provider ->
            Registry.register(
                Registries.ITEM_GROUP, provider.key, FabricItemGroup.builder()
                    .displayName(provider.displayName)
                    .icon(provider.displayIconProvider)
                    .entries(provider.entryCollector)
                    .build()
            )
        }
    }

    override fun registerBlocks() {
        TerastalBlocks.register { identifier, item -> Registry.register(TerastalBlocks.registry, identifier, item) }
    }
}
