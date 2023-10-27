package ms.projects.lab1.database;

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable

@Dao
interface UserDataDao {
    @Insert
    fun insert(userData: UserData): Completable

    @Query("SELECT * FROM user_data")
    fun getAll(): Maybe<List<UserData>>

    @Query("SELECT * FROM user_data")
    fun getAllObservable(): Observable<List<UserData>>

}