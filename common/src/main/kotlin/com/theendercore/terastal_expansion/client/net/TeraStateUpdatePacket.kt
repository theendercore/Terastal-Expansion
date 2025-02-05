package com.theendercore.terastal_expansion.client.net

import com.cobblemon.mod.common.api.types.tera.TeraType
import com.cobblemon.mod.common.api.types.tera.TeraTypes
import com.cobblemon.mod.common.net.messages.client.pokemon.update.SingleUpdatePacket
import com.cobblemon.mod.common.pokemon.Pokemon
import com.cobblemon.mod.common.util.readIdentifier
import com.cobblemon.mod.common.util.writeIdentifier
import com.theendercore.terastal_expansion.TerastalConst.id
import com.theendercore.terastal_expansion.misc.setTerastallizedType
import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.resources.ResourceLocation

class TeraStateUpdatePacket(pokemon: () -> Pokemon, value: TeraType) :
    SingleUpdatePacket<TeraType, TeraStateUpdatePacket>(pokemon, value) {
    override val id: ResourceLocation = ID

    override fun encodeValue(buffer: RegistryFriendlyByteBuf) {
        buffer.writeIdentifier(value.id)
    }

    override fun set(pokemon: Pokemon, value: TeraType) = pokemon.setTerastallizedType(value)

    companion object {
        val ID = id("tera_state_update")
        fun decode(buffer: RegistryFriendlyByteBuf) =
            TeraStateUpdatePacket(decodePokemon(buffer), TeraTypes.get(buffer.readIdentifier())!!)
    }
}