package com.theendercore.terastal_expansion.client

import com.cobblemon.mod.common.api.net.ClientNetworkPacketHandler
import com.theendercore.terastal_expansion.client.net.SpawnTerastalParticlePacket
import com.theendercore.terastal_expansion.init.TerastalParticles
import net.minecraft.client.Minecraft
import net.minecraft.core.particles.ColorParticleOption

object SpawnTerastalParticleHandler : ClientNetworkPacketHandler<SpawnTerastalParticlePacket> {
    override fun handle(packet: SpawnTerastalParticlePacket, client: Minecraft) {
        Minecraft.getInstance().level ?: return
        Minecraft.getInstance().particleEngine.createParticle(
            ColorParticleOption.create(TerastalParticles.TERASTAL_PARTICLE_TYPE, packet.color),
            packet.position.x, packet.position.y, packet.position.z,
            0.0, 0.0, 0.0
        )
    }
}