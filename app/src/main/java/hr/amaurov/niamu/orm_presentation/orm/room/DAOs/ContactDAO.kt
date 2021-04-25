package hr.amaurov.niamu.orm_presentation.orm.room.DAOs

import androidx.annotation.WorkerThread
import androidx.room.*
import hr.amaurov.niamu.orm_presentation.orm.room.entities.ContactRoom
import kotlinx.coroutines.flow.Flow

@Dao
interface ContactDAO {
    @Query("SELECT * FROM contacts")
    fun getAllContacts(): Flow<List<ContactRoom>>

    @Query("SELECT * FROM contacts WHERE contactID=:id")
    fun getContactDetails(id: Long): ContactRoom

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun createContact(contact: ContactRoom)

    @Update
    suspend fun updateContact(contact: ContactRoom)

    @Query("DELETE FROM contacts where contactID=:contactId")
    suspend fun deleteContact(contactId: Long)

    @Query("DELETE FROM contacts")
    suspend fun deleteAllContacts()
}