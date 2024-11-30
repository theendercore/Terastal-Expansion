package com.theendercore.terastal_expansion.init

import com.cobblemon.mod.common.block.GrowableStoneBlock.Companion.STAGE_0
import com.cobblemon.mod.common.block.GrowableStoneBlock.Companion.STAGE_1
import com.cobblemon.mod.common.block.GrowableStoneBlock.Companion.STAGE_2
import com.cobblemon.mod.common.block.GrowableStoneBlock.Companion.STAGE_3
import com.cobblemon.mod.common.block.TumblestoneBlock
import com.theendercore.terastal_expansion.init.misc.TerastalBlockProperties.TERA_CLUSTER_PROPERTIES
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
    val TERA_GEM_BLOCK = block("tera_gem_block", Block(TERA_GEM_PROPERTIES.mapColor(MapColor.OFF_WHITE)))

    @JvmField
    val TERA_SHARD_CLUSTER = clusterBlock("tera_shard_cluster", STAGE_3, 7, 3, null)

    @JvmField
    val LARGE_BUDDING_TERA_SHARD = clusterBlock("large_budding_tera_shard", STAGE_2, 5, 3, TERA_SHARD_CLUSTER)

    @JvmField
    val MEDIUM_BUDDING_TERA_SHARD = clusterBlock("medium_budding_tera_shard", STAGE_1, 4, 3, LARGE_BUDDING_TERA_SHARD)

    @JvmField
    val SMALL_BUDDING_TERA_SHARD = clusterBlock("small_budding_tera_shard", STAGE_0, 3, 4, MEDIUM_BUDDING_TERA_SHARD)

    private fun clusterBlock(name: String, stage: Int, height: Int, xzOffset: Int, nextStage: Block?): Block =
        this.create(name, TumblestoneBlock(TERA_CLUSTER_PROPERTIES, stage, height, xzOffset, nextStage))

    private fun <E : Block> block(name: String, entry: E): E {
        val block = create(name, entry)
        return block
    }
}
