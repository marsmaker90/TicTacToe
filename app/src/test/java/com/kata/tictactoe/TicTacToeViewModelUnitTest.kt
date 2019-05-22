package com.kata.tictactoe

import com.kata.tictactoe.utils.GameBoardPosition
import com.kata.tictactoe.utils.MatchStatus
import com.kata.tictactoe.utils.Player
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test


class TicTacToeViewModelUnitTest {

    private var ticTacToeViewModel = TicTacToeViewModel()

    @Before
    fun resetGameBoard() {
        ticTacToeViewModel.resetGameBoard()
    }

    @Test
    fun testShouldCheckWhetherTheFirstMoveAvailableForPlayerX() {
        assertEquals(ticTacToeViewModel.getCurrentPlayer(), Player.PLAYER_X_ID)
    }

    @Test
    fun testToStoreThePlayerMoveAndShouldCheckPlayerAtThatParticularIndexIsSameWhoStored() {
        assertNotNull(ticTacToeViewModel.storePlayerMoves(GameBoardPosition.INDEX_TOP_MIDDLE))
        assertSame(ticTacToeViewModel.getGameBoardByIndex(GameBoardPosition.INDEX_TOP_MIDDLE), Player.PLAYER_X_ID)
    }

    @Test
    fun testShouldCheckSwappingOfPlayerXtoPlayerOAfterMoveSuccessfullyStored() {
        assertEquals(ticTacToeViewModel.getCurrentPlayer(), Player.PLAYER_X_ID)
        ticTacToeViewModel.storePlayerMoves(GameBoardPosition.INDEX_TOP_MIDDLE)
        assertEquals(ticTacToeViewModel.getCurrentPlayer(), Player.PLAYER_O_ID)
        assertNotEquals(ticTacToeViewModel.getCurrentPlayer(), Player.PLAYER_X_ID)

    }

    @Test
    fun testShouldCheckSwappingOfPlayerOToPlayerXAfterMoveSuccessfullyStored() {
        ticTacToeViewModel.storePlayerMoves(GameBoardPosition.INDEX_TOP_LEFT)
        assertEquals(ticTacToeViewModel.getCurrentPlayer(), Player.PLAYER_O_ID)
        ticTacToeViewModel.storePlayerMoves(GameBoardPosition.INDEX_TOP_MIDDLE)
        assertEquals(ticTacToeViewModel.getCurrentPlayer(), Player.PLAYER_X_ID)
        assertNotEquals(ticTacToeViewModel.getCurrentPlayer(), Player.PLAYER_O_ID)

    }

    @Test
    fun testShouldCheckWhetherAnyPlayerWonByRow() {
        storePlayerMoves(
            intArrayOf(
                GameBoardPosition.INDEX_CENTER_LEFT,
                GameBoardPosition.INDEX_TOP_MIDDLE,
                GameBoardPosition.INDEX_CENTER_MIDDLE,
                GameBoardPosition.INDEX_TOP_RIGHT,
                GameBoardPosition.INDEX_CENTER_RIGHT
            )
        )
        assertEquals(ticTacToeViewModel.getMatchSummary().matchStatus, MatchStatus.WIN_BY_ROW)
    }

    @Test
    fun testShouldDeclineWinIfAlternatePlayersPlayedOnTheSameRow() {
        storePlayerMoves(
            intArrayOf(
                GameBoardPosition.INDEX_BOTTOM_LEFT,
                GameBoardPosition.INDEX_BOTTOM_MIDDLE,
                GameBoardPosition.INDEX_BOTTOM_RIGHT
            )
        )
        assertNotEquals(ticTacToeViewModel.getMatchSummary().matchStatus, MatchStatus.WIN_BY_ROW)
    }

    @Test
    fun testShouldCheckWhetherAnyPlayerWonByColumn() {

        storePlayerMoves(
            intArrayOf(
                GameBoardPosition.INDEX_TOP_MIDDLE,
                GameBoardPosition.INDEX_TOP_RIGHT,
                GameBoardPosition.INDEX_CENTER_MIDDLE,
                GameBoardPosition.INDEX_CENTER_LEFT,
                GameBoardPosition.INDEX_BOTTOM_MIDDLE
            )
        )

        assertEquals(ticTacToeViewModel.getMatchSummary().matchStatus, MatchStatus.WIN_BY_COLUMN)
    }

