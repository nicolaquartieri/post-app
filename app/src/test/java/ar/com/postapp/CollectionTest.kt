package ar.com.postapp

import ar.com.domain.entities.Post
import io.mockk.MockKAnnotations
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class CollectionTest {

    @Before
    fun setUp() = MockKAnnotations.init(this, relaxUnitFun = true)

    @Test(expected = IndexOutOfBoundsException::class)
    fun should_get_the_post_when_load_post_from_presenter() {
        val list = mutableListOf(
            Post(0, 0, "Titulo 1", "Detalles 1"),
            Post(1, 1, "Titulo 2", "Detalles 2"),
            Post(2, 2, "Titulo 3", "Detalles 3"),
            Post(3, 3, "Titulo 4", "Detalles 4"),
            Post(4, 4, "Titulo 5", "Detalles 5"),
            Post(5, 5, "Titulo 6", "Detalles 6"),
            Post(6, 6, "Titulo 7", "Detalles 7"),
            Post(7, 7, "Titulo 8", "Detalles 8")
        )

        val newList = list.subList(0, 9)

        Assert.assertEquals(9, newList.size)
    }

}