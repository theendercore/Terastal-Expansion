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

class HasTerastallizedStateUpdatePacket(pokemon: () -> Pokemon, value: TeraType?) :
    SingleUpdatePacket<TeraType?, HasTerastallizedStateUpdatePacket>(pokemon, value) {
    override val id: ResourceLocation = ID

    override fun encodeValue(buffer: RegistryFriendlyByteBuf) {
        buffer.writeNullable(value?.id) { _, id -> buffer.writeIdentifier(id) }
    }

    override fun set(pokemon: Pokemon, value: TeraType?) = pokemon.setTerastallizedType(value)

    companion object {
        val ID = id("has_terastallized_state")
        fun decode(buffer: RegistryFriendlyByteBuf) =
            HasTerastallizedStateUpdatePacket(decodePokemon(buffer), buffer.readNullable { TeraTypes.get(buffer.readIdentifier())!! })
    }
}