package hr.amaurov.niamu.orm_presentation.orm.interfaces

import hr.amaurov.niamu.orm_presentation.models.Contact

interface IORMService {
    fun createContact(contact: Contact)
    fun getAllContacts()
    fun getContactDetails(id: Long)
    fun updateContact(contact: Contact)
    fun deleteContact(id: Long)
}