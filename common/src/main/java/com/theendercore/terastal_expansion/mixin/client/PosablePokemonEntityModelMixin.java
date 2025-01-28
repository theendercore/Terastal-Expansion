package com.theendercore.terastal_expansion.mixin.client;

import com.cobblemon.mod.common.client.render.models.blockbench.PosableEntityModel;
import com.cobblemon.mod.common.client.render.models.blockbench.pokemon.PosablePokemonEntityModel;
import com.cobblemon.mod.common.client.render.models.blockbench.repository.RenderContext;
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(value = PosablePokemonEntityModel.class, remap = false)
public abstract class PosablePokemonEntityModelMixin<T extends Entity> extends PosableEntityModel<T> {
    @Override
    public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer buffer, int packedLight, int packedOverlay, int color) {
        var entity = this.getContext().request(RenderContext.Companion.getENTITY());
        if (entity instanceof PokemonEntity pokemonEntity);
        super.renderToBuffer(poseStack, buffer, packedLight, packedOverlay, color);
    }
}
