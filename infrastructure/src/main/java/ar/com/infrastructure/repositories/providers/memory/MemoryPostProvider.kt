package ar.com.infrastructure.repositories.providers.memory

import ar.com.domain.entities.Post
import ar.com.infrastructure.repositories.providers.remote.impl.PostProvider

class MemoryPostProvider: PostProvider {
    override fun getAllPost(rowsOfPage: Int, page: Int): List<Post> {
        val list = mutableListOf<Post>()
        list.add(Post(0, 0, "Titulo 1", "Detalles 1"))
        list.add(Post(1, 1, "Titulo 2", "Detalles 2"))
        list.add(Post(2, 2, "Titulo 3", "Detalles 3"))
        list.add(Post(3, 3, "Titulo 4", "Detalles 4"))
        return list
    }
}