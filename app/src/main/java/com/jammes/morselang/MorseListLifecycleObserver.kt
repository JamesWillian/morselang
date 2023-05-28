package com.jammes.morselang

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner

class MorseListLifecycleObserver(private val viewModel: MorseLangViewModel) : DefaultLifecycleObserver {
    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        viewModel.onResume()
    }
}