package com.kata.ticktactoe

import org.junit.Assert.*
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

    @Test
    fun testShouldPassIfPlayerMoveIsValidAndStored() {
        assertNotNull(ticTacToeViewModel.storePlayerMoves(1, TicTacToeViewModel.PLAYER_X_ID))
        assertSame(ticTacToeViewModel.getPlayBoardByIndex(1), TicTacToeViewModel.PLAYER_X_ID)
    }

    @Test
    fun testShouldReturnFalseIfSelectedPositionHasAlreadyTakenByAnyPlayer() {
        assertTrue(ticTacToeViewModel.storePlayerMoves(1, TicTacToeViewModel.PLAYER_X_ID))
        assertFalse(ticTacToeViewModel.storePlayerMoves(1, TicTacToeViewModel.PLAYER_X_ID))
        assertFalse(ticTacToeViewModel.storePlayerMoves(1, TicTacToeViewModel.PLAYER_O_ID))
        assertTrue(ticTacToeViewModel.storePlayerMoves(2, TicTacToeViewModel.PLAYER_O_ID))
    }

    @Test
    fun testShouldPassIfPlayerSwappedAfterEachValidMove() {
        assertTrue(ticTacToeViewModel.getCurrentPlayer() == TicTacToeViewModel.PLAYER_X_ID)
        assertTrue(ticTacToeViewModel.storePlayerMoves(1, TicTacToeViewModel.PLAYER_X_ID))
        assertTrue(ticTacToeViewModel.getCurrentPlayer() == TicTacToeViewModel.PLAYER_O_ID)
        assertFalse(ticTacToeViewModel.getCurrentPlayer() == TicTacToeViewModel.PLAYER_X_ID)

    }

    @Test
    fun testShouldPassIfAnyPlayerHaveWonByRowWhenFirstIndexOfRowIsNotEmptyAndOtherTwoIndicesAreSame() {
        assertTrue(ticTacToeViewModel.storePlayerMoves(3, TicTacToeViewModel.PLAYER_X_ID))
        assertTrue(ticTacToeViewModel.storePlayerMoves(4, TicTacToeViewModel.PLAYER_X_ID))
        assertTrue(ticTacToeViewModel.storePlayerMoves(5, TicTacToeViewModel.PLAYER_X_ID))
        assertTrue(ticTacToeViewModel.isWinnerByRow())
    }

    @Test
    fun testShouldReturnFalseIfAlternatePlayersHavePlayedOnTheSimilarRow() {
        assertTrue(ticTacToeViewModel.storePlayerMoves(6, TicTacToeViewModel.PLAYER_X_ID))
        assertTrue(ticTacToeViewModel.storePlayerMoves(7, TicTacToeViewModel.PLAYER_O_ID))
        assertTrue(ticTacToeViewModel.storePlayerMoves(8, TicTacToeViewModel.PLAYER_O_ID))
        assertFalse(ticTacToeViewModel.isWinnerByRow())
    }

    @Test
    fun testShouldPassIfAnyPlayerHaveWonByRowWhenFirstIndexOfColumnIsNotEmptyAndOtherTwoIndicesAreSame() {
        assertTrue(ticTacToeViewModel.storePlayerMoves(1, TicTacToeViewModel.PLAYER_X_ID))
        assertTrue(ticTacToeViewModel.storePlayerMoves(4, TicTacToeViewModel.PLAYER_X_ID))
        assertTrue(ticTacToeViewModel.storePlayerMoves(7, TicTacToeViewModel.PLAYER_X_ID))
        assertTrue(ticTacToeViewModel.isWinnerByColumn())
    }

    @Test
    fun testShouldReturnFalseIfAlternatePlayersHavePlayedOnTheSimilarColumn() {
        assertTrue(ticTacToeViewModel.storePlayerMoves(1, TicTacToeViewModel.PLAYER_X_ID))
        assertTrue(ticTacToeViewModel.storePlayerMoves(4, TicTacToeViewModel.PLAYER_O_ID))
        assertTrue(ticTacToeViewModel.storePlayerMoves(7, TicTacToeViewModel.PLAYER_X_ID))
        assertFalse(ticTacToeViewModel.isWinnerByColumn())
    }


    @Test
    fun testShouldPassIfAnyPlayerHaveWonByDiagonal() {
        assertTrue(ticTacToeViewModel.storePlayerMoves(2, TicTacToeViewModel.PLAYER_O_ID))
        assertTrue(ticTacToeViewModel.storePlayerMoves(4, TicTacToeViewModel.PLAYER_O_ID))
        assertTrue(ticTacToeViewModel.storePlayerMoves(6, TicTacToeViewModel.PLAYER_O_ID))
        assertTrue(ticTacToeViewModel.isWinnerByDiagonal())
    }



}
