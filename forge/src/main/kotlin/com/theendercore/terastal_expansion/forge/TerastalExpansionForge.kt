package com.theendercore.terastal_expansion.forge

import com.theendercore.terastal_expansion.ExampleCommandRegistry.registerCommands
import com.theendercore.terastal_expansion.TerastalExpansion.init
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.event.RegisterCommandsEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod

@Mod("terastal_expansion")
class TerastalExpansionForge {
    init {
        init()
        MinecraftForge.EVENT_BUS.register(this)
    }

    @SubscribeEvent
    fun onCommandRegistration(event: RegisterCommandsEvent) {
        registerCommands(event.dispatcher, event.buildContext, event.commandSelection)
    }
}
