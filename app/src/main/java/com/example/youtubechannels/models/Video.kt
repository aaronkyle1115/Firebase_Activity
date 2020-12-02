package com.example.youtubechannels.models

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
class Video(var id : String? = "", var name : String = "", var url : String? = "", var rank : String? = "", var reason : String? = "" ) {
    override fun toString() : String {
        return "$name"
    }
}