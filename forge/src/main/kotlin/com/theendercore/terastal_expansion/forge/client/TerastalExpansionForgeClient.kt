package com.theendercore.terastal_expansion.forge.client

import com.theendercore.terastal_expansion.client.TerastalClientImplementation
import com.theendercore.terastal_expansion.client.TerastalExpansionClient
import net.minecraft.block.Block
import net.minecraft.client.render.RenderLayer
import net.minecraft.client.render.RenderLayers
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import thedarkcolour.kotlinforforge.forge.MOD_BUS

object TerastalExpansionForgeClient : TerastalClientImplementation {
    fun init() {
        MOD_BUS.addListener(::onClientSetup)
    }

    private fun onClientSetup(event: FMLClientSetupEvent) {
        event.enqueueWork { TerastalExpansionClient.init(this) }
        MinecraftForge.EVENT_BUS.register(this)
    }

    @Suppress("DEPRECATION")
    override fun registerBlockRenderType(layer: RenderLayer, vararg blocks: Block) =
        blocks.forEach { block -> RenderLayers.setRenderLayer(block, layer) }

}