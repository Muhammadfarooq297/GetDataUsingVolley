package com.example.getdatausingvolley

import Adapter
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.GsonBuilder

class MainActivity : AppCompatActivity() {

    var url = "https://api.github.com/users"   // API URL
    private var userInfoItems = UserInfoList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.rev)

        val stringRequest = StringRequest(url,
            Response.Listener {
                val gsonBuilder = GsonBuilder()
                val gson = gsonBuilder.create()

                userInfoItems = UserInfoList().apply {
                    addAll(gson.fromJson(it, Array<UserInfoListItem>::class.java).toList())
                }

//                userInfoItems.forEach {
//                    userInfo.add(it)
//                }
                //Toast.makeText(this, userInfo.toString(), Toast.LENGTH_LONG).show()

                val adapter = Adapter(this, userInfoItems)
                recyclerView.layoutManager = LinearLayoutManager(this)
                recyclerView.adapter = adapter

            },
            Response.ErrorListener {
                Toast.makeText(this, "Something went wrong: ${it.toString()}", Toast.LENGTH_LONG).show()
            })

        val volleyQueue = Volley.newRequestQueue(this)
        volleyQueue.add(stringRequest)
    }
}
