package com.toadfrogson.clownhub.data.model

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

open class JokeModel : RealmObject {
    @PrimaryKey
    var dbId: ObjectId = ObjectId()
    var content: String = ""
    var category: String = ""
    var id: Int = 0
    var metadata: String = ""
    companion object {
        fun mapFromEntity(entity: JokeEntry) =
            JokeModel().apply {
                content = entity.joke
                category = entity.category
                id = entity.id
                metadata = buildMetadata(entity)
            }

        private fun buildMetadata(entity: JokeEntry) = buildString {
            append("joke id: ${entity.id}\n")
            append("joke is listed under flags: ${entity.flags}\n")
            val isSafeForWork = if (entity.safe) "safe for work" else "not safe for work"
            append("joke is $isSafeForWork\n")
            append("joke type is ${entity.type}\n")
        }
    }
}
