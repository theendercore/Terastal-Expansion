package com.theendercore.terastal_expansion.fabric.client

import com.theendercore.terastal_expansion.client.TerastalClientImplementation
import net.fabricmc.api.ClientModInitializer
import com.theendercore.terastal_expansion.client.TerastalExpansionClient
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap
import net.minecraft.block.Block
import net.minecraft.client.render.RenderLayer

class TerastalExpansionFabricClient : TerastalClientImplementation, ClientModInitializer {
    override fun onInitializeClient() {
        TerastalExpansionClient.init(this)
    }

    override fun registerBlockRenderType(layer: RenderLayer, vararg blocks: Block) =
        BlockRenderLayerMap.INSTANCE.putBlocks(layer, *blocks)
}