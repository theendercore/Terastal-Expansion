package com.theendercore.terastal_expansion.item

import com.cobblemon.mod.common.item.CobblemonItem
import com.theendercore.terastal_expansion.init.TerastalItemComponents
import net.minecraft.ChatFormatting
import net.minecraft.network.chat.Component
import net.minecraft.util.Mth.lerp
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import kotlin.math.max
import com.theendercore.terastal_expansion.config.TeraConfigObj.cfg as config
import net.minecraft.network.chat.Component.literal as text

class TeraOrbItem : CobblemonItem(Properties().stacksTo(1)) {
    override fun getBarColor(stack: ItemStack): Int = 0x9f009f

    override fun isBarVisible(stack: ItemStack): Boolean {
        val charge = stack.getCharge()
        return if (charge >= 0) charge < config.orbMaxCharges
        else super.isBarVisible(stack)
    }

    override fun getBarWidth(stack: ItemStack): Int {
        val charge = stack.getCharge()
        return if (charge <= -1) BAR_LIMIT.toInt()
        else barWidth(charge, config.orbMaxCharges)
    }

    override fun appendHoverText(stack: ItemStack, c: TooltipContext, list: MutableList<Component>, flag: TooltipFlag) {
        super.appendHoverText(stack, c, list, flag)
        if (config.orbMaxCharges >= 0) list.addLast(
            if (config.orbMaxCharges == 0) text("Tera is disabled!").withStyle(ChatFormatting.RED)
            else text("Charges: ${stack.getCharge()}/${config.orbMaxCharges}")
        )
    }

    /* override fun useOn(context: UseOnContext): InteractionResult {
         val charge = context.itemInHand.getCharge()
         println(charge.toFloat() / config.orbMaxCharges.toFloat())
         if (charge < 0) return super.useOn(context)
         context.itemInHand.setCharge(if (charge == config.orbMaxCharges) 0 else charge + 1)
         return super.useOn(context)
     }*/

    companion object {
        @JvmStatic
        fun ItemStack.canUse(): Boolean {
            if (config.orbMaxCharges == 0) return false
            val charges = this.getCharge()
            return (charges == -1) || (charges > 0)
        }

        @JvmStatic
        fun Player.useCharge(stack: ItemStack) =
            if (!this.isCreative) stack.setCharge(max(0, stack.getCharge() - 1)) else 0

        fun ItemStack.getCharge(): Int =
            this.getOrDefault(
                TerastalItemComponents.CHARGE,
                if (config.orbMaxCharges >= 0) config.orbMaxCharges else -1
            )

        fun ItemStack.refill() = this.setCharge(config.orbMaxCharges)
        private fun ItemStack.setCharge(charge: Int) = this.set(TerastalItemComponents.CHARGE, charge)
        private const val BAR_LIMIT = 14f
        private fun barWidth(value: Int, max: Int) = lerp(value.toFloat() / max.toFloat(), 0f, BAR_LIMIT).toInt()
    }
}
