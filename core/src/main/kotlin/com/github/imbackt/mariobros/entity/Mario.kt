package com.github.imbackt.mariobros.entity

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.World
import com.github.imbackt.mariobros.UNIT_SCALE
import ktx.box2d.body
import ktx.box2d.circle

class Mario(
    private val world: World
) : Sprite() {

    val body = defineMario()

    private fun defineMario(): Body {
        return world.body {
            type = BodyDef.BodyType.DynamicBody
            position.set(32f * UNIT_SCALE, 32f * UNIT_SCALE)
            circle(5f * UNIT_SCALE)
        }
    }
}