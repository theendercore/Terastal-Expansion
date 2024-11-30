package com.theendercore.terastal_expansion.init

import com.cobblemon.mod.common.item.group.CobblemonItemGroups.ItemGroupHolder
import com.theendercore.terastal_expansion.TerastalConst.id
import net.minecraft.item.ItemGroup
import net.minecraft.item.ItemGroup.DisplayContext
import net.minecraft.item.ItemGroup.Entries
import net.minecraft.item.ItemGroup.EntryCollector
import net.minecraft.item.ItemStack
import net.minecraft.registry.Registries
import net.minecraft.registry.RegistryKey

object TerastalTabs {
    val ALL = mutableListOf<ItemGroupHolder>()
    val TERASTAL_TAB = create("terastal_tab", { ItemStack(TerastalItems.TERA_GEM_SHARD) }, ::modEntries)

    private fun modEntries(displayContext: DisplayContext, entries: Entries) {
        entries.addAll(TerastalItems.all().map(::ItemStack))
    }

    private fun create(
        name: String, displayIconProvider: () -> ItemStack, entryCollector: EntryCollector,
    ): RegistryKey<ItemGroup> {
        val key = RegistryKey.of(Registries.ITEM_GROUP.key, id(name))
        this.ALL += ItemGroupHolder(key, displayIconProvider, entryCollector)
        return key
    }

    fun register(consumer: (holder: ItemGroupHolder) -> ItemGroup) {
        ALL.forEach(consumer::invoke)
    }

}
