package com.theendercore.terastal_expansion.neoforge

import com.theendercore.terastal_expansion.TerastalExpansion.init
import com.theendercore.terastal_expansion.neoforge.client.TerastalExpansionNeoClient
import com.theendercore.terastal_expansion.init.TerastalBlocks
import com.theendercore.terastal_expansion.init.TerastalItems
import com.theendercore.terastal_expansion.init.TerastalTabs
import com.theendercore.terastal_expansion.misc.TerastalImplementation
import net.minecraft.core.registries.Registries
import net.minecraft.world.item.CreativeModeTab
import net.neoforged.api.distmarker.Dist
import net.neoforged.fml.common.Mod
import net.neoforged.fml.loading.FMLEnvironment
import net.neoforged.neoforge.common.NeoForge
import net.neoforged.neoforge.registries.RegisterEvent
import thedarkcolour.kotlinforforge.neoforge.forge.MOD_BUS

@Mod("terastal_expansion")
class TerastalExpansionNeo : TerastalImplementation {
    init {
        init(this)

//        NeoForge.EVENT_BUS.register(this)
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

}
