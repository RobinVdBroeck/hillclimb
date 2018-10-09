package com.robinvdb.ai.board

import com.robinvdb.ai.Vector2
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class BoardTests {
    @Test
    fun `Setting and getting the start position work`() {
        val board = Board(1, 1)
        board.setStart(0, 0)

        val expected = Cell(CellState.START, Vector2(0, 0))
        assertEquals(board.getStartPosition(), expected)
    }

    @Test
    fun `Setting and getting the end position work`() {
        val board = Board(1, 1)
        board.setGoal(0, 0)

        val expected = Cell(CellState.GOAL, Vector2(0, 0))
        assertEquals(board.getGoalPosition(), expected)
    }

    @Test
    fun `Creating a board fills them all with empty cells`() {
        val width = 10
        val height = 10

        val board = Board(width, height)

        for (x in 0 until width) {
            for (y in 0 until width) {
                val cell = board.getCell(x, y)
                assertEquals(CellState.EMPTY, cell.value)
            }
        }
    }


    @Test
    fun `When getting a cell that is not in the board, throw an exception`() {
        val board = Board(5, 5)

        fun posProvider() = listOf(
                Vector2(50,50),
                Vector2(5, 4),
                Vector2(4, 5),
                Vector2(-1, 4),
                Vector2(4, -1)
        )

        for(pos in posProvider()) {
            assertFailsWith<IndexOutOfBoundsException> {
                board.getCell(pos.x, pos.y)
            }
        }

    }
}