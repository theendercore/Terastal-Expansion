package com.cobblemon.forge.example;

import com.cobblemon.common.example.ExampleCommandRegistry;
import com.theendercore.terastal_expansion.TerastalExpansion;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import static net.minecraftforge.common.MinecraftForge.EVENT_BUS;

@Mod("terastal_expansion")
public class ForgeModExample {

    public ForgeModExample() {
        TerastalExpansion.init();
        EVENT_BUS.register(this);

    }

    @SubscribeEvent
    public void onCommandRegistration(RegisterCommandsEvent event) {
        ExampleCommandRegistry.registerCommands(event.getDispatcher(), event.getBuildContext(), event.getCommandSelection());
    }
}
