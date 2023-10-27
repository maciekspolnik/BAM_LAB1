package ms.projects.lab1.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.database.MatrixCursor
import android.net.Uri
import ms.projects.lab1.database.AppDatabase
import ms.projects.lab1.database.UserData

class AppContentProvider : ContentProvider() {
    private lateinit var database: AppDatabase

    override fun onCreate(): Boolean {
        database = AppDatabase.getInstance(context!!)
        return true
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor {
        val userDataList: List<UserData> = database.getUserDataDao().getAllObservable()
            .blockingFirst()
        val cursor = MatrixCursor(arrayOf("id", "username", "number"))
        for (userData in userDataList) {
            cursor.addRow(arrayOf(userData.id, userData.username, userData.number))
        }
        return cursor
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? = null
    override fun update(uri: Uri, values: ContentValues?, selection: String?, selectionArgs: Array<String>?): Int = 0
    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int = 0
    override fun getType(uri: Uri): String? = null
}
