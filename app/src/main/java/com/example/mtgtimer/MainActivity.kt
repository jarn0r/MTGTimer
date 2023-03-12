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
    var startTime = 0
    var startmin = 0
    var starthour = 0
    var startSecond = 0
    var stopTime = 0
    var stopmin = 0
    var stophour = 0
    var stopSecond = 0
    var dauer = 0
    private lateinit var handler: Handler
    private var elapsedTime: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button1 = findViewById<Button>(R.id.button1)
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)
        val button4 = findViewById<Button>(R.id.button4)
        val timer1 = findViewById<EditText>(R.id.eTT1)
        val timer2 = findViewById<EditText>(R.id.eTT2)
        val timer3 = findViewById<EditText>(R.id.eTT3)
        val timer4 = findViewById<EditText>(R.id.eTT4)

        handler = Handler(Looper.getMainLooper())

        button1.setOnClickListener {
            if (isStarted) {
                // Stop the stopwatch
                isStarted = false
                handler.removeCallbacks(updateTimer)
                dauer = (stopSecond - startSecond) + (stopmin - startmin) * 60 + (stophour - starthour) * 3600
                //elapsedTime += (dauer * 1000)
                Log.d("MainActivity", "$stophour , $stopmin ,$stopSecond")
                //val seconds = elapsedTime / 1000
                timer1.setText(getString(R.string.elapsed_time, dauer))
                button1.text = getString(R.string.start)
            } else {
                // Start the stopwatch
                isStarted = true
                Log.d("MainActivity","$starthour ,$startmin, $startSecond")
                elapsedTime = measureTimeMillis {
                    handler.post(updateTimer)
                }
                button1.text = getString(R.string.stop)
            }
        }
    }
    private val updateTimer = object : Runnable {
        override fun run() {
            elapsedTime += 10
            val seconds = elapsedTime / 1000
            val timer1 = findViewById<EditText>(R.id.eTT1)
            timer1.setText(getString(R.string.elapsed_time, seconds))
            handler.postDelayed(this, 10)
        }
    }
}
