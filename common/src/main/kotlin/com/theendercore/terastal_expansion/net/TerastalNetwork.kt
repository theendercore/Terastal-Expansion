package com.theendercore.terastal_expansion.net

import com.cobblemon.mod.common.api.net.ClientNetworkPacketHandler
import com.cobblemon.mod.common.client.render.layer.PokemonOnShoulderRenderer
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.cobblemon.mod.common.net.PacketRegisterInfo
import com.cobblemon.mod.common.net.messages.client.PokemonUpdatePacket
import com.cobblemon.mod.common.pokemon.Pokemon
import com.theendercore.terastal_expansion.misc.setTerastallizedType
import net.minecraft.client.Minecraft
import com.cobblemon.mod.common.net.messages.client.pokemon.update.TeraTypeUpdatePacket as TeraTypeUP
import com.theendercore.terastal_expansion.client.net.HasTerastallizedStateUpdatePacket as HasTeraStateUP

object TerastalNetwork {
    val s2cPayloads = generateS2CPacketInfoList()

    //    val c2sPayloads = generateC2SPacketInfoList()
    private fun generateS2CPacketInfoList(): List<PacketRegisterInfo<*>> {
        val list = mutableListOf<PacketRegisterInfo<*>>()
        // Pokemon Update Packets
        list.add(PacketRegisterInfo(TeraTypeUP.ID, TeraTypeUP::decode, VerySketchyUpdatePacketHandler { poke, packet ->
            poke.teraType = packet.value
        }))
        list.add(
            PacketRegisterInfo(
                HasTeraStateUP.ID, HasTeraStateUP::decode,
                VerySketchyUpdatePacketHandler { poke, packet -> poke.setTerastallizedType(packet.value) }
            )
        )
        return list
    }

    class VerySketchyUpdatePacketHandler<T : PokemonUpdatePacket<T>>(val apply: (Pokemon, T) -> Unit) :
        ClientNetworkPacketHandler<T> {
        override fun handle(packet: T, client: Minecraft) {
            val poke = packet.pokemon()
            packet.applyToPokemon()
            poke.entity?.id
                ?.let { client.level?.getEntity(it) }
                ?.let { entity -> if (entity is PokemonEntity) apply(entity.pokemon, packet) }

            val player = client.player ?: return
            PokemonOnShoulderRenderer.clearCache(player)
        }
    }
}
