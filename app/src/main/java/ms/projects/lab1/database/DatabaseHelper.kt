package ms.projects.lab1.database

import android.content.Context
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe

class DatabaseHelper(private val context: Context) {

    fun insert(userData: UserData): Completable {
        return Completable.fromAction {
            AppDatabase.getInstance(context).getUserDataDao().insert(userData)
        }
    }

    fun getAll(): Maybe<List<UserData>> {
        return AppDatabase.getInstance(context).getUserDataDao().getAll()

    }


}