package eu.tutorials.myshoppal.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import eu.tutorials.myshoppal.data.local.model.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

}