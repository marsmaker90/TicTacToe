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

    fun storePlayerMoves(position: Int, playerTag: Int) {
        getBoard()[position / 3][position % 3] = playerTag
    }

    fun getPlayBoardByIndex(position: Int) = getBoard()[position / 3][position % 3]


}