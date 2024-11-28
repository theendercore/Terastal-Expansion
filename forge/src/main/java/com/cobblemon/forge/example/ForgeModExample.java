package com.cobblemon.forge.example;

import com.cobblemon.common.example.ExampleCommandRegistry;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.minecraftforge.common.MinecraftForge.EVENT_BUS;

@Mod("cobblemon_forge_mdk")
public class ForgeModExample {

    public ForgeModExample() {
        EVENT_BUS.register(this);

    }

    @SubscribeEvent
    public void onCommandRegistration(RegisterCommandsEvent event) {
        ExampleCommandRegistry.registerCommands(event.getDispatcher(), event.getBuildContext(), event.getCommandSelection());
    }
}
