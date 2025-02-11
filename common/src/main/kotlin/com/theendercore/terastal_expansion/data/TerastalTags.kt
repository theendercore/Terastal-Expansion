package com.theendercore.terastal_expansion.data

import com.theendercore.terastal_expansion.TerastalConst.id
import net.minecraft.core.registries.Registries
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item

object TerastalTags {
    val ITEM_TAGS = mutableListOf< TagKey<Item>>()

    val TERA_SHARDS = item("tera_shards")
    val TERA_GEMS = item("tera_gems")
    val POISON_ITEMS = item("poison_items")
    val PSYCHIC_ITEMS = item("psychic_items")
    val BUG_ITEMS = item("bug_items")
    val GHOST_ITEMS = item("ghost_items")
    val DRAGON_ITEMS = item("dragon_items")
    val DARK_ITEMS = item("dark_items")


    internal fun item(name: String): TagKey<Item> {
        val tag = TagKey.create(Registries.ITEM, id(name))
        ITEM_TAGS.add(tag)
        return tag
    }
    internal fun block(name: String) = TagKey.create(Registries.BLOCK, id(name))
}