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
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer
import com.github.imbackt.mariobros.MarioBros
import com.github.imbackt.mariobros.UNIT_SCALE
import com.github.imbackt.mariobros.asset.TiledMapAssets
import com.github.imbackt.mariobros.entity.Mario
import ktx.box2d.body
import ktx.box2d.box
import ktx.math.vec2
import ktx.scene2d.actors
import ktx.scene2d.label
import ktx.scene2d.table
import ktx.style.label
import ktx.style.skin
import ktx.tiled.width

class PlayScreen(game: MarioBros) : MarioScreen(game) {
    private var worldTimer = 300
    private var score = 0

    private val map: TiledMap = TmxMapLoader().load("maps/1-1.tmx")
    private val renderer = OrthogonalTiledMapRenderer(map, UNIT_SCALE)

    private val box2DDebugRenderer = Box2DDebugRenderer()

    private val defaultSkin = skin {
        label {
            font = BitmapFont()
            fontColor = Color.WHITE
        }
    }

    private val mario = Mario(world)

    override fun show() {
        stage.actors {
            table {
                top()
                defaults().expandX()
                padTop(10f)
                label("MARIO", skin = defaultSkin)
                label("WORLD", skin = defaultSkin)
                label("TIME", skin = defaultSkin)
                row()
                label(score.toString().padStart(6, '0'), skin = defaultSkin)
                label("1-1", skin = defaultSkin)
                label(worldTimer.toString().padStart(3, '0'), skin = defaultSkin)
                setFillParent(true)
                pack()
            }
        }

        for (i in 2..5) {
            map.layers[i].objects.getByType(RectangleMapObject::class.java).forEach {
                world.body {
                    type = BodyDef.BodyType.StaticBody
                    position.set(
                        (it.rectangle.x + it.rectangle.width / 2) * UNIT_SCALE,
                        (it.rectangle.y + it.rectangle.height / 2) * UNIT_SCALE
                    )
                    box(it.rectangle.width * UNIT_SCALE, it.rectangle.height * UNIT_SCALE)
                }
            }
        }
        println(map.width)
    }

    override fun render(delta: Float) {
        world.step(1 / 30f, 6, 2)
        gameViewport.camera.position.x = MathUtils.clamp(mario.body.position.x, gameViewport.worldWidth / 2, map.width - gameViewport.worldWidth / 2)
        gameViewport.apply()
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
                mario.body.applyLinearImpulse(vec2(0f, 10f), mario.body.worldCenter, true)
            Gdx.input.isKeyPressed(Input.Keys.RIGHT) && mario.body.linearVelocity.x <= 2f ->
                mario.body.applyLinearImpulse(vec2(0.2f, 0f), mario.body.worldCenter, true)
            Gdx.input.isKeyPressed(Input.Keys.LEFT) && mario.body.linearVelocity.x >= -2f ->
                mario.body.applyLinearImpulse(vec2(-0.2f, 0f), mario.body.worldCenter, true)
        }
    }
}