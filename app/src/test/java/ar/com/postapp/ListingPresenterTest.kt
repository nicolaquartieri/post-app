package ar.com.postapp

import ar.com.infrastructure.repositories.PostRepository
import ar.com.postapp.listing.presenter.ListingPresenter
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import org.junit.Before
import org.junit.Test

class ListingPresenterTest {

    @RelaxedMockK
    lateinit var repository: PostRepository

    @Before
    fun setUp() = MockKAnnotations.init(this, relaxUnitFun = true)

    @Test
    fun should_get_the_post_when_load_post_from_presenter() {
        val presenter = ListingPresenter(repository)

        presenter.loadPost()

        verify { repository.getPost() }
    }

}