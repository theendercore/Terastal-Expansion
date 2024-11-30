package com.theendercore.terastal_expansion.init

import com.theendercore.terastal_expansion.init.TerastalItems.blockItem
import com.theendercore.terastal_expansion.init.misc.TerastalBlockProperties.TERA_GEM_PROPERTIES
import com.theendercore.terastal_expansion.registry.TPlatformRegistry
import net.minecraft.block.Block
import net.minecraft.block.MapColor
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys

object TerastalBlocks : TPlatformRegistry<Registry<Block>, RegistryKey<Registry<Block>>, Block>() {
    override val registry: Registry<Block> = Registries.BLOCK
    override val registryKey: RegistryKey<Registry<Block>> = RegistryKeys.BLOCK

    @JvmField
    val TERA_GEM_BLOCK = block("tera_gem_block", Block(TERA_GEM_PROPERTIES.mapColor(MapColor.TERRACOTTA_ORANGE)))


    private fun <E : Block> block(name: String, entry: E): E {
        val block = create(name, entry)
        blockItem(name, block)
        return block
    }
}
