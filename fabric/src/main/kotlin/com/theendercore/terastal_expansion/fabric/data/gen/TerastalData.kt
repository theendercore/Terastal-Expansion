package com.theendercore.terastal_expansion.fabric.data.gen

import com.cobblemon.mod.common.block.GrowableStoneBlock
import com.theendercore.terastal_expansion.data.TerastalTags
import com.theendercore.terastal_expansion.init.TerastalBlocks
import com.theendercore.terastal_expansion.init.TerastalTabs
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.*
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.Registries
import net.minecraft.data.models.BlockModelGenerators
import net.minecraft.data.models.ItemModelGenerators
import net.minecraft.data.models.model.ModelTemplates
import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.data.recipes.RecipeOutput
import net.minecraft.data.recipes.RecipeProvider.has
import net.minecraft.data.recipes.ShapedRecipeBuilder
import net.minecraft.data.recipes.ShapelessRecipeBuilder
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.BlockTags
import net.minecraft.tags.ItemTags
import net.minecraft.tags.TagKey
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraft.world.item.enchantment.Enchantments
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.storage.loot.LootPool.lootPool
import net.minecraft.world.level.storage.loot.LootTable.lootTable
import net.minecraft.world.level.storage.loot.entries.LootItem.lootTableItem
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue
import java.util.concurrent.CompletableFuture
import com.theendercore.terastal_expansion.init.TerastalItems as T

typealias Pack = FabricDataOutput
typealias FutureLookup = CompletableFuture<HolderLookup.Provider>

object TerastalData : DataGeneratorEntrypoint {
    override fun onInitializeDataGenerator(gen: FabricDataGenerator) {
        val pack = gen.createPack()
        pack.addProvider(::TModels)
        pack.addProvider(::EnLang)

        pack.addProvider(::LootTables)
        val block = pack.addProvider(::TBlockTags)
        pack.addProvider { o, r -> TItemTags(o, r, block) }
        pack.addProvider(::TRecipes)
    }


}

val shardsToGems = mapOf(
    T.TERA_GEM_SHARD to T.TERA_GEM,
    T.NORMAL_TERA_SHARD to T.NORMAL_TERA_GEM,
    T.FIRE_TERA_SHARD to T.FIRE_TERA_GEM,
    T.WATER_TERA_SHARD to T.WATER_TERA_GEM,
    T.ELECTRIC_TERA_SHARD to T.ELECTRIC_TERA_GEM,
    T.GRASS_TERA_SHARD to T.GRASS_TERA_GEM,
    T.ICE_TERA_SHARD to T.ICE_TERA_GEM,
    T.FIGHTING_TERA_SHARD to T.FIGHTING_TERA_GEM,
    T.POISON_TERA_SHARD to T.POISON_TERA_GEM,
    T.GROUND_TERA_SHARD to T.GROUND_TERA_GEM,
    T.FLYING_TERA_SHARD to T.FLYING_TERA_GEM,
    T.PSYCHIC_TERA_SHARD to T.PSYCHIC_TERA_GEM,
    T.BUG_TERA_SHARD to T.BUG_TERA_GEM,
    T.ROCK_TERA_SHARD to T.ROCK_TERA_GEM,
    T.GHOST_TERA_SHARD to T.GHOST_TERA_GEM,
    T.DRAGON_TERA_SHARD to T.DRAGON_TERA_GEM,
    T.DARK_TERA_SHARD to T.DARK_TERA_GEM,
    T.STEEL_TERA_SHARD to T.STEEL_TERA_GEM,
    T.FAIRY_TERA_SHARD to T.FAIRY_TERA_GEM,
    T.STELLAR_TERA_SHARD to T.STELLAR_TERA_GEM,
)

class TModels(o: Pack) : FabricModelProvider(o) {
    override fun generateBlockStateModels(gen: BlockModelGenerators) {
        gen.createTrivialCube(TerastalBlocks.TERA_GEM_BLOCK)
        var block = TerastalBlocks.SMALL_BUDDING_TERA_SHARD as? GrowableStoneBlock
        while (true) {
            gen.createAmethystCluster(block!!)
            gen.createSimpleFlatItemModel(block)
            block = block.nextStage as? GrowableStoneBlock
            if (block == null) break
        }
    }

