package ms.projects.lab1.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase


@Database(version = 1, entities = [UserData::class])
abstract class AppDatabase : RoomDatabase() {

    abstract fun getUserDataDao(): UserDataDao

    companion object {
        private var instance: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (instance == null)
                instance = databaseBuilder(
                    context.applicationContext, AppDatabase::class.java,
                    "database"
                )
                    .fallbackToDestructiveMigration()
                    .build()

            return instance!!

        }
    }


}