    @Test
    fun testShouldDeclineWinIfAlternatePlayersPlayedOnTheSameColumn() {
        storePlayerMoves(
            intArrayOf(
                GameBoardPosition.INDEX_TOP_MIDDLE,
                GameBoardPosition.INDEX_CENTER_MIDDLE,
                GameBoardPosition.INDEX_BOTTOM_MIDDLE
            )
        )
        assertNotEquals(ticTacToeViewModel.getMatchSummary().matchStatus, MatchStatus.WIN_BY_COLUMN)
    }


    @Test
    fun testShouldCheckWhetherAnyPlayerWonByDiagonal() {
        storePlayerMoves(
            intArrayOf(
                GameBoardPosition.INDEX_TOP_RIGHT,
                GameBoardPosition.INDEX_TOP_MIDDLE,
                GameBoardPosition.INDEX_CENTER_MIDDLE,
                GameBoardPosition.INDEX_CENTER_LEFT,
                GameBoardPosition.INDEX_BOTTOM_LEFT
            )
        )
        assertEquals(ticTacToeViewModel.getMatchSummary().matchStatus, MatchStatus.WIN_BY_DIAGONAL)
    }

    @Test
    fun testShouldDeclineWinIfAlternatePlayersPlayedOnSimilarDiagonalPatterns() {
        storePlayerMoves(
            intArrayOf(
                GameBoardPosition.INDEX_TOP_RIGHT,
                GameBoardPosition.INDEX_CENTER_MIDDLE,
                GameBoardPosition.INDEX_BOTTOM_LEFT
            )
        )
        assertNotEquals(ticTacToeViewModel.getMatchSummary().matchStatus, MatchStatus.WIN_BY_DIAGONAL)
    }


    @Test
    fun testShouldCheckGameIsDrawWhenBothThePlayersExhaustingAllMoves() {
        storePlayerMoves(
            intArrayOf(
                GameBoardPosition.INDEX_CENTER_MIDDLE,
                GameBoardPosition.INDEX_TOP_LEFT,
                GameBoardPosition.INDEX_TOP_MIDDLE,
                GameBoardPosition.INDEX_BOTTOM_MIDDLE,
                GameBoardPosition.INDEX_BOTTOM_LEFT,
                GameBoardPosition.INDEX_TOP_RIGHT,
                GameBoardPosition.INDEX_CENTER_LEFT,
                GameBoardPosition.INDEX_CENTER_RIGHT,
                GameBoardPosition.INDEX_BOTTOM_RIGHT
            )
        )
        assertEquals(ticTacToeViewModel.getMatchSummary().matchStatus, MatchStatus.DRAW)
    }

    @Test
    fun testShouldCheckNoMovesAllowedToAnyPlayerWhenOneOfThePlayerWinsTheMatch() {
        storePlayerMoves(
            intArrayOf(
                GameBoardPosition.INDEX_TOP_LEFT,
                GameBoardPosition.INDEX_CENTER_MIDDLE,
                GameBoardPosition.INDEX_TOP_MIDDLE,
                GameBoardPosition.INDEX_CENTER_RIGHT,
                GameBoardPosition.INDEX_TOP_RIGHT
            )
        )
        assertEquals(ticTacToeViewModel.getMatchSummary().matchStatus, MatchStatus.WIN_BY_ROW)
        ticTacToeViewModel.storePlayerMoves(GameBoardPosition.INDEX_BOTTOM_RIGHT)
        assertFalse(ticTacToeViewModel.getMatchSummary().isValidMove)
    }

    @Test
    fun testShouldCheckWhetherAnyPlayerHasWonTheMatch() {
        storePlayerMoves(
            intArrayOf(
                GameBoardPosition.INDEX_TOP_LEFT,
                GameBoardPosition.INDEX_CENTER_LEFT,
                GameBoardPosition.INDEX_TOP_MIDDLE,
                GameBoardPosition.INDEX_CENTER_MIDDLE,
                GameBoardPosition.INDEX_TOP_RIGHT
            )
        )
        assertTrue(ticTacToeViewModel.getMatchSummary().isValidMove)
        assertNotEquals(ticTacToeViewModel.getMatchSummary().matchStatus, MatchStatus.DRAW)
        assertEquals(ticTacToeViewModel.getMatchSummary().matchStatus, MatchStatus.WIN_BY_ROW)
    }


    private fun storePlayerMoves(indices: IntArray) {
        indices.forEach { index -> ticTacToeViewModel.storePlayerMoves(index) }
    }

}
