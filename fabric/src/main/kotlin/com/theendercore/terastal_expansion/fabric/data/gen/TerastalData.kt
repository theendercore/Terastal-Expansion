package com.theendercore.terastal_expansion.fabric.data.gen

import com.cobblemon.mod.common.block.TumblestoneBlock
import com.theendercore.terastal_expansion.init.TerastalBlocks
import com.theendercore.terastal_expansion.init.TerastalItems
import com.theendercore.terastal_expansion.init.TerastalTabs
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.core.HolderLookup
import net.minecraft.data.models.BlockModelGenerators
import net.minecraft.data.models.ItemModelGenerators
import net.minecraft.data.models.model.ModelTemplates
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.BlockTags
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item
import java.util.concurrent.CompletableFuture

typealias FDOutput = FabricDataOutput
typealias FutureLookup = CompletableFuture<HolderLookup.Provider>

object TerastalData : DataGeneratorEntrypoint {
    override fun onInitializeDataGenerator(gen: FabricDataGenerator) {
        val pack = gen.createPack()
        pack.addProvider(::TModels)
        pack.addProvider(::EnLang)

        pack.addProvider(::LootTables)
        val block = pack.addProvider(::TBlockTags)
        pack.addProvider { o, r -> TItemTags(o, r, block) }
    }


}

class TModels(o: FDOutput) : FabricModelProvider(o) {
    override fun generateBlockStateModels(gen: BlockModelGenerators) {
//        TerastalBlocks.all().forEach(gen::registerSimpleCubeAll)
        gen.createTrivialCube(TerastalBlocks.TERA_GEM_BLOCK)
        var block: TumblestoneBlock? = TerastalBlocks.SMALL_BUDDING_TERA_SHARD as TumblestoneBlock?
        while (true) {
            gen.createAmethystCluster(block)
            gen.createSimpleFlatItemModel(block)
            block = block?.nextStage as TumblestoneBlock?
            if (block == null) break

        }
    }

    override fun generateItemModels(gen: ItemModelGenerators) {
        TerastalItems.all().filter { it !is BlockItem }.forEach(gen::registerPlain)
    }
}

class EnLang(o: FDOutput, r: FutureLookup) : FabricLanguageProvider(o, r) {
    override fun generateTranslations(registryLookup: HolderLookup.Provider, gen: TranslationBuilder) {
        TerastalItems.register { id, item -> gen.add(item, genLang(id)) }
        TerastalTabs.TERASTAL_TAB.let { gen.add(it, genLang(it.location())) }
    }

    private fun genLang(identifier: ResourceLocation): String =
        identifier.path.split("_").joinToString(" ") { it.replaceFirstChar(Char::uppercaseChar) }
}

class LootTables(o: FDOutput, r: FutureLookup) : FabricBlockLootTableProvider(o, r) {
    override fun generate() {
        TerastalBlocks.all().forEach(::dropSelf)
    }
}

class TBlockTags(o: FDOutput, r: FutureLookup) : FabricTagProvider.BlockTagProvider(o, r) {
    override fun addTags(arg: HolderLookup.Provider) {
        TerastalBlocks.all().forEach { block ->
            getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_PICKAXE).add(block)
        }
    }
}

class TItemTags(o: FDOutput, r: FutureLookup, b: BlockTagProvider) : FabricTagProvider.ItemTagProvider(o, r, b) {
    override fun addTags(arg: HolderLookup.Provider) {
    }
}

fun ItemModelGenerators.registerPlain(item: Item) = generateFlatItem(item, ModelTemplates.FLAT_ITEM)
