package com.jammes.morselang.core.repository

import com.jammes.morselang.MorseItem
import com.jammes.morselang.core.MorseRepository
import com.jammes.morselang.core.database.AppDatabase
import com.jammes.morselang.core.database.entity.Morse
import com.jammes.morselang.core.model.MorseDomain
import java.util.*

class MorseRepositoryImpl(appDatabase: AppDatabase): MorseRepository {

    private val dao = appDatabase.morseDao()

    override suspend fun fetchMorseList(): List<MorseDomain> {
        return dao.fetchMorse().map {
            MorseDomain(
                id = it.uuid,
                text = it.text,
                morse = it.morse
            )
        }
    }

    override suspend fun saveMorse(text: String, morse: String) {
        val morse = Morse(
            uuid = UUID.randomUUID().toString(),
            text = text,
            morse = morse
        )
        dao.insert(morse)
    }
}