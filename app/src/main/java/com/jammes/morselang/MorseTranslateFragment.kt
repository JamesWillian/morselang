package com.jammes.morselang

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.jammes.morselang.databinding.FragmentMorseTranslateBinding

/**
 * A simple [Fragment] subclass.
 */
class MorseTranslateFragment : Fragment() {

    private var _binding: FragmentMorseTranslateBinding? = null

    private val binding get() = _binding!!

    private val viewModel: MorseLangViewModel by activityViewModels{
        MorseLangViewModel.Factory()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMorseTranslateBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel
            .stateOnceAndStream()
            .observe(viewLifecycleOwner){}

        binding.button.setOnClickListener{
            showMorse()
        }
    }

    private fun showMorse() {
        binding.editTextMorse.setText(convertToMorse( binding.editTextText.text.toString().uppercase() ))
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
        " " to " ",
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

    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }

}