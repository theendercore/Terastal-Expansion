package com.theendercore.terastal_expansion

import com.cobblemon.mod.common.api.pokemon.PokemonSpecies
import com.mojang.brigadier.CommandDispatcher
import net.minecraft.commands.CommandBuildContext
import net.minecraft.commands.CommandSourceStack
import net.minecraft.commands.Commands
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.Style
import net.minecraft.resources.ResourceLocation

object ExampleCommandRegistry {
    fun registerCommands(
        dispatcher: CommandDispatcher<CommandSourceStack>,
        context: CommandBuildContext,
        environment: Commands.CommandSelection
    ) {
        dispatcher.register(Commands.literal("test").executes { ctx ->
            val species = PokemonSpecies.getByIdentifier(ResourceLocation.tryParse("cobblemon:bidoof")!!)
            ctx.getSource().sendSystemMessage(
                Component.literal("Got species: ")
                    .withStyle(Style.EMPTY.withColor(0x03e3fc))
                    .append(species!!.translatedName)
            )
            0
        })
    }
}
