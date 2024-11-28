package com.theendercore.terastal_expansion.init

import com.cobblemon.mod.common.item.CobblemonItem
import com.theendercore.terastal_expansion.registry.TPlatformRegistry
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys

object TerastalItems : TPlatformRegistry<Registry<Item>, RegistryKey<Registry<Item>>, Item>() {
    override val registry: Registry<Item> = Registries.ITEM
    override val registryKey: RegistryKey<Registry<Item>> = RegistryKeys.ITEM

    @JvmField
    val TERA_SHARD = noSettingsItem("tera_shard")

    private fun noSettingsItem(name: String): CobblemonItem =
        this.create(name, CobblemonItem(Item.Settings()))

}
