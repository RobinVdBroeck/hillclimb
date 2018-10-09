package com.robinvdb.ai.board

import com.robinvdb.ai.Vector2

data class Board(val width: Int, val height: Int) {

    private val cellMap = mutableMapOf<Vector2, Cell<CellState>>()

    init {
        fillBoard()
    }

    /**
     * Fills the board with empty cells
     */
    private fun fillBoard() {
        for (x in 0 until width) {
            for (y in 0 until height) {
                val cell = Cell(CellState.EMPTY, Vector2(x, y))
                cellMap[cell.position] = cell
            }
        }
    }

    /**
     * Returns the starting position
     */
    fun getStartPosition() = getFirstCell { cell -> cell.value == CellState.START }

    /**
     * Set the start position based on an x and y
     */
    fun setStart(x: Int, y: Int) {
        getCell(x, y).value = CellState.START
    }

    /**
     * Returns the goal position
     */
    fun getGoalPosition() = getFirstCell { cell -> cell.value == CellState.GOAL }

    /**
     * Set the goal position based on an x and y
     */
    fun setGoal(x: Int, y: Int) {
        getCell(x, y).value = CellState.GOAL
    }

    /**
     * Set a position to black
     */
    fun setBlack(x: Int, y: Int) {
        getCell(x, y).value = CellState.BLACK
    }

    private inline fun getFirstCell(filterFunction: (Cell<CellState>) -> Boolean) = cellMap.values.first(filterFunction)


    /**
     * @throws IndexOutOfBoundsException when x or y are invalid
     */
    fun getCell(position: Vector2) = cellMap[position] ?: throw IndexOutOfBoundsException()


    /**
     * @throws IndexOutOfBoundsException when x or y are invalid
     */
    fun getCell(x: Int, y: Int) = getCell(Vector2(x, y))


    fun print(path: List<Vector2> = emptyList()) {
        val cellWidth = 5
        fun printLine() = println("-".repeat((width) * cellWidth))

        printLine()
        for(y in height - 1 downTo 0) {
            for(x in 0 until width) {
                val cell = getCell(x, y)

                val stringRepresentation = ("| " + when(cell.value) {
                    CellState.START -> "S"
                    CellState.GOAL -> "G"
                    CellState.BLACK -> "B"
                    else -> {
                        val pos = Vector2(x,y)
                        if(path.contains(pos)) {
                            path.indexOf(pos).toString(10)
                        } else {
                            " "
                        }
                    }
                }).padEnd(cellWidth, ' ')
                print(stringRepresentation)
            }
            println()
            printLine()
        }
    }
}
