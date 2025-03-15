package com.theendercore.terastal_expansion.client.net

import com.cobblemon.mod.common.api.net.NetworkPacket
import com.cobblemon.mod.common.util.cobblemonResource
import com.cobblemon.mod.common.util.readIdentifier
import com.cobblemon.mod.common.util.writeIdentifier
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.phys.Vec3

class SpawnTerastalParticlePacket(
    val effectId: ResourceLocation,
    val position: Vec3,
    val color: Int
) : NetworkPacket<SpawnTerastalParticlePacket> {
    override val id = ID

    companion object {
        val ID = cobblemonResource("spawn_terastal_particle")
        fun decode(buffer: RegistryFriendlyByteBuf): SpawnTerastalParticlePacket {
            return SpawnTerastalParticlePacket(
                effectId = buffer.readIdentifier(),
                position = Vec3(
                    buffer.readDouble(),
                    buffer.readDouble(),
                    buffer.readDouble()
                ),
                color = buffer.readInt()
            )
        }
    }

    override fun encode(buffer: RegistryFriendlyByteBuf) {
        buffer.writeIdentifier(effectId)
        buffer.writeDouble(position.x)
        buffer.writeDouble(position.y)
        buffer.writeDouble(position.z)
        buffer.writeInt(color)
    }
}