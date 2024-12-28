package com.theendercore.terastal_expansion.fabric.net

import com.cobblemon.mod.common.NetworkManager
import com.cobblemon.mod.common.api.net.NetworkPacket
import com.cobblemon.mod.fabric.net.FabricPacketInfo
import com.theendercore.terastal_expansion.net.TerastalNetwork
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking
import net.minecraft.server.level.ServerPlayer

object TerastalFabricNetworkManager : NetworkManager {
    fun registerMessages() {
        TerastalNetwork.s2cPayloads.map { FabricPacketInfo(it) }.forEach { it.registerPacket(client = true) }
//        CobblemonNetwork.c2sPayloads.map { FabricPacketInfo(it) }.forEach { it.registerPacket(client = false) }
    }

    fun registerClientHandlers() {
        TerastalNetwork.s2cPayloads.map { FabricPacketInfo(it) }.forEach { it.registerClientHandler() }
    }

    /* fun registerServerHandlers() {
         CobblemonNetwork.c2sPayloads.map { FabricPacketInfo(it) }.forEach { it.registerServerHandler() }
     }*/

    override fun sendToServer(packet: NetworkPacket<*>) = ClientPlayNetworking.send(packet)
    override fun sendPacketToPlayer(player: ServerPlayer, packet: NetworkPacket<*>) =
        ServerPlayNetworking.send(player, packet)
}