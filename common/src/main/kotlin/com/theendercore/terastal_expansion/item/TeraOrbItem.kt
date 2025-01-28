package com.theendercore.terastal_expansion.item

import com.cobblemon.mod.common.item.CobblemonItem
import net.minecraft.world.item.ItemStack

class TeraOrbItem : CobblemonItem(Properties().stacksTo(1)) {
    override fun getBarColor(stack: ItemStack): Int = 0x9f009f
    override fun isBarVisible(stack: ItemStack): Boolean = true
    override fun getBarWidth(stack: ItemStack): Int = 8
}
