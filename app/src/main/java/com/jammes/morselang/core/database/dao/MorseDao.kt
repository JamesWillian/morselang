package com.jammes.morselang.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.jammes.morselang.core.database.entity.Morse

@Dao
interface MorseDao {

    @Query("select * from morse")
    suspend fun fetchMorse(): List<Morse>

    @Insert
    suspend fun insert(morse: Morse)

}