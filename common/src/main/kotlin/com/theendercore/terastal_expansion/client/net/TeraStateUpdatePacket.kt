package com.theendercore.terastal_expansion.client.net

import com.cobblemon.mod.common.net.messages.client.pokemon.update.SingleUpdatePacket
import com.cobblemon.mod.common.pokemon.Pokemon
import com.theendercore.terastal_expansion.TerastalConst.id
import com.theendercore.terastal_expansion.misc.setTeraState
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.resources.ResourceLocation

class TeraStateUpdatePacket(pokemon: () -> Pokemon, value: Boolean) :
    SingleUpdatePacket<Boolean, TeraStateUpdatePacket>(pokemon, value) {
    override val id: ResourceLocation = ID

    override fun encodeValue(buffer: RegistryFriendlyByteBuf) {
        buffer.writeBoolean(value)
    }

    override fun set(pokemon: Pokemon, value: Boolean) = pokemon.setTeraState(value)

    companion object {
        val ID = id("tera_state_update")
        fun decode(buffer: RegistryFriendlyByteBuf) = TeraStateUpdatePacket(decodePokemon(buffer), buffer.readBoolean())
    }
}