package com.robinvdb.ai

enum class Direction(val value: Vector2) {
    UP(Vector2(0, 1)),
    LEFT(Vector2(-1, 0)),
    RIGHT(Vector2(1, 0)),
    DOWN(Vector2(0, -1))
}
