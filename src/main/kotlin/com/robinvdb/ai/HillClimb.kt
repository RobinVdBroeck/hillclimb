package com.robinvdb.ai

import com.robinvdb.ai.board.Board
import com.robinvdb.ai.board.Cell
import com.robinvdb.ai.board.CellState
import com.robinvdb.ai.tree.Node


class HillClimb(private val board: Board) : AI {
    private val visited = mutableSetOf<Vector2>()
    private val start = board.getStartPosition()
    private val end = board.getGoalPosition()

    private fun validMove(newPosition: Vector2): Boolean {
        // 1. Is the position already visited?
        if (visited.contains(newPosition)) return false

        // 2. Is the position still on the board?
        if (newPosition.x !in (0 until board.width) || newPosition.y !in (0 until board.height)) return false

        // 3. Is it not black?
        val cell = board.getCell(newPosition)
        if (cell.value == CellState.BLACK) return false

        return true
    }


    private fun getChildren(node: Node<Cell<CellState>>): List<Node<Cell<CellState>>> {
        val children = mutableListOf<Node<Cell<CellState>>>()

        for (move in enumValues<Direction>()) {
            val newPosition = Vector2.add(node.value.position, move.value)

            if (!validMove(newPosition)) {
                continue
            }
            visited.add(newPosition)

            val cell = board.getCell(newPosition)
            val child = createNode(cell, end)
            children.add(child)
        }

        return children
    }

    private fun <T> heurastic(cell: Cell<T>, end: Cell<T>) = Vector2.distance(cell.position, end.position)

    private fun <T> createNode(cell: Cell<T>, end: Cell<T>): Node<Cell<T>> {
        return Node(cell, heurastic(cell, end))
    }

    override fun calculatePath(): List<Vector2> {
        val root = createNode(start, end)
        var pointer = root
        val path = mutableListOf<Vector2>()
        path.add(pointer.value.position)

        // Loop untill pointer = end
        while (pointer.value.position != end.position) {
            // Calculate the children
            pointer.children.addAll(getChildren(pointer))


            // If min is null: we had no children
            val min = pointer.minBy { it.heurastic } ?: return path

            pointer.visited = true

            pointer = min
            path.add(pointer.value.position)
        }

        return path
    }

}
