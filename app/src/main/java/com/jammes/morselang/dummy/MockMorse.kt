package com.jammes.morselang.dummy

import com.jammes.morselang.MorseItem
import com.jammes.morselang.core.MorseRepository
import java.util.UUID

object MockMorse: MorseRepository {

    val randomMorseList = listOf(
        MorseItem(
            id = UUID.randomUUID().toString(),
            text = "Read the book",
            morse = ".-. . .- -.. / - .... . / -... --- --- -.-"
        ),
        MorseItem(
            id = UUID.randomUUID().toString(),
            text = "Walk the dog",
            morse = ".-- .- .-.. -.- / - .... . / -.. --- --."
        ),
        MorseItem(
            id = UUID.randomUUID().toString(),
            text = "Do the dishes",
            morse = ".-.. --- / - .... . / -.. .. ... .... . ..."
        ),
        MorseItem(
            id = UUID.randomUUID().toString(),
            text = "Go to the gym",
            morse = "--. --- / - --- / - .... . / --. -.-- --"
        ),
        MorseItem(
            id = UUID.randomUUID().toString(),
            text = "Code every day",
            morse = "-.-. --- -.. . / . ...- . .-. -.-- / -.. .- -.--"
        ),
        MorseItem(
            id = UUID.randomUUID().toString(),
            text = "Make a cup of tea",
            morse = "-- .- -.- . / .- / -.-. ..- .--. / --- ..-. / - . .-"
        ),
        MorseItem(
            id = UUID.randomUUID().toString(),
            text = "Make a cup of coffee",
            morse = "-- .- -.- . / .- / -.-. ..- .--. / --- ..-. / -.-. --- ..-. ..-. . ."
        )
    )

    private val morseItemList: MutableList<MorseItem> = mutableListOf()

    override fun fetchMorseList() = morseItemList.map { it.copy() }

    override fun saveMorse(text: String, morse: String) {
        morseItemList.add(
            MorseItem(id = UUID.randomUUID().toString(), text = text, morse = morse)
        )
    }
}