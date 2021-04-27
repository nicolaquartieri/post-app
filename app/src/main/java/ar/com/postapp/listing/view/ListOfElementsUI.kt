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
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ListOfElementsUI(private val context: Context,
                       private val lifecycleScope: LifecycleCoroutineScope,
                       private val repository: PostRepository,
                       private val clickListener: View.OnClickListener? = null
) : UIComponents<View> {

    @InternalCoroutinesApi
    override fun createView(): View {
        val postAdapter = createAdapter(clickListener)
        val recyclerView = createRecycleView(postAdapter)
        val page = createPager()

        lifecycleScope.launch {
            postAdapter.loadStateFlow.collectLatest{loadStates ->
                Log.d("DEBUG", "LoadState.Loading ${loadStates.refresh is LoadState.Loading}")
                // TODO
                //progressBar.isVisible = loadStates.refresh is LoadState.Loading
                //retry.isVisible = loadState.refresh !is LoadState.Loading
                //errorMsg.isVisible = loadState.refresh is LoadState.Error
            }
        }
        lifecycleScope.launch {
            page.collect {
                postAdapter.submitData(it)
            }
        }

        return recyclerView
    }

    private fun createPager() = Pager(
        config = PagingConfig(
            initialLoadSize = 5,
            pageSize = 20
        )
    ) { PostPagingSource(repository) }
        .flow.cachedIn(lifecycleScope)

    private fun createRecycleView(postAdapter: PostAdapter): RecyclerView {
        val recycle = RecyclerView(context)
        recycle.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = postAdapter
            visibility = View.VISIBLE
        }

        return recycle
    }

    private fun createAdapter(listener: View.OnClickListener?): PostAdapter {
        val adapter = PostAdapter(listener)
        adapter.addLoadStateListener {states ->
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
                    Log.d("DEBUG", "Error ${it.error.message}")
                }
                Log.d("DEBUG", "NOT LoadState.Loading")
            }
        }
        adapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
                super.onItemRangeChanged(positionStart, itemCount)
                Log.d("DEBUG", "Count: $itemCount")
            }
        })
        return adapter
    }
}