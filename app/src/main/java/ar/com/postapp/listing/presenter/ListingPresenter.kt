package ar.com.postapp.listing.presenter

import androidx.lifecycle.MutableLiveData
import ar.com.domain.entities.Post
import ar.com.infrastructure.repositories.PostRepository
import ar.com.presentation.Presenter

class ListingPresenter(private val repository: PostRepository) : Presenter {

    val livePostData = MutableLiveData<List<Post>>()

    init {

    }

    fun loadPost() {
        //val posts = repository.getPost()
        //livePostData.postValue(posts)
    }
}
