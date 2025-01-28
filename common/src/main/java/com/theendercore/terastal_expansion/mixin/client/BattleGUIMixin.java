package com.theendercore.terastal_expansion.mixin.client;

import com.cobblemon.mod.common.battles.MoveActionResponse;
import com.cobblemon.mod.common.battles.ShowdownActionResponse;
import com.cobblemon.mod.common.client.battle.SingleActionRequest;
import com.cobblemon.mod.common.client.gui.battle.BattleGUI;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static com.theendercore.terastal_expansion.misc.HelpersKt.helpMeEscapeHell;
import static com.theendercore.terastal_expansion.misc.HelpersKt.makeText;

@Mixin(value = BattleGUI.class, remap = false)
public class BattleGUIMixin extends Screen {

    protected BattleGUIMixin(Component title) {
        super(title);
    }

    @Inject(method = "selectAction", at = @At(value = "INVOKE", target = "Lcom/cobblemon/mod/common/client/gui/battle/BattleGUI;changeActionSelection(Lcom/cobblemon/mod/common/client/gui/battle/subscreen/BattleActionSelection;)V"))
    void x(SingleActionRequest request, ShowdownActionResponse response, CallbackInfo ci) {
        if (response instanceof MoveActionResponse moves) {
            var player = minecraft.player;
            if (player == null) return;
            var text = helpMeEscapeHell(request, moves);
            player.sendSystemMessage(makeText("Showdown Move: " + text, 0));
        }
    }

}
