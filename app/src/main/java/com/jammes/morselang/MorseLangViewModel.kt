package com.jammes.morselang

import androidx.lifecycle.*
import com.jammes.morselang.core.MorseRepository
import com.jammes.morselang.core.model.MorseDomain
import kotlinx.coroutines.launch

class MorseLangViewModel(private val repository: MorseRepository) : ViewModel() {

    private val uiState: MutableLiveData<UiState> by lazy {

        MutableLiveData<UiState>(UiState(morseItemList = emptyList()))
    }

    private fun MorseDomain.toMorseItem() : MorseItem {
        return MorseItem(
            id,
            text,
            morse
        )
    }

    fun stateOnceAndStream(): LiveData<UiState> = uiState

    fun saveMorse(text: String, morse: String) {
        viewModelScope.launch {
            repository.saveMorse(text, morse)
            refreshMorseList()
        }
    }

    private suspend fun refreshMorseList() {
        uiState.value?.let { currentUiState ->
            uiState.value = currentUiState.copy(
                morseItemList = repository.fetchMorseList().map {morse ->
                    morse.toMorseItem()
                }
            )
        }
    }

    fun convertToMorse(userText: String): String {

        val codeMorse = StringBuilder()

        for (key in userText){
            val morseLetter = alphabetMorse[key.toString()] ?: ""
            codeMorse.append(morseLetter).append(" ")
        }

        return codeMorse.toString().trim()
    }

    data class UiState(val morseItemList: List<MorseItem>)

    class Factory(private val repository: MorseRepository): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MorseLangViewModel(repository) as T
        }
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