package com.toadfrogson.clownhub.app.model

import androidx.annotation.StringRes
import com.toadfrogson.clownhub.R

sealed interface JokeType {
    @get:StringRes
    val translatedValue: Int
    val value: String

    data object AllJokes : JokeType {
        override val translatedValue: Int
            get() = R.string.joke_category_all
        override val value: String
            get() = ""
    }

    data object Miscellaneous : JokeType {
        override val translatedValue: Int
            get() = R.string.joke_category_miscellaneous
        override val value: String
            get() = "Misc"
    }

    data object Programming : JokeType {
        override val translatedValue: Int
            get() = R.string.joke_category_programming
        override val value: String
            get() = "Programming"
    }

    data object Pun : JokeType {
        override val translatedValue: Int
            get() = R.string.joke_category_pun
        override val value: String
            get() = "Pun"
    }

    data object Spooky : JokeType {
        override val translatedValue: Int
            get() = R.string.joke_category_spooky
        override val value: String
            get() = "Spooky"
    }
}

object JokesCategoriesList {
    val jokesList = listOf(
        JokeType.AllJokes,
        JokeType.Miscellaneous,
        JokeType.Programming,
        JokeType.Pun,
        JokeType.Spooky
    )
}
