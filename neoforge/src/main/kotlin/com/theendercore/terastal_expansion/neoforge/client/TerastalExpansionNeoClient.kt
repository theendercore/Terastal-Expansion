package com.theendercore.terastal_expansion.neoforge.client

import com.theendercore.terastal_expansion.client.TerastalClientImplementation
import com.theendercore.terastal_expansion.client.TerastalExpansionClient
import com.theendercore.terastal_expansion.client.TerastalParticle
import com.theendercore.terastal_expansion.init.TerastalParticles
import net.minecraft.client.particle.ParticleProvider
import net.minecraft.client.particle.SpriteSet
import net.minecraft.client.renderer.ItemBlockRenderTypes
import net.minecraft.client.renderer.RenderType
import net.minecraft.core.particles.ParticleOptions
import net.minecraft.core.particles.ParticleType
import net.minecraft.world.level.block.Block
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent
import thedarkcolour.kotlinforforge.neoforge.forge.MOD_BUS

object TerastalExpansionNeoClient : TerastalClientImplementation {
    fun init() {
        with(MOD_BUS) {
            addListener(::onClientSetup)
            addListener(::onRegisterParticleProviders)
        }
    }

    @SubscribeEvent
    private fun onClientSetup(event: FMLClientSetupEvent) {
        event.enqueueWork {
            TerastalExpansionClient.init(this)
        }
    }

    @Suppress("DEPRECATION")
    override fun registerBlockRenderType(layer: RenderType, vararg blocks: Block) =
        blocks.forEach { block -> ItemBlockRenderTypes.setRenderLayer(block, layer) }

    override fun <T : ParticleOptions> registerParticleFactory(
        type: ParticleType<T>, factory: (SpriteSet) -> ParticleProvider<T>
    ) =
        throw UnsupportedOperationException("NeoForge can't store these early, use TerastalNeoForgeClient#onRegisterParticleProviders")

    private fun onRegisterParticleProviders(event: RegisterParticleProvidersEvent) {
        event.registerSpriteSet(TerastalParticles.TERASTAL_PARTICLE_TYPE, TerastalParticle::Provider)
    }
}