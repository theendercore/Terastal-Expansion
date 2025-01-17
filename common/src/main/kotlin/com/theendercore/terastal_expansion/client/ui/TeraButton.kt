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
        override val selectable: Boolean get() = gimmickMove?.disabled == false
    }
}