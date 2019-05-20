package com.kata.ticktactoe

import org.junit.Assert.assertTrue
import org.junit.Test


class TicTacToeViewModelUnitTest {

    private var ticTacToeViewModel = TicTacToeViewModel()

    @Test
    fun testShouldReturnTrueIfAllMovesAreLegal() {
        (0..2).forEach { i ->
            (0..2).forEach { j ->
                assertTrue(ticTacToeViewModel.getBoard()[i][j] == 0)
            }
        }
    }

    @Test
    fun testShouldReturnTrueIfFirstMoveIsByPlayerX() {
        assertTrue(ticTacToeViewModel.getCurrentPlayer() == TicTacToeViewModel.PLAYER_X_ID)
    }


}
