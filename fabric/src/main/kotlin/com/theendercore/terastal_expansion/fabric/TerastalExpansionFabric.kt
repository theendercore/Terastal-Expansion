package com.theendercore.terastal_expansion.fabric

import com.theendercore.terastal_expansion.TerastalExpansion
import com.theendercore.terastal_expansion.init.TerastalBlocks
import com.theendercore.terastal_expansion.init.TerastalItems
import com.theendercore.terastal_expansion.init.TerastalTabs
import com.theendercore.terastal_expansion.misc.TerastalImplementation
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries.CREATIVE_MODE_TAB

object TerastalExpansionFabric : ModInitializer, TerastalImplementation {
    override fun onInitialize() {
        TerastalExpansion.init(this)
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
}
