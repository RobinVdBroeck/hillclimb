package com.robinvdb.ai

data class Vector2(var x: Int, var y: Int) {
    companion object {
        fun add(first: Vector2, second: Vector2): Vector2 {
            return Vector2(first.x + second.x, first.y + second.y)
        }

        fun distance(first: Vector2, second: Vector2): Int {
            return Math.abs(first.x - second.x) + Math.abs(first.y - second.y)
        }
    }
}
