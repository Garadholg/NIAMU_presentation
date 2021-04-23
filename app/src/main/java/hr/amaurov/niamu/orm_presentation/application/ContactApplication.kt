package hr.amaurov.niamu.orm_presentation.application

import android.app.Application
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import hr.amaurov.niamu.orm_presentation.orm.room.database.ApplicationRoomDatabase
import hr.amaurov.niamu.orm_presentation.orm.room.repositories.ContactRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class ContactApplication : Application(),ViewModelStoreOwner {
    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy {ApplicationRoomDatabase.getDatabase(this)}
    val contactRepository by lazy { ContactRepository(database.contactDAO())}

    private val appViewModelStore: ViewModelStore by lazy {
        ViewModelStore()
    }

    override fun getViewModelStore(): ViewModelStore {
        return appViewModelStore
    }
}