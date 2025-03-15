package com.theendercore.terastal_expansion.client

import net.minecraft.client.particle.ParticleProvider
import net.minecraft.client.particle.SpriteSet
import net.minecraft.client.renderer.RenderType
import net.minecraft.core.particles.ParticleOptions
import net.minecraft.core.particles.ParticleType
import net.minecraft.world.level.block.Block


interface TerastalClientImplementation {
    fun registerBlockRenderType(layer: RenderType, vararg blocks: Block)
    fun <T : ParticleOptions> registerParticleFactory(
        type: ParticleType<T>, factory: (SpriteSet) -> ParticleProvider<T>
    )
}