package com.github.imbackt.mariobros

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.FitViewport
import com.github.imbackt.mariobros.screen.PlayScreen
import com.github.imbackt.mariobros.screen.MarioScreen
import ktx.app.KtxGame
import ktx.box2d.createWorld

const val V_WIDTH = 400
const val V_HEIGHT = 224

class MarioBros : KtxGame<MarioScreen>() {
    val gameViewport = FitViewport(V_WIDTH.toFloat(), V_HEIGHT.toFloat())
    val hudViewport = FitViewport(V_WIDTH.toFloat(), V_HEIGHT.toFloat())
    val batch: Batch by lazy { SpriteBatch() }
    val stage: Stage by lazy { Stage(hudViewport) }
    val world = createWorld()

    override fun create() {
        addScreen(PlayScreen(this))
        setScreen<PlayScreen>()
    }
}