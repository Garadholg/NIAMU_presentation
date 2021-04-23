package hr.amaurov.niamu.orm_presentation.orm.room.repositories

import hr.amaurov.niamu.orm_presentation.orm.room.DAOs.ContactDAO
import hr.amaurov.niamu.orm_presentation.orm.room.entities.ContactRoom
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList


class ContactRepository(private val contactDAO: ContactDAO) {

    val allContacts: Flow<List<ContactRoom>> =contactDAO.getAllContacts()

    suspend fun createContact(contact: ContactRoom) {
        contactDAO.createContact(contact)
    }

    fun getContactDetails(id: Long) :ContactRoom {
        return contactDAO.getContactDetails(id)
    }

    suspend fun updateContact(contact: ContactRoom) {
        contactDAO.updateContact(contact)
    }

    suspend fun deleteContact(contactId: Long) {
        contactDAO.deleteContact(contactId)
    }

    suspend fun deleteAllContacts() {
        contactDAO.deleteAllContacts()
    }
}