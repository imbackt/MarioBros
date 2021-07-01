package com.github.imbackt.mariobros.aprite

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType.DynamicBody
import com.badlogic.gdx.physics.box2d.World
import com.github.imbackt.mariobros.PPM
import ktx.box2d.body
import ktx.box2d.circle

class Mario(world: World) : Sprite() {
    val body = world.body {
        position.set(32f / PPM, 64f / PPM)
        type = DynamicBody
        circle(radius = 5f / PPM)
    }
}