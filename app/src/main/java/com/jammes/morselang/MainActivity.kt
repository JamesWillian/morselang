package com.jammes.morselang

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.jammes.morselang.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.clearTxt.visibility = View.INVISIBLE
        binding.copyMorse.visibility = View.INVISIBLE
        binding.saveMorse.visibility = View.INVISIBLE

        binding.clearTxt.setOnClickListener {
            binding.editTextText.text.clear()
        }

        binding.editTextText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            /**
             *
             * Apos o texto ser alterado, chama a funcao para converter o texto para morse
             *
             */
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                val txt = s.toString().uppercase().trim()

                binding.editTextMorse.setText(convertToMorse( txt ))

            }

            /**
             *
             * Controla a visibilidade dos icones de Limpar Texto, Copiar e Salvar
             *
             */
            override fun afterTextChanged(s: Editable?) {

                if (binding.editTextText.text.toString() == "") {
                    binding.clearTxt.visibility = View.INVISIBLE
                } else {
                    binding.clearTxt.visibility = View.VISIBLE
                }

                if (binding.editTextMorse.text.toString() == "") {
                    binding.copyMorse.visibility = View.INVISIBLE
                    binding.saveMorse.visibility = View.INVISIBLE
                } else {
                    binding.copyMorse.visibility = View.VISIBLE
                    binding.saveMorse.visibility = View.VISIBLE
                }
            }
        })
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
