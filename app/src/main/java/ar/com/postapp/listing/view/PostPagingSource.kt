package ar.com.postapp.listing.view

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import ar.com.domain.entities.Post
import ar.com.infrastructure.repositories.PostRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostPagingSource(private val repository: PostRepository) : PagingSource<Int, Post>() {

    override fun getRefreshKey(state: PagingState<Int, Post>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Post> {
        return try {
            val page = params.key ?: 0

            var data = emptyList<Post>()

            withContext(Dispatchers.IO) {
                data = repository.getPost(params.loadSize, page * params.loadSize)
            }

            Log.d("DEBUG", "Page: $page - ParamSize: ${params.loadSize} - dataSize: ${data.size}")

            LoadResult.Page(
                    data = data,
                    prevKey = if (page == 0) null else page - 1,
                    nextKey = if (data.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}