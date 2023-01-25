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

    data class UiState(val MorseTranslationList: List<MorseTranslation>)

    class Factory(): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MorseLangViewModel() as T
        }
    }
}