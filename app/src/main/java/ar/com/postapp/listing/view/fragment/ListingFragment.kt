package ar.com.postapp.listing.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import ar.com.postapp.R
import ar.com.postapp.common.RepositoryProvider
import ar.com.postapp.listing.presenter.ListingPresenter
import ar.com.postapp.listing.presenter.ui.UIComponents
import ar.com.postapp.listing.view.EmptyUI
import ar.com.postapp.listing.view.FABElementUI
import ar.com.postapp.listing.view.ListOfElementsUI
import kotlinx.android.synthetic.main.fragment_listing.*

class ListingFragment: Fragment() {
    companion object { const val TAG: String = "ListingFragment" }

    private lateinit var presenter: ListingPresenter
    private val listener: View.OnClickListener = View.OnClickListener {
        Toast.makeText(activity, "Click", Toast.LENGTH_SHORT).show()
        // TODO Do something.
    }

    private val repository = RepositoryProvider.repository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = ListingPresenter(repository)
        presenter.livePostData.observe(this, {
            Toast.makeText(activity, "Got the list with: ${it.size}", Toast.LENGTH_LONG).show()
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_listing, container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.loadPost()
        initUIWith()
    }

    private fun initUIWith() {
        val components = listUIComponents()
        display(components)
    }

    private fun listUIComponents(): List<UIComponents<View>> = listOf(
        EmptyUI(requireContext()),
        ListOfElementsUI(requireContext(), lifecycleScope, repository, listener),
        FABElementUI(requireContext())
    )

    private fun display(components: List<UIComponents<View>>) {
        components.map { it.createView() }.forEach { container.addView(it) }
    }
}