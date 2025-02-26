package com.theendercore.terastal_expansion.init

import com.mojang.serialization.Codec
import com.theendercore.terastal_expansion.registry.TPlatformRegistry
import net.minecraft.core.Registry
import net.minecraft.core.component.DataComponentType
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.resources.ResourceKey

object TerastalItemComponents : TPlatformRegistry<Registry<DataComponentType<*>>, ResourceKey<Registry<DataComponentType<*>>>, DataComponentType<*>>() {

    val CHARGE: DataComponentType<Int> = create(
        "charge", DataComponentType.builder<Int>()
            .persistent(Codec.INT)
            .networkSynchronized(ByteBufCodecs.INT)
            .build()
    )

  /*  fun register() {
        Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, id("charge"), CHARGE)
    }*/

    override val registry = BuiltInRegistries.DATA_COMPONENT_TYPE
    override val resourceKey = Registries.DATA_COMPONENT_TYPE

}