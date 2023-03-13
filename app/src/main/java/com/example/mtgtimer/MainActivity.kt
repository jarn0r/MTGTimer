package com.example.mtgtimer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.EditText
import java.text.SimpleDateFormat

import kotlin.concurrent.thread
import kotlin.system.measureTimeMillis
import kotlin.time.measureTime

class MainActivity : AppCompatActivity() {
    var isStarted = false
    var startTime = 0L
    var stopTime = 0L
    var timeGes = 0L
    private lateinit var handler: Handler
    private var elapsedTime: Long = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button1 = findViewById<Button>(R.id.button1)
        val timer1 = findViewById<EditText>(R.id.eTT1)
        var gesTimer1 = 0
        val button2 = findViewById<Button>(R.id.button2)
        val timer2 = findViewById<EditText>(R.id.eTT2)
        var gesTimer2 = 0
        val button3 = findViewById<Button>(R.id.button3)
        val timer3 = findViewById<EditText>(R.id.eTT3)
        var gesTimer3 = 0
        val button4 = findViewById<Button>(R.id.button4)
        val timer4 = findViewById<EditText>(R.id.eTT4)
        var gesTimer4 = 0
        var timerGes = findViewById<EditText>(R.id.eTTGesamt)
        val end = findViewById<Button>(R.id.stopper)

        handler = Handler(Looper.getMainLooper())

        button1.setOnClickListener {
            if (isStarted) {
                // Stop the stopwatch
                isStarted = false
                handler.removeCallbacks(updateTimer)
                stopTime = System.currentTimeMillis()
                val elapsedSeconds = ((stopTime - startTime) / 1000).toInt()
                timeGes += elapsedSeconds
                gesTimer1 += elapsedSeconds
                timer1.setText(getString(R.string.elapsed_time, gesTimer1))
                button1.text = getString(R.string.player1)
                timerGes.setText(getString(R.string.elapsed_time, timeGes))
            } else {
                // Start the stopwatch
                isStarted = true
                startTime = System.currentTimeMillis()
                elapsedTime = 0L
                handler.post(updateTimer)
                button1.text = getString(R.string.stop)
            }
        }
        button2.setOnClickListener {
            if (isStarted) {
                // Stop the stopwatch
                isStarted = false
                handler.removeCallbacks(updateTimer)
                stopTime = System.currentTimeMillis()
                val elapsedSeconds = ((stopTime - startTime) / 1000).toInt()
                timeGes += elapsedSeconds
                gesTimer2 += elapsedSeconds
                timer2.setText(getString(R.string.elapsed_time, gesTimer2))
                button2.text = getString(R.string.player2)
                timerGes.setText(getString(R.string.elapsed_time, timeGes))
            } else {
                // Start the stopwatch
                isStarted = true
                startTime = System.currentTimeMillis()
                elapsedTime = 0L
                handler.post(updateTimer)
                button2.text = getString(R.string.stop)
            }
        }
        button3.setOnClickListener {
            if (isStarted) {
                // Stop the stopwatch
                isStarted = false
                handler.removeCallbacks(updateTimer)
                stopTime = System.currentTimeMillis()
                val elapsedSeconds = ((stopTime - startTime) / 1000).toInt()
                timeGes += elapsedSeconds
                gesTimer3 += elapsedSeconds
                timer3.setText(getString(R.string.elapsed_time, gesTimer3))
                button3.text = getString(R.string.player3)
                timerGes.setText(getString(R.string.elapsed_time, timeGes))
            } else {
                // Start the stopwatch
                isStarted = true
                startTime = System.currentTimeMillis()
                elapsedTime = 0L
                handler.post(updateTimer)
                button3.text = getString(R.string.stop)
            }
        }
        button4.setOnClickListener {
            if (isStarted) {
                // Stop the stopwatch
                isStarted = false
                handler.removeCallbacks(updateTimer)
                stopTime = System.currentTimeMillis()
                val elapsedSeconds = ((stopTime - startTime) / 1000).toInt()
                timeGes += elapsedSeconds
                gesTimer4 += elapsedSeconds
                timer4.setText(getString(R.string.elapsed_time, gesTimer4))
                button4.text = getString(R.string.player4)
                timerGes.setText(getString(R.string.elapsed_time, timeGes))
            } else {
                // Start the stopwatch
                isStarted = true
                startTime = System.currentTimeMillis()
                elapsedTime = 0L
                handler.post(updateTimer)
                button4.text = getString(R.string.stop)
            }
        }
        end.setOnClickListener{
            val perc1 = calcTimePercent(gesTimer1.toFloat(), timeGes)
            timer1.setText(getString(R.string.result, gesTimer1, perc1))
            val perc2 = calcTimePercent(gesTimer2.toFloat(), timeGes)
            timer2.setText(getString(R.string.result, gesTimer2, perc2))
            val perc3 = calcTimePercent(gesTimer3.toFloat(), timeGes)
            timer3.setText(getString(R.string.result, gesTimer3, perc3))
            val perc4 = calcTimePercent(gesTimer4.toFloat(), timeGes)
            timer4.setText(getString(R.string.result, gesTimer4, perc4))
        }
    }

    private val updateTimer = object : Runnable {
        override fun run() {
            elapsedTime += 10
            val seconds = (elapsedTime / 1000).toInt()
            val timerGes = findViewById<EditText>(R.id.eTTGesamt)
            timerGes.setText(getString(R.string.elapsed_time, seconds))
            handler.postDelayed(this, 10)
        }
    }
}
fun calcTimePercent(first: Float , ges: Long = 0): Float{
    val all: Float = first / ges *100
     return all
}
