package com.jammes.morselang

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.jammes.morselang.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        supportActionBar!!.hide()

        binding.editTextText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                showMorse()
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun showMorse() {
        binding.editTextMorse.setText(convertToMorse( binding.editTextText.text.toString().uppercase().trim() ))
    }

    private fun convertToMorse(userText: String): String {
        var codeMorse = ""

        for (key in userText){
            val morseWord = alphabetMorse[key.toString()] ?: ""
            codeMorse += "$morseWord "
        }

        return codeMorse.trim()
    }

    private val alphabetMorse = mapOf<String, String>(
        "A" to ".-",
        "B" to "-...",
        "C" to "-.-.",
        "D" to "-..",
        "E" to ".",
        "F" to "..-.",
        "G" to "--.",
        "H" to "....",
        "I" to "..",
        "J" to ".---",
        "K" to "-.-",
        "L" to ".-..",
        "M" to "--",
        "N" to "-.",
        "O" to "---",
        "P" to ".--.",
        "Q" to "--.-",
        "R" to ".-.",
        "S" to "...",
        "T" to "-",
        "U" to "..-",
        "V" to "...-",
        "W" to ".--",
        "X" to "-..-",
        "Y" to "-.--",
        "Z" to "--..",
        " " to "/",
        "0" to "-----",
        "1" to ".----",
        "2" to "..---",
        "3" to "...--",
        "4" to "....-",
        "5" to ".....",
        "6" to "-....",
        "7" to "--...",
        "8" to "---..",
        "9" to "----.",
        "." to ".-.-.-",
        "," to "--..--",
        "?" to "..--..",
        "!" to "..--.",
        ":" to "---...",
        "'" to ".---.",
        "=" to "-...-",
        "(" to "-.--.",
        ")" to "-.--.-",
        "/" to "-..-.",
    )


}
