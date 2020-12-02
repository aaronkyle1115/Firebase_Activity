package com.example.youtubechannels

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.youtubechannels.handler.VideoHandler
import com.example.youtubechannels.models.Video

class Adding : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adding)
        editTitle = findViewById(R.id.editTitle)
        editRank = findViewById(R.id.editRank)
        editURL = findViewById(R.id.editURL)
        editReason = findViewById(R.id.editReason)
        videoHandler = VideoHandler()
        btnAdd = findViewById(R.id.btnAdd)
        if(current == true){
            editTitle.setText(videoUpdate.name)
            editRank.setText(videoUpdate.rank)
            editURL.setText(videoUpdate.url)
            editReason.setText(videoUpdate.reason)
            btnAdd.setText("Update")
            current = false
        }

        btnAdd.setOnClickListener {
            val title = editTitle.text.toString()
            val rank = editRank.text.toString()
            val url = editURL.text.toString()
            val reason = editReason.text.toString()

            if(btnAdd.text.toString() == "Add"){
                val video = Video(name = title, url = url, rank = rank, reason = reason)
                if (videoHandler.create(video)) {
                    Toast.makeText(applicationContext, "Video Added!!", Toast.LENGTH_SHORT).show()
                    clearFields()
                }
            }
            else if(btnAdd.text.toString() == "Update"){
                val video = Video(id = videoUpdate.id, name = title, rank = rank, url = url, reason = reason )
                if(videoHandler.update(video)){
                    Toast.makeText(applicationContext, "Video Info Update", Toast.LENGTH_SHORT).show()
                    clearFields()
                }
            }



        }
    }
    fun clearFields() {
        editTitle.text.clear()
        editRank.text.clear()
        editURL.text.clear()
        editReason.text.clear()
        btnAdd.setText("Add")
        startActivity(Intent(this, MainActivity::class.java))
    }
}