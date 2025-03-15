package com.theendercore.terastal_expansion.fabric.client

import com.theendercore.terastal_expansion.client.TerastalClientImplementation
import com.theendercore.terastal_expansion.client.TerastalExpansionClient
import com.theendercore.terastal_expansion.client.TerastalParticle
import com.theendercore.terastal_expansion.fabric.TerastalExpansionFabric
import com.theendercore.terastal_expansion.init.TerastalParticles
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap.INSTANCE
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry
import net.minecraft.client.particle.ParticleProvider
import net.minecraft.client.particle.SpriteSet
import net.minecraft.client.renderer.RenderType
import net.minecraft.core.particles.ParticleOptions
import net.minecraft.core.particles.ParticleType
import net.minecraft.world.level.block.Block

class TerastalExpansionFabricClient : TerastalClientImplementation, ClientModInitializer {
    override fun onInitializeClient() {
        registerParticleFactory(TerastalParticles.TERASTAL_PARTICLE_TYPE, TerastalParticle::Provider)
        TerastalExpansionClient.init(this)
        TerastalExpansionFabric.networkManager.registerClientHandlers()
    }

    override fun registerBlockRenderType(layer: RenderType, vararg blocks: Block) = INSTANCE.putBlocks(layer, *blocks)
    override fun <T : ParticleOptions> registerParticleFactory(
        type: ParticleType<T>, factory: (SpriteSet) -> ParticleProvider<T>
    ) = ParticleFactoryRegistry.getInstance()
        .register(type, ParticleFactoryRegistry.PendingParticleFactory { factory(it) })

}
