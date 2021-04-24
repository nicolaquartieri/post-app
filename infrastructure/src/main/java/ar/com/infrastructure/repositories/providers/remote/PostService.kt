package ar.com.infrastructure.repositories.providers.remote

import ar.com.domain.entities.Post
import retrofit2.http.GET

interface PostService {
    @GET("posts")
    suspend fun getAllPost(): List<Post>
}
