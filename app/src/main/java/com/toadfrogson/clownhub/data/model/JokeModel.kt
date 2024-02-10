package com.toadfrogson.clownhub.data.model

data class JokeModel(val content: String, val category: String, val id: Int) {
    companion object {
        fun mapFromEntity(entity: JokeEntry) =
            JokeModel(content = entity.joke, category = entity.category, id = entity.id)
    }
}
