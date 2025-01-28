package com.theendercore.terastal_expansion.init

import com.cobblemon.mod.common.item.group.CobblemonItemGroups.ItemGroupHolder
import com.theendercore.terastal_expansion.TerastalConst.MOD_ID
import com.theendercore.terastal_expansion.TerastalConst.id
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.CreativeModeTab.*
import net.minecraft.world.item.ItemStack

object TerastalTabs {
    val ALL = mutableListOf<ItemGroupHolder>()
    val TERASTAL_TAB = create(MOD_ID, { ItemStack(TerastalItems.TERA_GEM_SHARD) }, ::modEntries)

    private fun modEntries(displayContext: ItemDisplayParameters, entries: Output) {
        val map = TerastalItems.all().map(::ItemStack).sortedBy { it.displayName.string }
        entries.acceptAll(map)
    }

    private fun create(
        name: String, displayIconProvider: () -> ItemStack, entryCollector: DisplayItemsGenerator,
    ): ResourceKey<CreativeModeTab> {
        val key = ResourceKey.create(Registries.CREATIVE_MODE_TAB, id(name))
        this.ALL += ItemGroupHolder(key, displayIconProvider, entryCollector)
        return key
    }

    fun register(consumer: (holder: ItemGroupHolder) -> CreativeModeTab) {
        ALL.forEach(consumer::invoke)
    }

}
