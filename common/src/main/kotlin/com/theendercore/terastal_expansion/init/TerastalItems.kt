package com.theendercore.terastal_expansion.init

import com.cobblemon.mod.common.item.CobblemonItem
import com.theendercore.terastal_expansion.registry.TPlatformRegistry
import net.minecraft.block.Block
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys

object TerastalItems : TPlatformRegistry<Registry<Item>, RegistryKey<Registry<Item>>, Item>() {
    override val registry: Registry<Item> = Registries.ITEM
    override val registryKey: RegistryKey<Registry<Item>> = RegistryKeys.ITEM

    @JvmField
    val TERA_GEM_SHARD = item("tera_gem_shard")
    @JvmField
    val TERA_GEM = item("tera_gem")

    val NORMAL_TERA_SHARD = item("normal_tera_shard")
    val FIRE_TERA_SHARD = item("fire_tera_shard")
    val WATER_TERA_SHARD = item("water_tera_shard")
    val ELECTRIC_TERA_SHARD = item("electric_tera_shard")
    val GRASS_TERA_SHARD = item("grass_tera_shard")
    val ICE_TERA_SHARD = item("ice_tera_shard")
    val FIGHTING_TERA_SHARD = item("fighting_tera_shard")
    val POISON_TERA_SHARD = item("poison_tera_shard")
    val GROUND_TERA_SHARD = item("ground_tera_shard")
    val FLYING_TERA_SHARD = item("flying_tera_shard")
    val PSYCHIC_TERA_SHARD = item("psychic_tera_shard")
    val BUG_TERA_SHARD = item("bug_tera_shard")
    val ROCK_TERA_SHARD = item("rock_tera_shard")
    val GHOST_TERA_SHARD = item("ghost_tera_shard")
    val DRAGON_TERA_SHARD = item("dragon_tera_shard")
    val DARK_TERA_SHARD = item("dark_tera_shard")
    val STEEL_TERA_SHARD = item("steel_tera_shard")
    val FAIRY_TERA_SHARD = item("fairy_tera_shard")
    val STELLAR_TERA_SHARD = item("stellar_tera_shard")

    val NORMAL_TERA_GEM = item("normal_tera_gem")
    val FIRE_TERA_GEM = item("fire_tera_gem")
    val WATER_TERA_GEM = item("water_tera_gem")
    val ELECTRIC_TERA_GEM = item("electric_tera_gem")
    val GRASS_TERA_GEM = item("grass_tera_gem")
    val ICE_TERA_GEM = item("ice_tera_gem")
    val FIGHTING_TERA_GEM = item("fighting_tera_gem")
    val POISON_TERA_GEM = item("poison_tera_gem")
    val GROUND_TERA_GEM = item("ground_tera_gem")
    val FLYING_TERA_GEM = item("flying_tera_gem")
    val PSYCHIC_TERA_GEM = item("psychic_tera_gem")
    val BUG_TERA_GEM = item("bug_tera_gem")
    val ROCK_TERA_GEM = item("rock_tera_gem")
    val GHOST_TERA_GEM = item("ghost_tera_gem")
    val DRAGON_TERA_GEM = item("dragon_tera_gem")
    val DARK_TERA_GEM = item("dark_tera_gem")
    val STEEL_TERA_GEM = item("steel_tera_gem")
    val FAIRY_TERA_GEM = item("fairy_tera_gem")
    val STELLAR_TERA_GEM = item("stellar_tera_gem")


    internal fun item(name: String): CobblemonItem = this.create(name, CobblemonItem(Item.Settings()))
    internal fun blockItem(name: String, block: Block): BlockItem =
        this.create(name, BlockItem(block, Item.Settings()))
}
