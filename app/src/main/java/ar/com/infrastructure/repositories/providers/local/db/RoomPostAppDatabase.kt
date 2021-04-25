package ar.com.infrastructure.repositories.providers.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ar.com.infrastructure.repositories.providers.local.db.entities.PostEntity

@Database(
    entities = [PostEntity::class],
    version = 1,
    exportSchema = false
)
abstract class RoomPostAppDatabase: RoomDatabase() {
    abstract fun postDAO(): PostDAO

    companion object {
        @Volatile
        private var INSTANCE: RoomPostAppDatabase? = null

        fun getInstance(context: Context): RoomPostAppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                RoomPostAppDatabase::class.java, "post_database.db")
                .build()
    }
}