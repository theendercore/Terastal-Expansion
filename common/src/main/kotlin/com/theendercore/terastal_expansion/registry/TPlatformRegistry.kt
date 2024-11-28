package com.theendercore.terastal_expansion.registry

import com.cobblemon.mod.common.platform.PlatformRegistry
import com.theendercore.terastal_expansion.TeraConst.id
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey

abstract class TPlatformRegistry<R : Registry<T>, K : RegistryKey<R>, T> : PlatformRegistry<R, K, T>() {
    override fun <E : T> create(name: String, entry: E): E {
        val identifier = id(name)
        this.queue[identifier] = entry
      /*  if (entry is BagItemConvertible) {
            BagItems.bagItems.add(
                priority = Priority.NORMAL,
                value = entry
            )
        }*/
        return entry
    }
}