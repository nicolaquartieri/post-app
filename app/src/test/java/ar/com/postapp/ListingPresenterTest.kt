package ar.com.postapp

import ar.com.infrastructure.repositories.PostRepository
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Before

class ListingPresenterTest {

    @RelaxedMockK
    lateinit var repository: PostRepository

    @Before
    fun setUp() = MockKAnnotations.init(this, relaxUnitFun = true)



}