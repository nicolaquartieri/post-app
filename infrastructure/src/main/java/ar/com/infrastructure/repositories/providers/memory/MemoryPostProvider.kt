package ar.com.infrastructure.repositories.providers.memory

import ar.com.domain.entities.Post
import ar.com.infrastructure.repositories.providers.PostProvider

class MemoryPostProvider: PostProvider {
    private var list = mutableListOf(
    Post(0, 0, "Titulo 1", "Detalles 1"),
    Post(1, 1, "Titulo 2", "Detalles 2"),
    Post(2, 2, "Titulo 3", "Detalles 3"),
    Post(3, 3, "Titulo 4", "Detalles 4"),
    Post(4, 4, "Titulo 5", "Detalles 5"),
    Post(5, 5, "Titulo 6", "Detalles 6"),
    Post(6, 6, "Titulo 7", "Detalles 7"),
    Post(7, 7, "Titulo 8", "Detalles 8"))

    override suspend fun getAllPost(rowsOfPage: Int, page: Int): List<Post> {
        return list.subList(page, rowsOfPage)
    }

    override suspend fun insertAllPost(posts: List<Post>): List<Long> {
        list.addAll(posts)
        return list.map { it.id.toLong() }
    }
}