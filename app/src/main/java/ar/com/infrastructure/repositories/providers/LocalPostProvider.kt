package ar.com.infrastructure.repositories.providers

import ar.com.PostApplication
import ar.com.domain.entities.Post
import ar.com.infrastructure.repositories.providers.local.db.RoomPostAppDatabase
import ar.com.infrastructure.repositories.providers.local.db.entities.PostEntity
import ar.com.infrastructure.repositories.providers.mappers.PostMapper

class LocalPostProvider: PostProvider {
    private val dao = RoomPostAppDatabase.getInstance(PostApplication.INSTANCE).postDAO()

    override suspend fun getAllPost(rowsOfPage: Int, page: Int): List<Post> {
        val entities = dao.getAll(rowsOfPage, page)
        return PostMapper.fromEntities(entities)
    }

    override suspend fun insertAllPost(posts: List<Post>): List<Long> {
        val postsEntities: List<PostEntity> = PostMapper.toEntities(posts)
        return dao.insertAll(postsEntities)
    }
}