    override fun generateItemModels(gen: ItemModelGenerators) =
        T.all().filter { it !is BlockItem }.forEach(gen::registerPlain)
}

class EnLang(o: Pack, r: FutureLookup) : FabricLanguageProvider(o, r) {
    override fun generateTranslations(registryLookup: HolderLookup.Provider, gen: TranslationBuilder) {
        T.register { id, item -> gen.add(item, genLang(id)) }
        TerastalTabs.TERASTAL_TAB.let { gen.add(it, genLang(it.location())) }
        TerastalTags.ITEM_TAGS.forEach { gen.add(it, genLang(it.location)) }
    }

    private fun genLang(identifier: ResourceLocation): String =
        identifier.path.split("_").joinToString(" ") { it.replaceFirstChar(Char::uppercaseChar) }
}

class LootTables(o: Pack, r: FutureLookup) : FabricBlockLootTableProvider(o, r) {
    override fun generate() {
        val registryLookup = registries.lookupOrThrow(Registries.ENCHANTMENT)
        listOf(
            TerastalBlocks.SMALL_BUDDING_TERA_SHARD,
            TerastalBlocks.MEDIUM_BUDDING_TERA_SHARD, TerastalBlocks.LARGE_BUDDING_TERA_SHARD
        ).forEach(::tumblestoneDrop)

        add(TerastalBlocks.TERA_GEM_BLOCK) {
            lootTable().withPool(
                lootPool().add(
                    lootTableItem(TerastalBlocks.TERA_GEM_BLOCK).`when`(hasSilkTouch()).otherwise(
                        lootTableItem(T.TERA_GEM_SHARD).apply(SetItemCountFunction.setCount(ConstantValue.exactly(4.0f)))
                    )
                )
            )
        }

        add(TerastalBlocks.TERA_SHARD_CLUSTER) {
            lootTable().withPool(
                lootPool().add(
                    lootTableItem(TerastalBlocks.TERA_SHARD_CLUSTER).`when`(hasSilkTouch())
                        .otherwise(
                            lootTableItem(T.TERA_GEM_SHARD)
                                .apply(SetItemCountFunction.setCount(ConstantValue.exactly(4.0f)))
                                .apply(ApplyBonusCount.addOreBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE)))
                        )
                )
            )
        }
    }
}

class TBlockTags(o: Pack, r: FutureLookup) : FabricTagProvider.BlockTagProvider(o, r) {
    override fun addTags(arg: HolderLookup.Provider) {
        TerastalBlocks.all().forEach { block ->
            getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_PICKAXE).add(block)
        }
    }
}

class TItemTags(o: Pack, r: FutureLookup, b: BlockTagProvider) : FabricTagProvider.ItemTagProvider(o, r, b) {
    override fun addTags(arg: HolderLookup.Provider) {
        shardsToGems.forEach { (shard, gem) ->
            getOrCreateTagBuilder(TerastalTags.TERA_SHARDS).add(shard)
            getOrCreateTagBuilder(TerastalTags.TERA_GEMS).add(gem)
        }
        getOrCreateTagBuilder(TerastalTags.POISON_ITEMS).add(Items.ROTTEN_FLESH, Items.POISONOUS_POTATO)
        getOrCreateTagBuilder(TerastalTags.PSYCHIC_ITEMS).add(Items.ENDER_PEARL, Items.OBSIDIAN)
        getOrCreateTagBuilder(TerastalTags.BUG_ITEMS).add(Items.SPIDER_EYE, Items.HONEY_BLOCK)
        getOrCreateTagBuilder(TerastalTags.GHOST_ITEMS).add(Items.SOUL_SAND, Items.SOUL_SOIL)
        getOrCreateTagBuilder(TerastalTags.DRAGON_ITEMS).add(Items.DRAGON_BREATH, Items.TURTLE_SCUTE)
        getOrCreateTagBuilder(TerastalTags.DARK_ITEMS).add(Items.BONE, Items.SCULK)
    }
}

