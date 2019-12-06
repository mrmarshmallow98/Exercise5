package com.android.example.exercise5

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import java.util.prefs.AbstractPreferences

class MainActivity : AppCompatActivity() {

    lateinit var countViewModel: CountViewModel

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Initialise View Model
        countViewModel = ViewModelProviders.of(this).get(CountViewModel::class.java)

        //Initialise Shared Preferences
        sharedPreferences = getPreferences(Context.MODE_PRIVATE)

        imageViewLike.setOnClickListener{
            countViewModel.countLike++
            textViewLike.text = countViewModel.countLike.toString()
        }

        imageViewDislike.setOnClickListener{
            countViewModel.countDislike++
            textViewDislike.text = countViewModel.countDislike.toString()
        }
    }

    override fun onStart(){
        Log.d("MainActivity","onStart")
        super.onStart()
    }
    override fun onResume(){
        Log.d("MainActivity","onResume")

        countViewModel.countLike = sharedPreferences.getInt(getString(R.string.like), 0)
        countViewModel.countDislike = sharedPreferences.getInt(getString(R.string.dislike), 0)

        textViewLike.text = countViewModel.countLike.toString()
        textViewDislike.text = countViewModel.countDislike.toString()

        super.onResume()
    }
    override fun onPause(){
        Log.d("MainActivity","onPause")
        with(sharedPreferences.edit()){
            putInt(getString(R.string.like),countViewModel.countLike)
            putInt(getString(R.string.dislike),countViewModel.countDislike)
            commit()
        }
        super.onPause()
    }
    override fun onStop(){
        Log.d("MainActivity","onStop")
        super.onStop()
    }

    override fun onDestroy(){
        Log.d("MainActivity","onDestroy")
        super.onDestroy()
    }
}
