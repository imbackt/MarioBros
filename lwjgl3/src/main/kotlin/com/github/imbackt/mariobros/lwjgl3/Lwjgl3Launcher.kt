package com.github.imbackt.mariobros.lwjgl3

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration
import com.github.imbackt.mariobros.MarioBros
import com.github.imbackt.mariobros.V_HEIGHT
import com.github.imbackt.mariobros.V_WIDTH

fun main() {
    Lwjgl3Application(MarioBros(), Lwjgl3ApplicationConfiguration().apply {
        setTitle("DarkMatter")
        setWindowedMode(V_WIDTH * 2, V_HEIGHT * 2)
        setWindowIcon("libgdx128.png", "libgdx64.png", "libgdx32.png", "libgdx16.png")
    })
}