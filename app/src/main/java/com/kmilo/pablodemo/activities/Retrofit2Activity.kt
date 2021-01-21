package com.kmilo.pablodemo.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import com.kmilo.pablodemo.R
import com.kmilo.pablodemo.interfaces.IApiRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

const val BASE_URL = "https://cat-fact.herokuapp.com"

class Retrofit2Activity : AppCompatActivity() {

    private var TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit2)

        val layoutGenerateNewFact = findViewById<RelativeLayout>(R.id.layout_generate_new_fact)

        getCurrentData()

        layoutGenerateNewFact.setOnClickListener {
            getCurrentData()
        }
    }

    private fun getCurrentData(){
        val tv_textView = findViewById<TextView>(R.id.tv_textView)
        val tv_timeStamp = findViewById<TextView>(R.id.tv_timeStamp)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)

        tv_textView.visibility = View.INVISIBLE
        tv_timeStamp.visibility = View.INVISIBLE
        progressBar.visibility = View.VISIBLE

        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IApiRequest::class.java)

        GlobalScope.launch(Dispatchers.IO){

            try{
                val response = api.getCatFacts().awaitResponse()
                if (response.isSuccessful){
                    val data = response.body()!!
                    Log.d(TAG, data.text)

                    withContext(Dispatchers.Main){
                        tv_textView.visibility = View.VISIBLE
                        tv_timeStamp.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE

                        tv_textView.text = data.text
                        tv_timeStamp.text = data.createdAt
                    }
                }
            }catch (e: Exception){
                withContext(Dispatchers.Main){
                    Toast.makeText(applicationContext,"Seems like something went wrong...", Toast.LENGTH_LONG).show()
                }
            }

        }
    }
}