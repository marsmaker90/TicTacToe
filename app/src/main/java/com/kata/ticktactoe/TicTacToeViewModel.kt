package com.kata.ticktactoe

import androidx.lifecycle.ViewModel

class TicTacToeViewModel : ViewModel() {
    var mBoard = Array(3) { IntArray(3) }
}