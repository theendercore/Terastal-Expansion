package com.theendercore.terastal_expansion.init

import com.mojang.serialization.MapCodec
import com.theendercore.terastal_expansion.registry.TPlatformRegistry
import net.minecraft.core.Registry
import net.minecraft.core.particles.ColorParticleOption
import net.minecraft.core.particles.ParticleOptions
import net.minecraft.core.particles.ParticleType
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import net.minecraft.resources.ResourceKey
import java.util.function.Function

typealias ParticleReg = Registry<ParticleType<*>>

object TerastalParticles : TPlatformRegistry<ParticleReg, ResourceKey<ParticleReg>, ParticleType<*>>() {
    override val registry: ParticleReg = BuiltInRegistries.PARTICLE_TYPE
    override val resourceKey: ResourceKey<ParticleReg> = Registries.PARTICLE_TYPE
    val TERASTAL_PARTICLE_TYPE =
        this.create("terastal", make(ColorParticleOption::codec, ColorParticleOption::streamCodec))


    private fun <T : ParticleOptions?> make(
        codecGetter: Function<ParticleType<T>, MapCodec<T>>,
        streamCodecGetter: Function<ParticleType<T>, StreamCodec<in RegistryFriendlyByteBuf, T>>
    ): ParticleType<T> = object : ParticleType<T>(false) {
        override fun codec(): MapCodec<T> = codecGetter.apply(this)
        override fun streamCodec(): StreamCodec<in RegistryFriendlyByteBuf, T> = streamCodecGetter.apply(this)
    }
}