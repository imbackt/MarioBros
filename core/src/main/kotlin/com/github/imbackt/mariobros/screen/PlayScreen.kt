package com.github.imbackt.mariobros.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.maps.objects.RectangleMapObject
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.maps.tiled.TmxMapLoader
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType.StaticBody
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer
import com.github.imbackt.mariobros.MarioBros
import com.github.imbackt.mariobros.PPM
import com.github.imbackt.mariobros.aprite.Mario
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
    private val renderer = OrthogonalTiledMapRenderer(map, 1f / PPM)
    private val box2DDebugRenderer = Box2DDebugRenderer()
    private val mario = Mario(world)

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
                world.body {
                    type = StaticBody
                    position.set(
                        (it.rectangle.x / PPM + it.rectangle.width / 2 / PPM),
                        (it.rectangle.y / PPM + it.rectangle.height / 2 / PPM)
                    )
                    box(it.rectangle.width / PPM, it.rectangle.height / PPM)
                }
            }
        }
    }

    override fun render(delta: Float) {
        world.step(1 / 60f, 6, 2)
        gameViewport.apply()
        gameViewport.camera.position.x = mario.body.position.x
        renderer.setView(gameViewport.camera as OrthographicCamera)
        renderer.render()
        stage.run {
            viewport.apply()
            act()
            draw()
        }
        box2DDebugRenderer.render(world, gameViewport.camera.combined)

        when {
            Gdx.input.isKeyJustPressed(Input.Keys.UP) ->
                mario.body.applyLinearImpulse(Vector2(0f, 4f), mario.body.worldCenter, true)
            Gdx.input.isKeyPressed(Input.Keys.RIGHT) && mario.body.linearVelocity.x <= 2 ->
                mario.body.applyLinearImpulse(Vector2(0.1f, 0f), mario.body.worldCenter, true)
            Gdx.input.isKeyPressed(Input.Keys.RIGHT) && mario.body.linearVelocity.x >= 2 ->
                mario.body.applyLinearImpulse(Vector2(-0.1f, 0f), mario.body.worldCenter, true)
        }
    }
}