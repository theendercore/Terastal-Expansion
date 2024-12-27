package com.theendercore.terastal_expansion.neoforge.client

import com.theendercore.terastal_expansion.client.TerastalClientImplementation
import com.theendercore.terastal_expansion.client.TerastalExpansionClient
import net.minecraft.client.renderer.ItemBlockRenderTypes
import net.minecraft.client.renderer.RenderType
import net.minecraft.world.level.block.Block
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent
import net.neoforged.neoforge.common.NeoForge
import thedarkcolour.kotlinforforge.neoforge.forge.MOD_BUS

object TerastalExpansionNeoClient : TerastalClientImplementation {
    fun init() {
        MOD_BUS.addListener(::onClientSetup)
    }

    @SubscribeEvent
    private fun onClientSetup(event: FMLClientSetupEvent) {
        event.enqueueWork {
            TerastalExpansionClient.init(this)
        }
//        NeoForge.EVENT_BUS.register(this)
    }

    @Suppress("DEPRECATION")
    override fun registerBlockRenderType(layer: RenderType, vararg blocks: Block) =
        blocks.forEach { block -> ItemBlockRenderTypes.setRenderLayer(block, layer) }


}