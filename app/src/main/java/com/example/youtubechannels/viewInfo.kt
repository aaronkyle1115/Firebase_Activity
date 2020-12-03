package com.example.youtubechannels

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class viewInfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)

        val viewTitle = findViewById<TextView>(R.id.viewTitle)
        val viewRank = findViewById<TextView>(R.id.viewRank)
        val viewURL = findViewById<TextView>(R.id.viewURL)
        val viewReason = findViewById<TextView>(R.id.viewReason)
        val btnBack : Button = findViewById(R.id.btnBack)

        viewTitle.setText("Title: " + videoUpdate.name)
        viewRank.setText("Rank: "+ videoUpdate.rank)
        viewURL.setText("URL: " + videoUpdate.url)
        viewReason.setText("Reason: " + videoUpdate.reason)

        btnBack.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
        }


    }
}