package com.kata.tictactoe

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.kata.tictactoe.utils.GameBoardPosition
import com.kata.tictactoe.utils.MatchStatus
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val ticTacToeViewModel: TicTacToeViewModel by lazy { TicTacToeViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initButton()
    }

    private fun initButton() {
        buttonTopRowLeft.setOnClickListener(this)
        buttonTopRowLeft.tag = GameBoardPosition.INDEX_0

        buttonTopRowMiddle.setOnClickListener(this)
        buttonTopRowMiddle.tag = GameBoardPosition.INDEX_1

        buttonTopRowRight.setOnClickListener(this)
        buttonTopRowRight.tag = GameBoardPosition.INDEX_2

        buttonCenterRowLeft.setOnClickListener(this)
        buttonCenterRowLeft.tag = GameBoardPosition.INDEX_3

        buttonCenterRowMiddle.setOnClickListener(this)
        buttonCenterRowMiddle.tag = GameBoardPosition.INDEX_4

        buttonCenterRowRight.setOnClickListener(this)
        buttonCenterRowRight.tag = GameBoardPosition.INDEX_5

        buttonBottomRowLeft.setOnClickListener(this)
        buttonBottomRowLeft.tag = GameBoardPosition.INDEX_6

        buttonBottomRowMiddle.setOnClickListener(this)
        buttonBottomRowMiddle.tag = GameBoardPosition.INDEX_7

        buttonBottomRowRight.setOnClickListener(this)
        buttonBottomRowRight.tag = GameBoardPosition.INDEX_8

        resetButton.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.buttonTopRowLeft, R.id.buttonTopRowMiddle, R.id.buttonTopRowRight,
            R.id.buttonCenterRowLeft, R.id.buttonCenterRowMiddle, R.id.buttonCenterRowRight,
            R.id.buttonBottomRowLeft, R.id.buttonBottomRowMiddle, R.id.buttonBottomRowRight
            -> checkAndRecordPlayerMove(view)

            R.id.resetButton -> {
                removeAllButtonText()
                ticTacToeViewModel.resetGameBoard()
            }
        }
    }

    private fun removeAllButtonText() {
        textViewMatchSummary.text = ""
        IntRange(0, 8).forEach {
            val view = tableLayout.findViewWithTag<Button>(it)
            view.text = ""
            view.isClickable = true
        }

    }

    private fun checkAndRecordPlayerMove(view: View) {
        ticTacToeViewModel.storePlayerMoves(view.tag.toString().toInt())
        (view as Button).text = ticTacToeViewModel.getPlayerName(this)
        disableButtonClickWhenMatchEnds()
        view.isClickable = false
    }

    private fun disableButtonClickWhenMatchEnds() {
        if (ticTacToeViewModel.getMatchSummary().matchStatus == MatchStatus.MATCH_END) {
            IntRange(0, 8).forEach {
                tableLayout.findViewWithTag<Button>(it).isClickable = false
            }
        } else {
            textViewMatchSummary.text = ticTacToeViewModel.getMatchSummary().matchSummary
        }
    }


}
