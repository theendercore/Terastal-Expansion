package com.theendercore.terastal_expansion.forge

import com.theendercore.terastal_expansion.TerastalExpansion.init
import com.theendercore.terastal_expansion.init.TerastalItems
import com.theendercore.terastal_expansion.misc.TeraImplementation
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.registries.RegisterEvent
import thedarkcolour.kotlinforforge.forge.MOD_BUS

@Mod("terastal_expansion")
class TerastalExpansionForge : TeraImplementation {
    init {
        init(this)
        MinecraftForge.EVENT_BUS.register(this)
    }

    override fun registerItems() = MOD_BUS.addListener<RegisterEvent> {
        it.register(TerastalItems.registryKey) { helper ->
            TerastalItems.register { identifier, item -> helper.register(identifier, item) }
        }
    }

}
