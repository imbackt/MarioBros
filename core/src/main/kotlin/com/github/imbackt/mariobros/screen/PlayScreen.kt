package com.github.imbackt.mariobros.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.maps.MapObject
import com.badlogic.gdx.maps.objects.RectangleMapObject
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.maps.tiled.TmxMapLoader
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType.StaticBody
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer
import com.github.imbackt.mariobros.MarioBros
import ktx.box2d.body
import ktx.box2d.box
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

    private val map: TiledMap = TmxMapLoader().load("1-1.tmx")
    private val renderer = OrthogonalTiledMapRenderer(map)
    val box2DDebugRenderer = Box2DDebugRenderer()

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

        for (i in 2..5) {
            map.layers[i].objects.getByType(RectangleMapObject::class.java).forEach {
                val body = world.body {
                    type = StaticBody
                    position.set(it.rectangle.x + it.rectangle.width / 2, it.rectangle.y + it.rectangle.height / 2)
                    box(it.rectangle.width, it.rectangle.height)
                }
            }
        }
    }

    override fun render(delta: Float) {
        gameViewport.apply()
        renderer.setView(gameViewport.camera as OrthographicCamera)
        renderer.render()
        stage.run {
            viewport.apply()
            act()
            draw()
        }
        box2DDebugRenderer.render(world, gameViewport.camera.combined)
        if (Gdx.input.isTouched) {
            gameViewport.camera.position.x += 100 * delta
        }
    }
}