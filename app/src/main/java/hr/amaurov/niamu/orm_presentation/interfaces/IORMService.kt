package hr.amaurov.niamu.orm_presentation.interfaces

import hr.amaurov.niamu.orm_presentation.models.Contact

interface IORMService {
    fun createContact(contact: Contact)
    fun getAllContacts(): List<Contact>?
    fun getContactDetails(id: Long): Contact?
    fun updateContact(contact: Contact)
    fun deleteContact(id: Long)
}