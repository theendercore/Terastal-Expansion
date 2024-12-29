package com.theendercore.terastal_expansion.client.net

import com.cobblemon.mod.common.net.messages.client.pokemon.update.SingleUpdatePacket
import com.cobblemon.mod.common.pokemon.Pokemon
import com.cobblemon.mod.common.util.cobblemonResource
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.resources.ResourceLocation

class TeraStateUpdatePacket(pokemon: () -> Pokemon, value: Boolean) : SingleUpdatePacket<Boolean, TeraStateUpdatePacket>(pokemon, value) {
    override val id: ResourceLocation = ID

    override fun encodeValue(buffer: RegistryFriendlyByteBuf) {
        buffer.writeBoolean(value)
    }

    override fun set(pokemon: Pokemon, value: Boolean) {
//        pokemon.teraType = value
    }

    companion object {
        val ID = cobblemonResource("tera_state_update")
        fun decode(buffer: RegistryFriendlyByteBuf) = TeraStateUpdatePacket(decodePokemon(buffer), buffer.readBoolean())
    }
}