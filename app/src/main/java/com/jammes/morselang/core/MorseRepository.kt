package com.jammes.morselang.core

import com.jammes.morselang.MorseItem
import com.jammes.morselang.core.model.MorseDomain

interface MorseRepository {

    suspend fun fetchMorseList(): List<MorseDomain>

    suspend fun saveMorse(text: String, morse: String)
}