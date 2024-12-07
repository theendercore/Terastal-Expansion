package com.theendercore.terastal_expansion.client

import net.minecraft.block.Block
import net.minecraft.client.render.RenderLayer

interface TerastalClientImplementation {
    fun registerBlockRenderType(layer: RenderLayer, vararg blocks: Block)
}