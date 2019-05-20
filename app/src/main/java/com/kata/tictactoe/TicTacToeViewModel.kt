package com.kata.tictactoe

import androidx.lifecycle.ViewModel

class TicTacToeViewModel : ViewModel() {

    private var mBoard = Array(3) { IntArray(3) }
    private var mCurrentPlayer = PLAYER_X_ID
    var isGameFinished: Boolean = false

    companion object {
        const val PLAYER_X_ID = 1
        const val PLAYER_O_ID = 2
        private var GAME_MOVE_COUNTER = 0
    }

    fun getCurrentPlayer(): Int {
        return mCurrentPlayer
    }

    fun getBoard(): Array<IntArray> {
        return mBoard
    }

    fun storePlayerMoves(position: Int, playerTag: Int): Boolean {

        if (!isGameFinished && GAME_MOVE_COUNTER <= 9) {
            if (getPlayBoardByIndex(position) == 0) {
                updatePlayBoardIndex(position, playerTag)
                updateCurrentPlayer(playerTag)
                GAME_MOVE_COUNTER = GAME_MOVE_COUNTER.plus(1)
                return true
            } else {
                return false
            }
        } else {
            return false
        }

    }

    fun getPlayBoardByIndex(position: Int) = getBoard()[position / 3][position % 3]

    private fun updatePlayBoardIndex(position: Int, playerTag: Int) {
        getBoard()[position / 3][position % 3] = playerTag
    }

    private fun updateCurrentPlayer(playerTag: Int) {
        mCurrentPlayer = if (playerTag == PLAYER_X_ID) {
            PLAYER_O_ID
        } else {
            PLAYER_X_ID
        }
    }

    fun isWinnerByRow(): Boolean {
        IntRange(0, 2).forEach { index ->
            if (checkIndexIsNotEmpty(index, 0)
                && compareIndices(Pair(index, 0), Pair(index, 1), Pair(index, 2))
            ) {
                isGameFinished = true
                return true
            }
        }
        return false
    }

    fun isWinnerByColumn(): Boolean {
        IntRange(0, 2).forEach { index ->
            if (checkIndexIsNotEmpty(0, index) &&
                compareIndices(Pair(0, index), Pair(1, index), Pair(2, index))
            ) {
                isGameFinished = true
                return true
            }
        }
        return false
    }

    fun isWinnerByDiagonal(): Boolean {
        if (checkIndexIsNotEmpty(0, 0) && compareIndices(Pair(0, 0), Pair(1, 1), Pair(2, 2)) ||
            checkIndexIsNotEmpty(0, 2) && compareIndices(Pair(0, 2), Pair(1, 1), Pair(2, 0))
        ) {
            isGameFinished = true
            return true
        } else {
            return false
        }
    }

    fun isMatchDrawn(): Boolean {
        return if (GAME_MOVE_COUNTER == 9) {
            isGameFinished = true
            true
        } else {
            false
        }

    }


    private fun checkIndexIsNotEmpty(firstIndex: Int, secondIndex: Int) = getBoard()[firstIndex][secondIndex] > 0

    private fun compareIndices(
        firstPosition: Pair<Int, Int>,
        secondPosition: Pair<Int, Int>,
        thirdPosition: Pair<Int, Int>
    ): Boolean {

        val firstIndexValue = getBoard()[firstPosition.first][firstPosition.second]
        val secondIndexValue = getBoard()[secondPosition.first][secondPosition.second]
        val thirdIndexValue = getBoard()[thirdPosition.first][thirdPosition.second]

        return firstIndexValue == secondIndexValue &&
                firstIndexValue == thirdIndexValue
    }
}