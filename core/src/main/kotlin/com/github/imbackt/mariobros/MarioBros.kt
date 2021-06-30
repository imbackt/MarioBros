package com.github.imbackt.mariobros

import com.github.imbackt.mariobros.screen.PlayScreen
import com.github.imbackt.mariobros.screen.MarioScreen
import ktx.app.KtxGame

const val V_WIDTH = 400
const val V_HEIGHT = 224

class MarioBros : KtxGame<MarioScreen>() {
    override fun create() {
        addScreen(PlayScreen())
        setScreen<PlayScreen>()
    }
}