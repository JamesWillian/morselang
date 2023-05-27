package com.jammes.morselang.core.repository

import com.jammes.morselang.MorseItem
import com.jammes.morselang.core.MorseRepository
import com.jammes.morselang.core.database.AppDatabase
import com.jammes.morselang.core.model.MorseDomain

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

    override suspend fun saveMorse(morse: MorseDomain) {
        TODO("Not yet implemented")
    }
}