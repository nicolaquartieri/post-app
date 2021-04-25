package ar.com.infrastructure.repositories.providers.remote

import ar.com.domain.entities.Post
import ar.com.infrastructure.repositories.providers.PostProvider
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit


class RemotePostProvider: PostProvider {

    private val retrofit: Retrofit

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addNetworkInterceptor(interceptor)
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl("http://jsonplaceholder.typicode.com/")
            .addConverterFactory(JacksonConverterFactory.create())
            .client(client)
            .build()
    }

    private var postService = retrofit.create(PostService::class.java)

    override suspend fun getAllPost(rowsOfPage: Int, page: Int): List<Post> {
        return postService.getAllPost()
    }

    override suspend fun insertAllPost(posts: List<Post>) {
        // TODO
    }
}
