package com.theendercore.terastal_expansion.mixin;

import com.cobblemon.mod.common.client.render.models.blockbench.PosableEntityModel;
import com.cobblemon.mod.common.client.render.models.blockbench.pokemon.PosablePokemonEntityModel;
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Mixin;

import static com.theendercore.terastal_expansion.misc.HelpersKt.getTeraState;
import static com.theendercore.terastal_expansion.misc.HelpersKt.getTeraTypeColor;

@Mixin(value = PosablePokemonEntityModel.class, remap = false)
public abstract class PosablePokemonEntityModelMixin<T extends Entity> extends PosableEntityModel<T> {
    @Override
    public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer buffer, int packedLight, int packedOverlay, int color) {
        var entity = this.getContext().getEntity();
        if (entity instanceof PokemonEntity pokemonEntity && getTeraState(pokemonEntity.getPokemon())) {
            var col = getTeraTypeColor(pokemonEntity.getPokemon().getTeraType());
            Minecraft.getInstance().player.displayClientMessage(Component.literal(col.toString()), true);
            Minecraft.getInstance().particleEngine.createTrackingEmitter(
                    pokemonEntity, new DustParticleOptions(new Vector3f(col.getRed(), col.getGreen(), col.getBlue()), 1f)
            );
        }
        super.renderToBuffer(poseStack, buffer, packedLight, packedOverlay, color);
    }
}
