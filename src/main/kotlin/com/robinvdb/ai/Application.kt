package com.robinvdb.ai

import com.robinvdb.ai.board.Board

fun main(args: Array<String>) {
    val board = Board(5, 5).apply {
        setStart(4, 2)
        setGoal(0, 0)
        setBlack(2, 2)
    }
    println("Empty board")
    board.print()


    println("Hill climb 1")
    val hillclimb: AI = HillClimb(board)
    val path = hillclimb.calculatePath()
    println("Moves: " + path.size)
    path.forEach {
        println("x=${it.x}, y=${it.y}")
    }
    board.print(path)
}

