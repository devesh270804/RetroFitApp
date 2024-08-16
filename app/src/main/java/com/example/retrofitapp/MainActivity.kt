package com.example.retrofitapp

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textview:TextView = findViewById(R.id.textView)
        val retrofitService = RetrofitInstance
            .getRetrofitInstance()
            .create(AlbumService::class.java)

        val responseLiveData : LiveData<Response<Albums>> =
            liveData {
              //  val response = retrofitService.getAlbums()
                val response = retrofitService.getSpecificAlbums(5)
                emit(response)
            }
        responseLiveData.observe(this, Observer {
            val albumsList = it.body()?.listIterator()

            if(albumsList != null){
                while (albumsList.hasNext()){
                    val albumItem = albumsList.next()
                   //Log.i("TAGY",albumItem.title)

                    val result = "Album Title : ${albumItem.title} \n"
                    textview.append(result)
                }

            }
        })


    }
}