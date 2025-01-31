package com.theendercore.terastal_expansion.net

import com.cobblemon.mod.common.client.net.pokemon.update.PokemonUpdatePacketHandler
import com.cobblemon.mod.common.net.PacketRegisterInfo
import com.cobblemon.mod.common.net.messages.client.pokemon.update.TeraTypeUpdatePacket as TeraTypeUP
import com.theendercore.terastal_expansion.client.net.TeraStateUpdatePacket as TeraStateUP
object TerastalNetwork {
    val s2cPayloads = generateS2CPacketInfoList()
    //    val c2sPayloads = generateC2SPacketInfoList()
    private fun generateS2CPacketInfoList(): List<PacketRegisterInfo<*>> {
        val list = mutableListOf<PacketRegisterInfo<*>>()
        // Pokemon Update Packets
        list.add(PacketRegisterInfo(TeraTypeUP.ID, TeraTypeUP::decode, PokemonUpdatePacketHandler()))
        list.add(PacketRegisterInfo(TeraStateUP.ID, TeraStateUP::decode, PokemonUpdatePacketHandler()))

        return list
    }
}
