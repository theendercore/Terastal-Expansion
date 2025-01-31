package com.theendercore.terastal_expansion.client.ui

import com.cobblemon.mod.common.battles.InBattleMove
import com.cobblemon.mod.common.battles.ShowdownMoveset
import com.cobblemon.mod.common.client.gui.battle.subscreen.BattleGimmickButton
import com.cobblemon.mod.common.client.gui.battle.subscreen.BattleMoveSelection

class TerastalButton(moveSelection: BattleMoveSelection, x: Float, y: Float) :
    BattleGimmickButton(ShowdownMoveset.Gimmick.TERASTALLIZATION, x, y) {
    override var tiles = moveSelection.baseTiles.map { tile -> TerastalTile(moveSelection, tile.move, tile.x, tile.y) }

    class TerastalTile(moveSelection: BattleMoveSelection, move: InBattleMove, x: Float, y: Float) :
        GimmickTile(ShowdownMoveset.Gimmick.TERASTALLIZATION, moveSelection, move, x, y) {
        init {
            rgb = Triple(rgb.first + 0.25, rgb.second + 0.25, rgb.third + 0.25)
        }

        override val selectable: Boolean get() = gimmickMove == null || gimmickMove?.disabled == false
    }
}