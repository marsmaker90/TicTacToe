package com.kata.tictactoe

import org.junit.Assert.*
import org.junit.Test


class TicTacToeViewModelUnitTest {

    private var ticTacToeViewModel = TicTacToeViewModel()

    @Test
    fun testShouldReturnTrueIfAllMovesAreLegal() {
        ticTacToeViewModel.resetPlayBoard()
        (0..2).forEach { i ->
            (0..2).forEach { j ->
                assertTrue(ticTacToeViewModel.getBoard()[i][j] == 0)
            }
        }
    }

    @Test
    fun testShouldReturnTrueIfFirstMoveIsByPlayerX() {
        ticTacToeViewModel.resetPlayBoard()
        assertTrue(ticTacToeViewModel.getCurrentPlayer() == TicTacToeViewModel.PLAYER_X_ID)
    }

    @Test
    fun testShouldPassIfPlayerMoveIsValidAndStored() {
        ticTacToeViewModel.resetPlayBoard()
        assertNotNull(ticTacToeViewModel.storePlayerMoves(1, TicTacToeViewModel.PLAYER_X_ID))
        assertSame(ticTacToeViewModel.getPlayBoardByIndex(1), TicTacToeViewModel.PLAYER_X_ID)
    }

    @Test
    fun testShouldReturnFalseIfSelectedPositionHasAlreadyTakenByAnyPlayer() {
        ticTacToeViewModel.resetPlayBoard()
        assertTrue(ticTacToeViewModel.storePlayerMoves(1, TicTacToeViewModel.PLAYER_X_ID).first)
        assertFalse(ticTacToeViewModel.storePlayerMoves(1, TicTacToeViewModel.PLAYER_X_ID).first)
        assertFalse(ticTacToeViewModel.storePlayerMoves(1, TicTacToeViewModel.PLAYER_O_ID).first)
        assertTrue(ticTacToeViewModel.storePlayerMoves(2, TicTacToeViewModel.PLAYER_O_ID).first)
    }

    @Test
    fun testShouldPassIfPlayerSwappedAfterEachValidMove() {
        ticTacToeViewModel.resetPlayBoard()
        assertTrue(ticTacToeViewModel.getCurrentPlayer() == TicTacToeViewModel.PLAYER_X_ID)
        assertTrue(ticTacToeViewModel.storePlayerMoves(1, TicTacToeViewModel.PLAYER_X_ID).first)
        assertTrue(ticTacToeViewModel.getCurrentPlayer() == TicTacToeViewModel.PLAYER_O_ID)
        assertFalse(ticTacToeViewModel.getCurrentPlayer() == TicTacToeViewModel.PLAYER_X_ID)

    }

    @Test
    fun testShouldPassIfAnyPlayerHaveWonByRowWhenFirstIndexOfRowIsNotEmptyAndOtherTwoIndicesAreSame() {
        ticTacToeViewModel.resetPlayBoard()
        assertTrue(ticTacToeViewModel.storePlayerMoves(3, TicTacToeViewModel.PLAYER_X_ID).first)
        assertTrue(ticTacToeViewModel.storePlayerMoves(4, TicTacToeViewModel.PLAYER_X_ID).first)
        assertTrue(ticTacToeViewModel.storePlayerMoves(5, TicTacToeViewModel.PLAYER_X_ID).first)
        assertTrue(ticTacToeViewModel.isWinnerByRow())
    }

    @Test
    fun testShouldReturnFalseIfAlternatePlayersHavePlayedOnTheSimilarRow() {
        ticTacToeViewModel.resetPlayBoard()
        assertTrue(ticTacToeViewModel.storePlayerMoves(6, TicTacToeViewModel.PLAYER_X_ID).first)
        assertTrue(ticTacToeViewModel.storePlayerMoves(7, TicTacToeViewModel.PLAYER_O_ID).first)
        assertTrue(ticTacToeViewModel.storePlayerMoves(8, TicTacToeViewModel.PLAYER_O_ID).first)
        assertFalse(ticTacToeViewModel.isWinnerByRow())
    }

    @Test
    fun testShouldPassIfAnyPlayerHaveWonByRowWhenFirstIndexOfColumnIsNotEmptyAndOtherTwoIndicesAreSame() {
        ticTacToeViewModel.resetPlayBoard()
        assertTrue(ticTacToeViewModel.storePlayerMoves(1, TicTacToeViewModel.PLAYER_X_ID).first)
        assertTrue(ticTacToeViewModel.storePlayerMoves(4, TicTacToeViewModel.PLAYER_X_ID).first)
        assertTrue(ticTacToeViewModel.storePlayerMoves(7, TicTacToeViewModel.PLAYER_X_ID).first)
        assertTrue(ticTacToeViewModel.isWinnerByColumn())
    }

