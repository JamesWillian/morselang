package com.jammes.morselang.core

import com.jammes.morselang.MorseItem

interface MorseRepository {

    fun fetchMorseList(): List<MorseItem>

    fun saveMorse(text: String, morse: String)
}