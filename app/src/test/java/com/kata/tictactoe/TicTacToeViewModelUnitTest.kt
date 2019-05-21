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
    fun testToCheckWhetherTheFirstMoveDoneByPlayerX() {
        assertEquals(ticTacToeViewModel.getCurrentPlayer(), Player.PLAYER_X_ID)
    }

    @Test
    fun testToStoreThePlayerMoveAndCheckPlayerAtThatParticularIndex() {
        assertNotNull(ticTacToeViewModel.storePlayerMoves(GameBoardPosition.INDEX_1))
        assertSame(ticTacToeViewModel.getGameBoardByIndex(GameBoardPosition.INDEX_1), Player.PLAYER_X_ID)
    }

    @Test
    fun testSwappingOfPlayerAfterMoveSuccessfullyStored() {
        assertEquals(ticTacToeViewModel.getCurrentPlayer(), Player.PLAYER_X_ID)
        ticTacToeViewModel.storePlayerMoves(GameBoardPosition.INDEX_1)
        assertEquals(ticTacToeViewModel.getCurrentPlayer(), Player.PLAYER_O_ID)
        assertNotEquals(ticTacToeViewModel.getCurrentPlayer(), Player.PLAYER_X_ID)

    }

    @Test
    fun testWhetherAnyPlayerWonByRow() {
        storePlayerMoves(
            intArrayOf(
                GameBoardPosition.INDEX_3,
                GameBoardPosition.INDEX_1,
                GameBoardPosition.INDEX_4,
                GameBoardPosition.INDEX_2,
                GameBoardPosition.INDEX_5
            )
        )
        assertEquals(ticTacToeViewModel.getMatchSummary().matchStatus, MatchStatus.WIN_BY_ROW)
    }

    @Test
    fun testAlternatePlayersHavePlayedOnTheSimilarRow() {
        storePlayerMoves(
            intArrayOf(
                GameBoardPosition.INDEX_6,
                GameBoardPosition.INDEX_7,
                GameBoardPosition.INDEX_8
            )
        )
        assertNotEquals(ticTacToeViewModel.getMatchSummary().matchStatus, MatchStatus.WIN_BY_ROW)
    }

    @Test
    fun testAnyPlayerHaveWonByRowWhenFirstIndexOfColumnIsNotEmptyAndOtherTwoIndicesAreSame() {

        storePlayerMoves(
            intArrayOf(
                GameBoardPosition.INDEX_1,
                GameBoardPosition.INDEX_2,
                GameBoardPosition.INDEX_4,
                GameBoardPosition.INDEX_3,
                GameBoardPosition.INDEX_7
            )
        )

        assertEquals(ticTacToeViewModel.getMatchSummary().matchStatus, MatchStatus.WIN_BY_COLUMN)
    }

    @Test
    fun testAlternatePlayersHavePlayedOnTheSimilarColumn() {
        storePlayerMoves(
            intArrayOf(
                GameBoardPosition.INDEX_1,
                GameBoardPosition.INDEX_4,
                GameBoardPosition.INDEX_7
            )
        )
        assertNotEquals(ticTacToeViewModel.getMatchSummary().matchStatus, MatchStatus.WIN_BY_COLUMN)
    }


    @Test
    fun testAnyPlayerHaveWonByDiagonal() {
        storePlayerMoves(
            intArrayOf(
                GameBoardPosition.INDEX_2,
                GameBoardPosition.INDEX_1,
                GameBoardPosition.INDEX_4,
                GameBoardPosition.INDEX_3,
                GameBoardPosition.INDEX_6
            )
        )
        assertEquals(ticTacToeViewModel.getMatchSummary().matchStatus, MatchStatus.WIN_BY_DIAGONAL)
    }

    @Test
    fun testAlternatePlayersHavePlayedOnTheSimilarDiagonalPattern() {
        storePlayerMoves(
            intArrayOf(
                GameBoardPosition.INDEX_2,
                GameBoardPosition.INDEX_4,
                GameBoardPosition.INDEX_6
            )
        )
        assertNotEquals(ticTacToeViewModel.getMatchSummary().matchStatus, MatchStatus.WIN_BY_DIAGONAL)
    }


    @Test
    fun testGameIsDrawByExhaustingAllMovesForBothThePlayers() {
        storePlayerMoves(
            intArrayOf(
                GameBoardPosition.INDEX_4,
                GameBoardPosition.INDEX_0,
                GameBoardPosition.INDEX_1,
                GameBoardPosition.INDEX_7,
                GameBoardPosition.INDEX_6,
                GameBoardPosition.INDEX_2,
                GameBoardPosition.INDEX_3,
                GameBoardPosition.INDEX_5,
                GameBoardPosition.INDEX_8
            )
        )
        assertEquals(ticTacToeViewModel.getMatchSummary().matchStatus, MatchStatus.DRAW)
    }

    @Test
    fun testNoMovesAllowedToAnyPlayerIfOneOfThePlayerWinsTheMatch() {
        storePlayerMoves(
            intArrayOf(
                GameBoardPosition.INDEX_0,
                GameBoardPosition.INDEX_4,
                GameBoardPosition.INDEX_1,
                GameBoardPosition.INDEX_5,
                GameBoardPosition.INDEX_2
            )
        )
        assertEquals(ticTacToeViewModel.getMatchSummary().matchStatus, MatchStatus.WIN_BY_ROW)
        ticTacToeViewModel.storePlayerMoves(GameBoardPosition.INDEX_8)
        assertFalse(ticTacToeViewModel.getMatchSummary().isValidMove)
    }

    @Test
    fun testPlayerHasWonTheMatch() {
        storePlayerMoves(
            intArrayOf(
                GameBoardPosition.INDEX_0,
                GameBoardPosition.INDEX_3,
                GameBoardPosition.INDEX_1,
                GameBoardPosition.INDEX_4,
                GameBoardPosition.INDEX_2
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
