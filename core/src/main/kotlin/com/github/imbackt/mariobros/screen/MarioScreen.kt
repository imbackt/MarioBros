package com.github.imbackt.mariobros.screen

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.Viewport
import com.github.imbackt.mariobros.MarioBros
import ktx.app.KtxScreen

abstract class MarioScreen(
    val game: MarioBros,
    val batch: Batch = game.batch,
    val gameViewport: Viewport = game.gameViewport,
    val hudViewport: Viewport = game.hudViewport,
    val stage: Stage = game.stage,
    val world: World = game.world
) : KtxScreen {
    override fun resize(width: Int, height: Int) {
        gameViewport.update(width, height, true)
        hudViewport.update(width, height, true)
    }
}