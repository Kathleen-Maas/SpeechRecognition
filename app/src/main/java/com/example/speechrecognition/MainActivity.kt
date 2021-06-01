package com.example.speechrecognition

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import com.example.speechrecognition.databinding.ActivityMainBinding
import androidx.core.app.ActivityCompat

import android.os.Build
import android.speech.RecognizerIntent
import androidx.core.content.ContextCompat
import android.widget.ImageView

import android.widget.EditText

import android.speech.SpeechRecognizer
import java.util.*
import java.util.ArrayList

import android.R
import android.annotation.SuppressLint

import android.speech.RecognitionListener
import android.util.Log
import android.util.Log.DEBUG
import android.view.MotionEvent

import android.view.View
import android.view.View.OnTouchListener
import android.widget.Toast
import androidx.annotation.NonNull


class MainActivity : AppCompatActivity() {

    val RecordAudioRequestCode = 1
    private var speechRecognizer: SpeechRecognizer? = null

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Kathy Jo", "Hi")
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(ContextCompat.checkSelfPermission(this,Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED){
            checkPermission();
        }

        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);

        val speechRecognizerIntent: Intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        speechRecognizer?.setRecognitionListener(Listener())

//        (object : RecognitionListener {
//            override fun onReadyForSpeech(params: Bundle?) {}
//            override fun onBeginningOfSpeech() {
//                Log.d("Kathy Jo", "listening")
//                binding.text.setText("")
//                binding.text.hint = "Listening..."
//            }
//
//            override fun onRmsChanged(rmsdB: Float) {}
//            override fun onBufferReceived(buffer: ByteArray?) {}
//            override fun onEndOfSpeech() {}
//            override fun onError(error: Int) {}
//            override fun onResults(results: Bundle?) {
//                binding.button.setImageResource(R.drawable.ic_input_get)
//                val data = results?.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION)
//                binding.text.setText(data!![0])
//            }
//
//            override fun onPartialResults(partialResults: Bundle?) {}
//            override fun onEvent(eventType: Int, params: Bundle?) {}
//        })

        binding.button.setOnTouchListener(OnTouchListener { _, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_UP) {
                speechRecognizer?.stopListening()
                Log.d("Kathy Jo", "See release")
            }
            if (motionEvent.action == MotionEvent.ACTION_DOWN) {
                binding.button.setImageResource(R.drawable.arrow_down_float)
                speechRecognizer?.startListening(speechRecognizerIntent)
                Log.d("Kathy Jo", "See Press")
            }
            false
        })


    }

    override fun onDestroy() {
        super.onDestroy()
        speechRecognizer!!.destroy()
    }

    private fun checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.RECORD_AUDIO),
                RecordAudioRequestCode
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == RecordAudioRequestCode && grantResults.isNotEmpty()){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
                Toast.makeText(this,"Permission Granted",Toast.LENGTH_SHORT).show();
        }
    }
}

class Listener : RecognitionListener {
    override fun onReadyForSpeech(params: Bundle?) {
        TODO("Not yet implemented")
    }

    override fun onBeginningOfSpeech() {
        Log.d("Kathy Jo", "Beginning speech")
    }

    override fun onRmsChanged(rmsdB: Float) {
        TODO("Not yet implemented")
    }

    override fun onBufferReceived(buffer: ByteArray?) {
        TODO("Not yet implemented")
    }

    override fun onEndOfSpeech() {
        TODO("Not yet implemented")
    }

    override fun onError(error: Int) {
        TODO("Not yet implemented")
    }

    override fun onResults(results: Bundle?) {
        Log.d("Kathy Jo", "results")
    }

    override fun onPartialResults(partialResults: Bundle?) {
        TODO("Not yet implemented")
    }

    override fun onEvent(eventType: Int, params: Bundle?) {
        TODO("Not yet implemented")
    }

}
