package com.theendercore.terastal_expansion.client

import com.theendercore.terastal_expansion.init.TerastalBlocks
import net.minecraft.client.renderer.RenderType


object TerastalExpansionClient {
    private lateinit var implementation: TerastalClientImplementation
    fun init(implementation: TerastalClientImplementation) {
        this.implementation = implementation

        registerBlockRenderTypes()
    }

    private fun registerBlockRenderTypes() {
        implementation.registerBlockRenderType(
            RenderType.cutout(),
            TerastalBlocks.TERA_SHARD_CLUSTER,
            TerastalBlocks.LARGE_BUDDING_TERA_SHARD,
            TerastalBlocks.MEDIUM_BUDDING_TERA_SHARD,
            TerastalBlocks.SMALL_BUDDING_TERA_SHARD,
        )
    }
}