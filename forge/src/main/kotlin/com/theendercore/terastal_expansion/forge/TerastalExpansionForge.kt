package com.theendercore.terastal_expansion.forge

import com.theendercore.terastal_expansion.TerastalExpansion.init
import com.theendercore.terastal_expansion.init.TerastalBlocks
import com.theendercore.terastal_expansion.init.TerastalItems
import com.theendercore.terastal_expansion.init.TerastalTabs
import com.theendercore.terastal_expansion.misc.TerastalImplementation
import net.minecraft.item.ItemGroup
import net.minecraft.registry.RegistryKeys
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.registries.RegisterEvent
import thedarkcolour.kotlinforforge.forge.MOD_BUS

@Mod("terastal_expansion")
class TerastalExpansionForge : TerastalImplementation {
    init {
        init(this)
        MinecraftForge.EVENT_BUS.register(this)
//        CobblemonForge
    }

    override fun registerItems() = MOD_BUS.addListener<RegisterEvent> {
        it.register(TerastalItems.registryKey) { helper -> TerastalItems.register(helper::register) }
        it.register(RegistryKeys.ITEM_GROUP) { helper ->
            TerastalTabs.register { holder ->
                val itemGroup = ItemGroup.builder()
                    .displayName(holder.displayName)
                    .icon(holder.displayIconProvider)
                    .entries(holder.entryCollector)
                    .build()
                helper.register(holder.key, itemGroup)
                itemGroup
            }
        }
    }

    override fun registerBlocks() = MOD_BUS.addListener<RegisterEvent> {
        it.register(TerastalBlocks.registryKey) { helper -> TerastalBlocks.register(helper::register) }
    }

}
