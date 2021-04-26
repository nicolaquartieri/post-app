package ar.com.infrastructure.repositories.providers.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ar.com.infrastructure.repositories.providers.local.db.entities.PostEntity

@Dao
interface PostDAO {
    @Query("SELECT * FROM post limit (:rowsOfPage) offset (:page)")
    fun getAll(rowsOfPage: Int, page: Int): List<PostEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(models: List<PostEntity>)

    @Insert
    fun insert(post: PostEntity)

    @Query("SELECT * FROM post WHERE :id")
    fun getById(id: Int): PostEntity

    @Query("DELETE from post")
    fun deleteAll()
}
