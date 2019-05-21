package com.kata.tictactoe.utils

data class MatchSummary(
    var matchStatus: MatchStatus? = null,
    var matchSummary: String = "",
    var isValidMove: Boolean = true
)