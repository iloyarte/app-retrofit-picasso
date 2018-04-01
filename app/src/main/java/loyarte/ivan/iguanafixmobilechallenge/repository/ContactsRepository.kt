package loyarte.ivan.iguanafixmobilechallenge.repository

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import loyarte.ivan.iguanafixmobilechallenge.domain.Contact
import loyarte.ivan.iguanafixmobilechallenge.service.ContactsApiService

class ContactsRepository() {
    private val apiService: ContactsApiService = ContactsApiService.create()

    fun getContacts(onSuccess: (List<Contact>) -> Unit): Disposable? {
        return apiService.getContacts()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(onSuccess, { error ->
                    error.printStackTrace()
                })
    }

    fun getContactDetails(contactId: Int, onSuccess: (Contact) -> Unit): Disposable? {
        return apiService.getContactDetails(contactId)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(onSuccess, { error ->
                    error.printStackTrace()
                })
    }
}

object ContactsRepositoryProvider {
    fun provideContactsRepository(): ContactsRepository {
        return ContactsRepository()
    }
}
