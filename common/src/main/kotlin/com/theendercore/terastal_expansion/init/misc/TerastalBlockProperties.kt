package com.theendercore.terastal_expansion.init.misc

import com.cobblemon.mod.common.CobblemonSounds
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument
import net.minecraft.world.level.material.PushReaction

object TerastalBlockProperties {

    val TERA_GEM_PROPERTIES = BlockBehaviour.Properties.of()
        .strength(1.0F)
        .sound(CobblemonSounds.TUMBLESTONE_BLOCK_SOUNDS)
        .requiresCorrectToolForDrops()
        .instrument(NoteBlockInstrument.BASEDRUM)

    val TERA_CLUSTER_PROPERTIES = BlockBehaviour.Properties.of()
        .pushReaction(PushReaction.DESTROY)
        .noOcclusion()
        .strength(1.5F)
        .sound(CobblemonSounds.TUMBLESTONE_SOUNDS)
}