    @Test
    fun testShouldReturnFalseIfAlternatePlayersHavePlayedOnTheSimilarColumn() {
        ticTacToeViewModel.resetPlayBoard()
        assertTrue(ticTacToeViewModel.storePlayerMoves(1, TicTacToeViewModel.PLAYER_X_ID).first)
        assertTrue(ticTacToeViewModel.storePlayerMoves(4, TicTacToeViewModel.PLAYER_O_ID).first)
        assertTrue(ticTacToeViewModel.storePlayerMoves(7, TicTacToeViewModel.PLAYER_X_ID).first)
        assertFalse(ticTacToeViewModel.isWinnerByColumn())
    }


    @Test
    fun testShouldPassIfAnyPlayerHaveWonByDiagonal() {
        ticTacToeViewModel.resetPlayBoard()
        assertTrue(ticTacToeViewModel.storePlayerMoves(2, TicTacToeViewModel.PLAYER_O_ID).first)
        assertTrue(ticTacToeViewModel.storePlayerMoves(4, TicTacToeViewModel.PLAYER_O_ID).first)
        assertTrue(ticTacToeViewModel.storePlayerMoves(6, TicTacToeViewModel.PLAYER_O_ID).first)
        assertTrue(ticTacToeViewModel.isWinnerByDiagonal())
    }

    @Test
    fun testShouldReturnFalseIfAlternatePlayersHavePlayedOnTheSimilarDiagonalPattern() {
        ticTacToeViewModel.resetPlayBoard()
        assertTrue(ticTacToeViewModel.storePlayerMoves(2, TicTacToeViewModel.PLAYER_X_ID).first)
        assertTrue(ticTacToeViewModel.storePlayerMoves(4, TicTacToeViewModel.PLAYER_O_ID).first)
        assertTrue(ticTacToeViewModel.storePlayerMoves(6, TicTacToeViewModel.PLAYER_X_ID).first)
        assertFalse(ticTacToeViewModel.isWinnerByDiagonal())
    }


    @Test
    fun testShouldReturnTrueIfGameIsFinishedByExhaustingAllMovesForBothThePlayers() {
        ticTacToeViewModel.resetPlayBoard()
        assertTrue(ticTacToeViewModel.storePlayerMoves(4, TicTacToeViewModel.PLAYER_X_ID).first)
        assertTrue(ticTacToeViewModel.storePlayerMoves(0, TicTacToeViewModel.PLAYER_O_ID).first)
        assertTrue(ticTacToeViewModel.storePlayerMoves(1, TicTacToeViewModel.PLAYER_X_ID).first)
        assertTrue(ticTacToeViewModel.storePlayerMoves(7, TicTacToeViewModel.PLAYER_O_ID).first)
        assertTrue(ticTacToeViewModel.storePlayerMoves(6, TicTacToeViewModel.PLAYER_X_ID).first)
        assertTrue(ticTacToeViewModel.storePlayerMoves(2, TicTacToeViewModel.PLAYER_O_ID).first)
        assertTrue(ticTacToeViewModel.storePlayerMoves(3, TicTacToeViewModel.PLAYER_X_ID).first)
        assertTrue(ticTacToeViewModel.storePlayerMoves(5, TicTacToeViewModel.PLAYER_O_ID).first)
        assertTrue(ticTacToeViewModel.storePlayerMoves(8, TicTacToeViewModel.PLAYER_X_ID).first)
        assertTrue(ticTacToeViewModel.isMatchDrawn())
    }

    @Test
    fun testShouldPassIfNoMovesAllowedToAnyPlayerIfOneOfThePlayerWinsTheMatch() {
        ticTacToeViewModel.resetPlayBoard()
        assertTrue(ticTacToeViewModel.storePlayerMoves(0, TicTacToeViewModel.PLAYER_X_ID).first)
        assertTrue(ticTacToeViewModel.storePlayerMoves(1, TicTacToeViewModel.PLAYER_X_ID).first)
        assertTrue(ticTacToeViewModel.storePlayerMoves(2, TicTacToeViewModel.PLAYER_X_ID).first)
        assertTrue(ticTacToeViewModel.isWinnerByRow())
        assertFalse(ticTacToeViewModel.storePlayerMoves(8, TicTacToeViewModel.PLAYER_X_ID).first)
    }

    @Test
    fun testShouldPassIfPlayerHasWonTheMatch() {
        ticTacToeViewModel.resetPlayBoard()
        assertTrue(ticTacToeViewModel.storePlayerMoves(0, TicTacToeViewModel.PLAYER_X_ID).first)
        assertTrue(ticTacToeViewModel.storePlayerMoves(1, TicTacToeViewModel.PLAYER_X_ID).first)
        assertTrue(ticTacToeViewModel.storePlayerMoves(2, TicTacToeViewModel.PLAYER_X_ID).first)
        assertNotEquals("", ticTacToeViewModel.identifyIfAnyPlayerHadWon())
        assertNotSame("Match Drawn", ticTacToeViewModel.identifyIfAnyPlayerHadWon())
    }

}
