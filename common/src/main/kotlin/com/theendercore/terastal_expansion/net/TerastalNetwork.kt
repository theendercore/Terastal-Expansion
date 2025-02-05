package com.theendercore.terastal_expansion.net

import com.cobblemon.mod.common.api.net.ClientNetworkPacketHandler
import com.cobblemon.mod.common.client.net.pokemon.update.PokemonUpdatePacketHandler
import com.cobblemon.mod.common.client.render.layer.PokemonOnShoulderRenderer
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity
import com.cobblemon.mod.common.net.PacketRegisterInfo
import com.cobblemon.mod.common.net.messages.client.PokemonUpdatePacket
import com.theendercore.terastal_expansion.client.net.TeraUpdatePacket
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
        list.add(PacketRegisterInfo(TeraTypeUP.ID, TeraTypeUP::decode, VerySketchyUpdatePacketHandler()))
        list.add(PacketRegisterInfo(HasTeraStateUP.ID, HasTeraStateUP::decode, VerySketchyUpdatePacketHandler()))

//        list.add(PacketRegisterInfo(TeraUpdatePacket.ID, TeraUpdatePacket::decode, VerySketchyUpdatePacketHandler()))

        return list
    }

    class VerySketchyUpdatePacketHandler<T : PokemonUpdatePacket<T>> : ClientNetworkPacketHandler<T> {
        override fun handle(packet: T, client: Minecraft) {
            val pokemon = packet.pokemon.invoke()
            val id = pokemon.entity?.id
            if (id != null) {
                val entity = client.level?.getEntity(id)
                if (entity is PokemonEntity) {
//                    val clientPokemon = entity.pokemon
                    entity.pokemon.teraType = pokemon.teraType
//                    entity.pokemon.setTerastallizedType(pokemon.teraType)
                }
            }

            val player = client.player ?: return
            PokemonOnShoulderRenderer.clearCache(player)
        }
    }
}
