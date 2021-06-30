package com.github.imbackt.mariobros.screen

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.Viewport
import com.github.imbackt.mariobros.MarioBros
import ktx.app.KtxScreen

abstract class MarioScreen(
    val game: MarioBros,
    val batch: Batch = game.batch,
    val gameViewport: Viewport = game.gameViewport,
    val hudViewport: Viewport = game.hudViewport,
    val stage: Stage = game.stage
) : KtxScreen