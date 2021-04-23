package ar.com.postapp.listing.view

import android.content.Context
import android.view.View
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ar.com.infrastructure.repositories.PostRepository
import ar.com.postapp.listing.presenter.ui.UIComponents
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ListOfElements(private val context: Context,
                     private val lifecycleScope: LifecycleCoroutineScope,
                     private val repository: PostRepository
) : UIComponents<View> {

    @InternalCoroutinesApi
    override fun createView(): View {
        val recyclerView = RecyclerView(context)
        recyclerView.visibility = View.VISIBLE
        val postAdapter = PostAdapter()
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = postAdapter
        }

        val page = Pager(
            config = PagingConfig(
                initialLoadSize = 30,
                pageSize = 10
            )
        ) { PostPagingSource(repository) }
            .flow.cachedIn(lifecycleScope)

        lifecycleScope.launch {
            page.collect {
                postAdapter.submitData(it)
            }
        }

        return recyclerView
    }
}