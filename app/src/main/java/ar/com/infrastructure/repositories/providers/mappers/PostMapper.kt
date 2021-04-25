package ar.com.infrastructure.repositories.providers.mappers

import ar.com.domain.entities.Post
import ar.com.infrastructure.repositories.providers.local.db.entities.PostEntity

object PostMapper {
    fun fromEntities(entities: List<PostEntity>) =
        entities.map { Post(
            id = it.id,
            title = it.title,
            body = it.body,
            userId = it.userId)
        }

    fun toEntities(posts: List<Post>) =
        posts.map { PostEntity(id = it.id,
                               userId = it.userId,
                               title = it.title,
                               body = it.body)
        }
}
