package hr.amaurov.niamu.orm_presentation.orm.room.viewModels

import androidx.lifecycle.*
import hr.amaurov.niamu.orm_presentation.orm.room.entities.ContactRoom
import hr.amaurov.niamu.orm_presentation.orm.room.repositories.ContactRepository
import kotlinx.coroutines.launch

class ContactViewModel(private val repository: ContactRepository) : ViewModel() {


    val allContacts:LiveData<List<ContactRoom>> = repository.allContacts.asLiveData()

    fun getContactDetails(contactId: Long): ContactRoom {
        return repository.getContactDetails(contactId)
    }

    fun createContact(contact: ContactRoom) = viewModelScope.launch {
        repository.createContact(contact)
    }

    fun updateContact(contact: ContactRoom) = viewModelScope.launch {
        repository.updateContact(contact)
    }

    fun deleteContact(contactId: Long) = viewModelScope.launch {
        repository.deleteContact(contactId)
    }

    fun deleteAllContacts() = viewModelScope.launch {
        repository.deleteAllContacts()
    }
}

class ContactsViewModelFactory(private val repository: ContactRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ContactViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ContactViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}