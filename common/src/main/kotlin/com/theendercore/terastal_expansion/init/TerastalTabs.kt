package com.theendercore.terastal_expansion.init

import com.cobblemon.mod.common.item.group.CobblemonItemGroups.ItemGroupHolder
import com.theendercore.terastal_expansion.TerastalConst.MOD_ID
import com.theendercore.terastal_expansion.TerastalConst.id
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.CreativeModeTab.*
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.ItemLike

object TerastalTabs {
    val ALL = mutableListOf<ItemGroupHolder>()
    val TERASTAL_TAB = create(MOD_ID, { ItemStack(TerastalItems.TERA_ORB) }, ::modEntries)

    private fun modEntries(displayContext: ItemDisplayParameters, entries: Output) {
        val list = listOf<ItemLike>(
            TerastalItems.TERA_ORB,
            TerastalItems.TERA_GEM_SHARD,
            TerastalItems.TERA_GEM,
            TerastalBlocks.TERA_GEM_BLOCK,
            TerastalBlocks.TERA_SHARD_CLUSTER,
            TerastalBlocks.LARGE_BUDDING_TERA_SHARD,
            TerastalBlocks.MEDIUM_BUDDING_TERA_SHARD,
            TerastalBlocks.SMALL_BUDDING_TERA_SHARD,
            TerastalItems.NORMAL_TERA_SHARD,
            TerastalItems.FIRE_TERA_SHARD,
            TerastalItems.WATER_TERA_SHARD,
            TerastalItems.ELECTRIC_TERA_SHARD,
            TerastalItems.GRASS_TERA_SHARD,
            TerastalItems.ICE_TERA_SHARD,
            TerastalItems.FIGHTING_TERA_SHARD,
            TerastalItems.POISON_TERA_SHARD,
            TerastalItems.GROUND_TERA_SHARD,
            TerastalItems.FLYING_TERA_SHARD,
            TerastalItems.PSYCHIC_TERA_SHARD,
            TerastalItems.BUG_TERA_SHARD,
            TerastalItems.ROCK_TERA_SHARD,
            TerastalItems.GHOST_TERA_SHARD,
            TerastalItems.DRAGON_TERA_SHARD,
            TerastalItems.DARK_TERA_SHARD,
            TerastalItems.STEEL_TERA_SHARD,
            TerastalItems.FAIRY_TERA_SHARD,
            TerastalItems.STELLAR_TERA_SHARD,
            TerastalItems.NORMAL_TERA_GEM,
            TerastalItems.FIRE_TERA_GEM,
            TerastalItems.WATER_TERA_GEM,
            TerastalItems.ELECTRIC_TERA_GEM,
            TerastalItems.GRASS_TERA_GEM,
            TerastalItems.ICE_TERA_GEM,
            TerastalItems.FIGHTING_TERA_GEM,
            TerastalItems.POISON_TERA_GEM,
            TerastalItems.GROUND_TERA_GEM,
            TerastalItems.FLYING_TERA_GEM,
            TerastalItems.PSYCHIC_TERA_GEM,
            TerastalItems.BUG_TERA_GEM,
            TerastalItems.ROCK_TERA_GEM,
            TerastalItems.GHOST_TERA_GEM,
            TerastalItems.DRAGON_TERA_GEM,
            TerastalItems.DARK_TERA_GEM,
            TerastalItems.STEEL_TERA_GEM,
            TerastalItems.FAIRY_TERA_GEM,
            TerastalItems.STELLAR_TERA_GEM,
            TerastalItems.NORMAL_TERA_HAT,
            TerastalItems.FIRE_TERA_HAT,
            TerastalItems.WATER_TERA_HAT,
            TerastalItems.ELECTRIC_TERA_HAT,
            TerastalItems.GRASS_TERA_HAT,
            TerastalItems.ICE_TERA_HAT,
            TerastalItems.FIGHTING_TERA_HAT,
            TerastalItems.POISON_TERA_HAT,
            TerastalItems.GROUND_TERA_HAT,
            TerastalItems.FLYING_TERA_HAT,
            TerastalItems.PSYCHIC_TERA_HAT,
            TerastalItems.BUG_TERA_HAT,
            TerastalItems.ROCK_TERA_HAT,
            TerastalItems.GHOST_TERA_HAT,
            TerastalItems.DRAGON_TERA_HAT,
            TerastalItems.DARK_TERA_HAT,
            TerastalItems.STEEL_TERA_HAT,
            TerastalItems.FAIRY_TERA_HAT,
            TerastalItems.STELLAR_TERA_HAT,
            TerastalItems.TERASTALLIZER,
        ).map(::ItemStack)

        entries.acceptAll(list)
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
