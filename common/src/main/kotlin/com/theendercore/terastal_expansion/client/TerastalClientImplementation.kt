package com.theendercore.terastal_expansion.client

import net.minecraft.client.renderer.RenderType
import net.minecraft.world.level.block.Block


interface TerastalClientImplementation {
    fun registerBlockRenderType(layer: RenderType, vararg blocks: Block)
}