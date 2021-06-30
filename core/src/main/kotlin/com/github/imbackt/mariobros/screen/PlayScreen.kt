package com.github.imbackt.mariobros.screen

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.github.imbackt.mariobros.MarioBros
import ktx.scene2d.actors
import ktx.scene2d.label
import ktx.scene2d.table
import ktx.style.label
import ktx.style.skin

class PlayScreen(game: MarioBros) : MarioScreen(game) {
    private var worldTimer = 300
    private var score = 0

    private val customSkin = skin {
        label {
            font = BitmapFont()
            fontColor = Color.WHITE
        }
    }

    override fun show() {
        stage.actors {
            table {
                top()
                defaults().expandX()
                padTop(10f)
                label("MARIO", skin = customSkin)
                label("WORLD", skin = customSkin)
                label("TIME", skin = customSkin)
                row()
                label(score.toString().padStart(6, '0'), skin = customSkin)
                label("1-1", skin = customSkin)
                label(worldTimer.toString().padStart(3, '0'), skin = customSkin)
                setFillParent(true)
                pack()
            }
        }
    }

    override fun render(delta: Float) {
        stage.run {
            viewport.apply()
            act()
            draw()
        }
    }
}