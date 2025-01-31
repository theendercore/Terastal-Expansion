package com.theendercore.terastal_expansion.init.misc

import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument
import net.minecraft.world.level.material.PushReaction

object TerastalBlockProperties {

    val TERA_GEM_PROPERTIES = BlockBehaviour.Properties.of()
        .strength(1.0F)
        .sound(SoundType.AMETHYST)
        .requiresCorrectToolForDrops()
        .instrument(NoteBlockInstrument.BASEDRUM)

    val TERA_CLUSTER_PROPERTIES = BlockBehaviour.Properties.of()
        .pushReaction(PushReaction.DESTROY)
        .noOcclusion()
        .strength(1.5F)
        .sound(SoundType.AMETHYST_CLUSTER)
}