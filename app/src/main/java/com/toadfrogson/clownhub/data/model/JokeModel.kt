package com.toadfrogson.clownhub.data.model

data class JokeModel(val content: String, val category: String, val id: Int, val metadata: String) {
    companion object {
        fun mapFromEntity(entity: JokeEntry) =
            JokeModel(
                content = entity.joke,
                category = entity.category,
                id = entity.id,
                metadata = buildMetadata(entity)
            )

        private fun buildMetadata(entity: JokeEntry) = buildString {
            append("joke id: ${entity.id}\n")
            append("joke is listed under flags: ${entity.flags}\n")
            val isSafeForWork = if (entity.safe) "safe for work" else "not safe for work"
            append("joke is $isSafeForWork\n")
            append("joke type is ${entity.type}\n")
        }
    }
}
