package com.example.tictactoeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import  android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun buttonClick(view: View) {
        val buttonSelected = view as Button
        if(buttonSelected==newGame)
        {
            activePlayer=1
            player1.clear()
            player2.clear()
            for(i in 1..9)
            {
                var curButton:Button=when(i){
                    1->button1
                    2->button2
                    3->button3
                    4->button4
                    5->button5
                    6->button6
                    7->button7
                    8->button8
                    9 ->button9
                    else->{button1}
                }
                curButton.text=""
                curButton.setBackgroundResource(R.color.White)
                curButton.isEnabled=true
            }
        }
        else{
            var cellId = 0
            when (buttonSelected.id) {

                R.id.button1 -> cellId = 1
                R.id.button2 -> cellId = 2
                R.id.button3 -> cellId = 3
                R.id.button4 -> cellId = 4
                R.id.button5 -> cellId = 5
                R.id.button6 -> cellId = 6
                R.id.button7 -> cellId = 7
                R.id.button8 -> cellId = 8
                R.id.button9 -> cellId = 9
            }

            Log.d("Button selected ", buttonSelected.id.toString())
            Log.d("ButtonSelected:CellId :", cellId.toString())


            playGame(cellId, buttonSelected)
        }
    }

    var activePlayer = 1
    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()
 var nUserScore=0
    var nCompScore=0

    fun playGame(cellId: Int, buttonSelected: Button) {
        if (activePlayer == 1) {
            buttonSelected.text = "X"
            buttonSelected.setBackgroundResource(R.color.blue)
            player1.add(cellId)
            activePlayer = 2
            autoplay()

        } else {
            buttonSelected.text = "O"
            buttonSelected.setBackgroundResource(R.color.darkGreen)
            player2.add(cellId)
            activePlayer = 1
        }
        buttonSelected.isEnabled = false
        var winner = checkWinner()
        if (winner == 1) {
            Toast.makeText(this, "You win the game!!", Toast.LENGTH_LONG).show()
            button1.isEnabled = false
            button2.isEnabled = false
            button3.isEnabled = false
            button4.isEnabled = false
            button5.isEnabled = false
            button6.isEnabled = false
            button7.isEnabled = false
            button8.isEnabled = false
            button9.isEnabled = false
            nUserScore++

            userScoreButton.text="Your score: "+nUserScore/2
        }
        else if (winner == 2) {
            Toast.makeText(this, "Computer wins the game!!", Toast.LENGTH_LONG).show()
            button1.isEnabled = false
            button2.isEnabled = false
            button3.isEnabled = false
            button4.isEnabled = false
            button5.isEnabled = false
            button6.isEnabled = false
            button7.isEnabled = false
            button8.isEnabled = false
            button9.isEnabled = false
            nCompScore++

            compScoreButton.text="Comp score: "+nCompScore/2
                   }

    }

    fun checkWinner(): Int {

        for (i in 1..3) {
            if (player1.contains(1 + 3 * (i - 1)) && player1.contains(2 + 3 * (i - 1)) && player1.contains(
                    3 + 3 * (i - 1)
                )
            ) {
                return 1
            } else if (player1.contains(i) && player1.contains(i + 3) && player1.contains(i + 6)) {
                return 1
            }
        }
        if (player1.contains(1) && player1.contains(5) && player1.contains(9)) {
            return 1
        } else if (player1.contains(3) && player1.contains(5) && player1.contains(7)) {
            return 1
        }
        for (i in 1..3) {
            if (player2.contains(1 + 3 * (i - 1)) && player2.contains(2 + 3 * (i - 1)) && player2.contains(
                    3 + 3 * (i - 1)
                )
            ) {
                return 2
            } else if (player2.contains(i) && player2.contains(i + 3) && player2.contains(i + 6)) {
                return 2
            }
        }
        if (player2.contains(1) && player2.contains(5) && player2.contains(9)) {
            return 2
        } else if (player2.contains(3) && player2.contains(5) && player2.contains(7)) {
            return 2
        }
        return -1

    }

    fun autoplay() {
        var emptyCellsForComp = ArrayList<Int>()
        for (i in 1..9) {
            if (!(player1.contains(i)) && !(player2.contains(i))) {
                emptyCellsForComp.add(i)
            }
        }
        if(emptyCellsForComp.size!=0)
        {
            val r = Random()
            val randIndex = r.nextInt(emptyCellsForComp.size)
            val cellId = emptyCellsForComp[randIndex]
            var buttonS:Button = when (cellId) {
                1 -> button1
                2 -> button2
                3 -> button3
                4 -> button4
                5 -> button5
                6 -> button6
                7 -> button7
                8 -> button8
                9 -> button9
                else -> {
                    button1
                }

            }
            playGame(cellId, buttonS)
        }

    }
    fun clearTable()
    {
        for(i in 1..9)
        {
            var buttonToCorrect:Button=when(i) {
                1->button1
                2->button2
                3->button3


                4->button4

                5->button5
                6->button6
                7->button7
                8->button8
                9->button9
            else->{button1}
            }
            buttonToCorrect.text=""
            buttonToCorrect.setBackgroundResource(R.color.White)
            buttonToCorrect.isEnabled=true
        }
        player2.clear()
        player1.clear()
        activePlayer=1
    }
}