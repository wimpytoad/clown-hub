package com.toadfrogson.clownhub.data.model

import kotlinx.serialization.Serializable

@Serializable
data class JokesEntity(
    val error: Boolean,
    val jokes: List<JokeEntry>,
)

@Serializable
data class JokeEntry(
    val category: String,
    val type: String,
    val joke: String,
    val flags: Map<String, Boolean>,
    val id: Int,
    val safe: Boolean,
    val lang: String,
)
