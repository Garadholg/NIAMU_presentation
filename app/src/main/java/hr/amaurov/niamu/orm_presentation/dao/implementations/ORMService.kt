package hr.amaurov.niamu.orm_presentation.dao.implementations

import hr.amaurov.niamu.orm_presentation.dao.interfaces.IORMService
import hr.amaurov.niamu.orm_presentation.dao.repo.DBRepo
import hr.amaurov.niamu.orm_presentation.models.City
import hr.amaurov.niamu.orm_presentation.models.CityDao
import hr.amaurov.niamu.orm_presentation.models.Contact
import hr.amaurov.niamu.orm_presentation.models.ContactDao

object ORMService : IORMService {

    private val contactDao: ContactDao? = DBRepo.daoSession?.contactDao
    private val cityDao: CityDao? = DBRepo.daoSession?.cityDao

    override fun createContact(contact: Contact) {
        contactDao?.insert(contact)
    }

    override fun getAllContacts(): List<Contact>? {
        return contactDao?.loadAll()
    }

    override fun getContactDetails(id: Long): Contact? {
        return contactDao!!.queryBuilder().where(ContactDao.Properties.Id.eq(id)).build().unique()
    }

    override fun updateContact(contact: Contact) {
        contactDao?.update(contact)
    }

    override fun deleteContact(id: Long) {
        val contact = contactDao!!.queryBuilder().where(ContactDao.Properties.Id.eq(id)).build().unique()
        contactDao?.delete(contact)
    }

    override fun getAllCities(): List<City>? {
        return cityDao?.loadAll()
    }
} 