class TRecipes(o: Pack, r: FutureLookup) : FabricRecipeProvider(o, r) {
    override fun buildRecipes(exporter: RecipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, T.TERA_ORB)
            .pattern("SSS")
            .pattern("ODO")
            .pattern("SSS")
            .define('S', T.TERA_GEM_SHARD)
            .define('D', Items.DIAMOND)
            .define('O', Items.OBSIDIAN)
            .unlockedBy("has_shard", has(T.TERA_GEM_SHARD))
            .save(exporter)

        twoByTwoPacker(exporter, RecipeCategory.MISC, TerastalBlocks.TERA_GEM_BLOCK, T.TERA_GEM)

        shardsToGems.forEach { (shard, gem) ->
            ShapedRecipeBuilder.shaped(RecipeCategory.MISC, gem)
                .pattern("SSS")
                .pattern("SSS")
                .pattern("SSS")
                .define('S', shard)
                .unlockedBy("has_gem", has(gem))
                .unlockedBy("has_shard", has(shard))
                .save(exporter)
        }

        twoByTwoPacker(exporter, RecipeCategory.MISC, T.STELLAR_TERA_SHARD, TerastalBlocks.TERA_GEM_BLOCK)

        exporter.shard(T.NORMAL_TERA_SHARD, ItemTags.WOOL)
        exporter.shard(T.GRASS_TERA_SHARD, Items.WHEAT_SEEDS)
        exporter.shard(T.FIRE_TERA_SHARD, Items.COAL)
        exporter.shard(T.WATER_TERA_SHARD, Items.WATER_BUCKET)
        exporter.shard(T.ELECTRIC_TERA_SHARD, Items.REDSTONE)
        exporter.shard(T.ICE_TERA_SHARD, Items.ICE)
        exporter.shard(T.FIGHTING_TERA_SHARD, ItemTags.SWORDS)
        exporter.shard(T.POISON_TERA_SHARD, TerastalTags.POISON_ITEMS)
        exporter.shard(T.GROUND_TERA_SHARD, Items.DIRT)
        exporter.shard(T.FLYING_TERA_SHARD, Items.FEATHER)
        exporter.shard(T.PSYCHIC_TERA_SHARD, TerastalTags.PSYCHIC_ITEMS)
        exporter.shard(T.BUG_TERA_SHARD, TerastalTags.BUG_ITEMS)
        exporter.shard(T.ROCK_TERA_SHARD, ConventionalItemTags.COBBLESTONES)
        exporter.shard(T.GHOST_TERA_SHARD, TerastalTags.GHOST_ITEMS)
        exporter.shard(T.DRAGON_TERA_SHARD, TerastalTags.DRAGON_ITEMS)
        exporter.shard(T.DARK_TERA_SHARD, TerastalTags.DARK_ITEMS)
        exporter.shard(T.STEEL_TERA_SHARD, Items.IRON_BLOCK)
        exporter.shard(T.FAIRY_TERA_SHARD, ItemTags.FLOWERS)
    }
}

fun RecipeOutput.shard(output: Item, add: Item) = ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, output)
    .requires(T.TERA_GEM_SHARD).requires(add)
    .unlockedBy("has_shard", has(T.TERA_GEM_SHARD))
    .save(this)

fun RecipeOutput.shard(output: Item, add: TagKey<Item>) = ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, output)
    .requires(T.TERA_GEM_SHARD).requires(add)
    .unlockedBy("has_shard", has(T.TERA_GEM_SHARD))
    .save(this)

fun FabricBlockLootTableProvider.tumblestoneDrop(block: Block, item: Item = T.TERA_GEM_SHARD) = add(block) {
    lootTable().withPool(lootPool().add(lootTableItem(block).`when`(hasSilkTouch()).otherwise(lootTableItem(item))))
}

fun ItemModelGenerators.registerPlain(item: Item) = generateFlatItem(item, ModelTemplates.FLAT_ITEM)
