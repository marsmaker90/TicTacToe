package com.kata.ticktactoe

import androidx.lifecycle.ViewModel

class TicTacToeViewModel : ViewModel() {

    private var mBoard = Array(3) { IntArray(3) }
    private var mCurrentPlayer = PLAYER_X_ID

    companion object {
        const val PLAYER_X_ID = 1
        const val PLAYER_O_ID = 2
    }

    fun getCurrentPlayer(): Int {
        return mCurrentPlayer
    }

    fun getBoard(): Array<IntArray> {
        return mBoard
    }

    fun storePlayerMoves(position: Int, playerTag: Int): Boolean {
        return if (getPlayBoardByIndex(position) == 0) {
            updatePlayBoardIndex(position, playerTag)
            updateCurrentPlayer(playerTag)
            true
        } else {
            false
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
                && compareIndices(Pair(index, 0), Pair(index, 1), Pair(index, 2))) {
                return true
            }
        }
        return false
    }
    fun isWinnerByColumn(): Boolean {
        IntRange(0, 2).forEach { columnPosition ->
            if (checkIndexIsNotEmpty(0, columnPosition) &&
                compareIndices(Pair(0, columnPosition), Pair(1, columnPosition), Pair(2, columnPosition))
            ) {
                return true
            }
        }
        return false
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