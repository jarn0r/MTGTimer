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
    private lateinit var handler: Handler
    private var elapsedTime: Long = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button1 = findViewById<Button>(R.id.button1)
        val timer1 = findViewById<EditText>(R.id.eTT1)

        handler = Handler(Looper.getMainLooper())

        button1.setOnClickListener {
            if (isStarted) {
                // Stop the stopwatch
                isStarted = false
                handler.removeCallbacks(updateTimer)
                stopTime = System.currentTimeMillis()
                val elapsedSeconds = ((stopTime - startTime) / 1000).toInt()
                timer1.setText(getString(R.string.elapsed_time, elapsedSeconds))
                button1.text = getString(R.string.start)
            } else {
                // Start the stopwatch
                isStarted = true
                startTime = System.currentTimeMillis()
                elapsedTime = 0L
                handler.post(updateTimer)
                button1.text = getString(R.string.stop)
            }
        }
    }

    private val updateTimer = object : Runnable {
        override fun run() {
            elapsedTime += 10
            val seconds = (elapsedTime / 1000).toInt()
            val timer1 = findViewById<EditText>(R.id.eTT1)
            timer1.setText(getString(R.string.elapsed_time, seconds))
            handler.postDelayed(this, 10)
        }
    }
}
