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

    @JvmField
    val NORMAL_TERA_SHARD = item("normal_tera_shard")
    @JvmField
    val FIRE_TERA_SHARD = item("fire_tera_shard")
    @JvmField
    val WATER_TERA_SHARD = item("water_tera_shard")
    @JvmField
    val ELECTRIC_TERA_SHARD = item("electric_tera_shard")
    @JvmField
    val GRASS_TERA_SHARD = item("grass_tera_shard")
    @JvmField
    val ICE_TERA_SHARD = item("ice_tera_shard")
    @JvmField
    val FIGHTING_TERA_SHARD = item("fighting_tera_shard")
    @JvmField
    val POISON_TERA_SHARD = item("poison_tera_shard")
    @JvmField
    val GROUND_TERA_SHARD = item("ground_tera_shard")
    @JvmField
    val FLYING_TERA_SHARD = item("flying_tera_shard")
    @JvmField
    val PSYCHIC_TERA_SHARD = item("psychic_tera_shard")
    @JvmField
    val BUG_TERA_SHARD = item("bug_tera_shard")
    @JvmField
    val ROCK_TERA_SHARD = item("rock_tera_shard")
    @JvmField
    val GHOST_TERA_SHARD = item("ghost_tera_shard")
    @JvmField
    val DRAGON_TERA_SHARD = item("dragon_tera_shard")
    @JvmField
    val DARK_TERA_SHARD = item("dark_tera_shard")
    @JvmField
    val STEEL_TERA_SHARD = item("steel_tera_shard")
    @JvmField
    val FAIRY_TERA_SHARD = item("fairy_tera_shard")
    @JvmField
    val STELLAR_TERA_SHARD = item("stellar_tera_shard")

    @JvmField
    val NORMAL_TERA_GEM = item("normal_tera_gem")
    @JvmField
    val FIRE_TERA_GEM = item("fire_tera_gem")
    @JvmField
    val WATER_TERA_GEM = item("water_tera_gem")
    @JvmField
    val ELECTRIC_TERA_GEM = item("electric_tera_gem")
    @JvmField
    val GRASS_TERA_GEM = item("grass_tera_gem")
    @JvmField
    val ICE_TERA_GEM = item("ice_tera_gem")
    @JvmField
    val FIGHTING_TERA_GEM = item("fighting_tera_gem")
    @JvmField
    val POISON_TERA_GEM = item("poison_tera_gem")
    @JvmField
    val GROUND_TERA_GEM = item("ground_tera_gem")
    @JvmField
    val FLYING_TERA_GEM = item("flying_tera_gem")
    @JvmField
    val PSYCHIC_TERA_GEM = item("psychic_tera_gem")
    @JvmField
    val BUG_TERA_GEM = item("bug_tera_gem")
    @JvmField
    val ROCK_TERA_GEM = item("rock_tera_gem")
    @JvmField
    val GHOST_TERA_GEM = item("ghost_tera_gem")
    @JvmField
    val DRAGON_TERA_GEM = item("dragon_tera_gem")
    @JvmField
    val DARK_TERA_GEM = item("dark_tera_gem")
    @JvmField
    val STEEL_TERA_GEM = item("steel_tera_gem")
    @JvmField
    val FAIRY_TERA_GEM = item("fairy_tera_gem")
    @JvmField
    val STELLAR_TERA_GEM = item("stellar_tera_gem")


    internal fun item(name: String): CobblemonItem = this.create(name, CobblemonItem(Item.Settings()))
    internal fun blockItem(name: String, block: Block): BlockItem =
        this.create(name, BlockItem(block, Item.Settings()))
}
