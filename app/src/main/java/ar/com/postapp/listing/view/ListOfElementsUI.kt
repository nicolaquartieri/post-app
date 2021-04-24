package ar.com.postapp.listing.view

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.paging.LoadState
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

class ListOfElementsUI(private val context: Context,
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

        postAdapter.addLoadStateListener {states ->
            if (states.refresh == LoadState.Loading) {
                Toast.makeText(context, "Loading", Toast.LENGTH_SHORT).show()
                Log.d("DEBUG", "LoadState.Loading")
            } else {
                val error = when {
                    states.prepend is LoadState.Error -> states.prepend as LoadState.Error
                    states.append is LoadState.Error -> states.append as LoadState.Error
                    states.refresh is LoadState.Error -> states.refresh as LoadState.Error
                    else -> null
                }
                error?.let {
                    Toast.makeText(context, "Error ${it.error.message}", Toast.LENGTH_LONG).show()
                }
                Log.d("DEBUG", "NOT LoadState.Loading")
            }
        }

        val page = Pager(
            config = PagingConfig(
                initialLoadSize = 5,
                pageSize = 20
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