package com.example.youtubechannels

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import com.example.youtubechannels.handler.VideoHandler
import com.example.youtubechannels.models.Video
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import java.util.*
import kotlin.collections.ArrayList

lateinit var editTitle : EditText
lateinit var editRank : EditText
lateinit var editURL : EditText
lateinit var editReason : EditText
lateinit var btnAdd : Button
lateinit var videoHandler : VideoHandler
lateinit var videoUpdate : Video
public var current : Boolean = false
class MainActivity : AppCompatActivity() {


    lateinit var btnOpt : Button
    lateinit var videos : ArrayList<Video>
    lateinit var videoListView : ListView


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        videoHandler = VideoHandler()


        videoListView = findViewById(R.id.videoListView)
        btnOpt = findViewById(R.id.goAddbtn)
        btnOpt.setOnClickListener{
            startActivity(Intent(this, Adding::class.java))
        }
        registerForContextMenu(videoListView)
        videoListView.onItemClickListener = AdapterView.OnItemClickListener{parent, view, position, id ->
            videoUpdate = videos[position]
            startActivity(Intent(this, viewInfo::class.java))
        }
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
        val inflater = menuInflater
        inflater.inflate(R.menu.optionmenu, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        return when(item.itemId) {
            R.id.Edit_Video -> {
                videoUpdate = videos[info.position]
                current = true
                startActivity(Intent(this, Adding::class.java))
                true
            }
            R.id.Delete_Video -> {
                if(videoHandler.delete(videos[info.position])) {
                    Toast.makeText(applicationContext, "Video Deleted", Toast.LENGTH_SHORT).show()
                }
                true
            }
            else -> super.onContextItemSelected(item)
        }

    }


 override fun onStart() {
        super.onStart()

        videoHandler.videoRef.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                //TODO("Not yet implemented")
            }

            override fun onDataChange(p0: DataSnapshot) {
                videos.clear()
                p0.children.forEach{
                    it -> val video = it.getValue(Video::class.java)
                    videos.add(video!!)
                }

                val adapter = ArrayAdapter<Video>(applicationContext, android.R.layout.simple_list_item_1, videos)
                videoListView.adapter = adapter
            }



        })
    }
}