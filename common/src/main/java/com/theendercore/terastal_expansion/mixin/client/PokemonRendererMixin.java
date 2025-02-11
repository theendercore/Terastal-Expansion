package com.theendercore.terastal_expansion.mixin.client;

import com.cobblemon.mod.common.client.render.pokemon.PokemonRenderer;
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.theendercore.terastal_expansion.client.RenderingKt.renderTeraType;
//import static com.theendercore.terastal_expansion.misc.HelpersKt.renderPokemonDebugText;
import static com.theendercore.terastal_expansion.misc.MixinKt.getTerastallizedType;

@Mixin(value = PokemonRenderer.class, remap = false)
public abstract class PokemonRendererMixin<T extends Entity> extends EntityRenderer<T> {
    protected PokemonRendererMixin(EntityRendererProvider.Context context) {
        super(context);
    }

    @Inject(method = "render(Lcom/cobblemon/mod/common/entity/pokemon/PokemonEntity;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at = @At("TAIL"))
    void debugText(PokemonEntity entity, float entityYaw, float partialTicks, PoseStack poseMatrix, MultiBufferSource buffer, int packedLight, CallbackInfo ci) {
//        renderPokemonDebugText(poseMatrix, buffer, this.entityRenderDispatcher, entity);
        var terastallizedType = getTerastallizedType(entity.getPokemon());
        if (terastallizedType != null) {
            renderTeraType(entity, terastallizedType);
        }
    }
}
