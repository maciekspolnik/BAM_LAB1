package ms.projects.lab1.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import io.reactivex.rxjava3.core.Maybe

@Dao
interface UserDataDao {
    @Insert
    fun insert(userData: UserData)

    @Delete
    fun delete(userData: UserData)

    @Query("SELECT * FROM user_data")
    fun getAll(): Maybe<List<UserData>>

}