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
import net.minecraft.data.client.BlockStateModelGenerator
import net.minecraft.data.client.ItemModelGenerator
import net.minecraft.data.client.Models
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.registry.RegistryWrapper
import net.minecraft.registry.tag.BlockTags
import net.minecraft.util.Identifier
import java.util.concurrent.CompletableFuture

typealias FDOutput = FabricDataOutput
typealias FutureLookup = CompletableFuture<RegistryWrapper.WrapperLookup>

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
    override fun generateBlockStateModels(gen: BlockStateModelGenerator) {
//        TerastalBlocks.all().forEach(gen::registerSimpleCubeAll)
        gen.registerSimpleCubeAll(TerastalBlocks.TERA_GEM_BLOCK)
        var block: TumblestoneBlock? = TerastalBlocks.SMALL_BUDDING_TERA_SHARD as TumblestoneBlock?
        while (true) {
            gen.registerAmethyst(block)
            gen.registerItemModel(block!!.asItem())
            block = block.nextStage as TumblestoneBlock?
            if (block == null) break

        }
    }

    override fun generateItemModels(gen: ItemModelGenerator) {
        TerastalItems.all().filter { it !is BlockItem }.forEach(gen::registerPlain)
    }
}

class EnLang(o: FDOutput) : FabricLanguageProvider(o) {
    override fun generateTranslations(gen: TranslationBuilder) {
        TerastalItems.register { id, item -> gen.add(item, genLang(id)) }
        TerastalTabs.TERASTAL_TAB.let { gen.add(it, genLang(it.value)) }
    }

    private fun genLang(identifier: Identifier): String =
        identifier.path.split("_").joinToString(" ") { it.replaceFirstChar(Char::uppercaseChar) }
}

class LootTables(o: FDOutput) : FabricBlockLootTableProvider(o) {
    override fun generate() {
        TerastalBlocks.all().forEach(::addDrop)
    }
}

class TBlockTags(o: FDOutput, r: FutureLookup) : FabricTagProvider.BlockTagProvider(o, r) {
    override fun configure(arg: RegistryWrapper.WrapperLookup) {
        TerastalBlocks.all().forEach { block ->
            getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE).add(block)
        }
    }
}

class TItemTags(o: FDOutput, r: FutureLookup, b: BlockTagProvider) : FabricTagProvider.ItemTagProvider(o, r, b) {
    override fun configure(arg: RegistryWrapper.WrapperLookup?) {
    }
}

fun ItemModelGenerator.registerPlain(item: Item) {
    register(item, Models.GENERATED)
}
