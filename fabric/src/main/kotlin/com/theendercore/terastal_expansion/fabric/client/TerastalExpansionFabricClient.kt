package com.theendercore.terastal_expansion.fabric.client

import com.theendercore.terastal_expansion.client.TerastalClientImplementation
import net.fabricmc.api.ClientModInitializer
import com.theendercore.terastal_expansion.client.TerastalExpansionClient
import com.theendercore.terastal_expansion.fabric.TerastalExpansionFabric
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap.INSTANCE
import net.minecraft.client.renderer.RenderType
import net.minecraft.world.level.block.Block

class TerastalExpansionFabricClient : TerastalClientImplementation, ClientModInitializer {
    override fun onInitializeClient() {
        TerastalExpansionClient.init(this)
        TerastalExpansionFabric.networkManager.registerClientHandlers()
    }

    override fun registerBlockRenderType(layer: RenderType, vararg blocks: Block) = INSTANCE.putBlocks(layer, *blocks)
}
