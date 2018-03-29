package loyarte.ivan.iguanafixmobilechallenge.repository

import io.reactivex.Observable
import loyarte.ivan.iguanafixmobilechallenge.domain.Contact
import loyarte.ivan.iguanafixmobilechallenge.service.ContactsApiService

class ContactsRepository() {
    private val apiService: ContactsApiService = ContactsApiService.create()

    fun getContacts(): Observable<List<Contact>> {
        return apiService.getContacts()
    }
}

object ContactsRepositoryProvider {
    fun provideContactsRepository(): ContactsRepository {
        return ContactsRepository()
    }
}
