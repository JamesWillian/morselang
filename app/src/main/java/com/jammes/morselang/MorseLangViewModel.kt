package com.jammes.morselang

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MorseLangViewModel : ViewModel() {

    private val uiState: MutableLiveData<UiState> by lazy {

        MutableLiveData<UiState>()
    }

    fun stateOnceAndStream(): LiveData<UiState> = uiState

    fun convertToMorse(userText: String): String {

        val codeMorse = StringBuilder()

        for (key in userText){
            val morseLetter = alphabetMorse[key.toString()] ?: ""
            codeMorse.append(morseLetter).append(" ")
        }

        return codeMorse.toString().trim()
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

    data class UiState(val MorseItemList: List<MorseItem>)

    class Factory(): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MorseLangViewModel() as T
        }
    }
}