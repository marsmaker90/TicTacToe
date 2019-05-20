package com.kata.tictactoe

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val ticTacToeViewModel: TicTacToeViewModel by lazy { TicTacToeViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initButton()
    }

    private fun initButton() {
        button1.setOnClickListener(this)
        button1.tag = 0
        button2.setOnClickListener(this)
        button2.tag = 1
        button3.setOnClickListener(this)
        button3.tag = 2
        button4.setOnClickListener(this)
        button4.tag = 3
        button5.setOnClickListener(this)
        button5.tag = 4
        button6.setOnClickListener(this)
        button6.tag = 5
        button7.setOnClickListener(this)
        button7.tag = 6
        button8.setOnClickListener(this)
        button8.tag = 7
        button9.setOnClickListener(this)
        button9.tag = 8

        resetButton.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.button1, R.id.button2,
            R.id.button3, R.id.button4,
            R.id.button5, R.id.button6,
            R.id.button7, R.id.button8,
            R.id.button9 -> checkAndRecordPlayerMove(view)
        }
    }

    private fun checkAndRecordPlayerMove(view: View) {
        if (ticTacToeViewModel.getCurrentPlayer() == TicTacToeViewModel.PLAYER_X_ID) {
            val isValidMove =
                ticTacToeViewModel.storePlayerMoves(view.tag.toString().toInt(), TicTacToeViewModel.PLAYER_X_ID)
            if (isValidMove) {
                (view as Button).text = getString(R.string.player_x)
            }
        } else {
            val isValidMove =
                ticTacToeViewModel.storePlayerMoves(view.tag.toString().toInt(), TicTacToeViewModel.PLAYER_O_ID)
            if (isValidMove) {
                (view as Button).text = getString(R.string.player_o)
            }
        }


        if (ticTacToeViewModel.getGameMoveCounter() > 4 && ticTacToeViewModel.identifyIfAnyPlayerHadWon().isNotEmpty())
            matchSummary.text = ticTacToeViewModel.identifyIfAnyPlayerHadWon()
    }


}
