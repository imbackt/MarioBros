package com.github.imbackt.mariobros

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.FitViewport
import com.github.imbackt.mariobros.screen.MarioScreen
import com.github.imbackt.mariobros.screen.PlayScreen
import ktx.app.KtxGame
import ktx.box2d.createWorld
import ktx.box2d.earthGravity

const val V_WIDTH = 25
const val V_HEIGHT = 14
const val V_WIDTH_PIXELS = 25 * 16
const val V_HEIGHT_PIXELS = 14 * 16
const val UNIT_SCALE = 1 / 16f

class MarioBros : KtxGame<MarioScreen>() {
    val gameViewport = FitViewport(V_WIDTH.toFloat(), V_HEIGHT.toFloat())
    val hudViewport = FitViewport(V_WIDTH_PIXELS.toFloat(), V_HEIGHT_PIXELS.toFloat())
    val batch: Batch by lazy { SpriteBatch() }
    val stage: Stage by lazy { Stage(hudViewport, batch) }
    val world: World by lazy { createWorld(earthGravity, true) }

    override fun create() {
        addScreen(PlayScreen(this))
        setScreen<PlayScreen>()
    }

    override fun resize(width: Int, height: Int) {
        gameViewport.update(width, height, true)
        hudViewport.update(width, height, true)
    }

    override fun dispose() {
        super.dispose()
        batch.dispose()
        stage.dispose()
        world.dispose()
    }
}