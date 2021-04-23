package ar.com.infrastructure.repositories.providers

import ar.com.domain.entities.Post
import ar.com.infrastructure.repositories.providers.remote.impl.PostProvider

class RemotePostProvider: PostProvider {

    override fun getAllPost(rowsOfPage: Int, page: Int): List<Post> {
        return emptyList()
    }
}