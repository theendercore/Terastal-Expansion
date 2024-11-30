package com.theendercore.terastal_expansion.init.misc

import com.cobblemon.mod.common.CobblemonSounds
import net.minecraft.block.AbstractBlock
import net.minecraft.block.enums.Instrument

object TerastalBlockProperties {

    val TERA_GEM_PROPERTIES = AbstractBlock.Settings.create()
        .strength(1.0F)
        .sounds(CobblemonSounds.TUMBLESTONE_BLOCK_SOUNDS)
        .requiresTool()
        .instrument(Instrument.BASEDRUM)
}