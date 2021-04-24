package hr.amaurov.niamu.orm_presentation.dao.interfaces

import hr.amaurov.niamu.orm_presentation.models.City
import hr.amaurov.niamu.orm_presentation.models.Contact

interface IORMService {
    fun createContact(contact: Contact)
    fun getAllContacts(): List<Contact>?
    fun getContactDetails(id: Long): Contact?
    fun updateContact(contact: Contact)
    fun deleteContact(id: Long)
    fun getAllCities(): List<City>?
}