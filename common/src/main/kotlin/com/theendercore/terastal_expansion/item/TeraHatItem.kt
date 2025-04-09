package com.theendercore.terastal_expansion.item

import com.cobblemon.mod.common.item.CobblemonItem
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Equipable
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level

class TeraHatItem : CobblemonItem(Properties()), Equipable {
    override fun getEquipmentSlot(): EquipmentSlot = EquipmentSlot.HEAD
    override fun use(level: Level, player: Player, usedHand: InteractionHand): InteractionResultHolder<ItemStack> =
        this.swapWithEquipmentSlot(this, level, player, usedHand)
}