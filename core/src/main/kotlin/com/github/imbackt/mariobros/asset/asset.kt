package com.github.imbackt.mariobros.asset

import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.maps.tiled.TiledMap

enum class TiledMapAssets(
    fileName: String,
    val descriptor: AssetDescriptor<TiledMap> = AssetDescriptor(fileName, TiledMap::class.java)
) {
    LEVEL1_1("maps/1-1.tmx")
}

enum class TextureAtlasAssets(
    fileName: String,
    val descriptor: AssetDescriptor<TextureAtlas> = AssetDescriptor(fileName, TextureAtlas::class.java)
)