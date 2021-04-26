package ar.com.infrastructure.repositories.providers.local.db

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import ar.com.domain.entities.Post
import ar.com.infrastructure.repositories.providers.local.db.entities.PostEntity
import ar.com.infrastructure.repositories.providers.mappers.PostMapper
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RoomPostAppDatabaseTest {

    @Before
    fun setUp() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        roomPostAppDatabase = Room.inMemoryDatabaseBuilder(appContext,
            RoomPostAppDatabase::class.java).build()
        postDAO = roomPostAppDatabase.postDAO()
    }

    @After
    fun tearDown() {
        roomPostAppDatabase.close()
    }

    lateinit var postDAO: PostDAO
    lateinit var roomPostAppDatabase: RoomPostAppDatabase

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("ar.com.postapp", appContext.packageName)
    }

    @Test
    fun should_return_the_inserted_items() {
        val items = mutableListOf(
            Post(1, 1, "Titulo 1", "Detalles 1"),
            Post(2, 2, "Titulo 2", "Detalles 2"),
            Post(3, 3, "Titulo 3", "Detalles 3"),
            Post(4, 4, "Titulo 4", "Detalles 4"),
            Post(5, 5, "Titulo 5", "Detalles 5"),
            Post(6, 6, "Titulo 6", "Detalles 6"),
            Post(7, 7, "Titulo 7", "Detalles 7"),
            Post(8, 8, "Titulo 8", "Detalles 8")
        )
        val actualItems = PostMapper.toEntities(items)
        postDAO.insertAll(actualItems)

        val expectedItems = postDAO.getAll(20, 0)

        assertEquals(expectedItems, actualItems)
    }

    @Test
    fun should_delete_all_elements_in_table() {
        val items = mutableListOf(
            Post(1, 1, "Titulo 1", "Detalles 1"),
            Post(2, 2, "Titulo 2", "Detalles 2"),
            Post(3, 3, "Titulo 3", "Detalles 3"),
            Post(4, 4, "Titulo 4", "Detalles 4"),
            Post(5, 5, "Titulo 5", "Detalles 5"),
            Post(6, 6, "Titulo 6", "Detalles 6"),
            Post(7, 7, "Titulo 7", "Detalles 7"),
            Post(8, 8, "Titulo 8", "Detalles 8")
        )
        val actualItems = PostMapper.toEntities(items)
        postDAO.insertAll(actualItems)

        postDAO.deleteAll()

        val expectedItems = postDAO.getAll(20, 0)
        assertTrue(expectedItems.isEmpty())
    }

    @Test
    fun should_insert_one_element_in_table() {
        val post = PostEntity(1, 1, "Titulo 1", "Detalles 1")

        postDAO.insert(post)

        val expectedItems = postDAO.getAll(20, 0)
        assertTrue(expectedItems.size == 1)
    }

    @Test
    fun should_return_the_inserted_item() {
        val post = PostEntity(1, 1, "Titulo 1", "Detalles 1")
        postDAO.insert(post)

        val expectedItem = postDAO.getById(1)

        assertEquals(expectedItem, post)
    }
}