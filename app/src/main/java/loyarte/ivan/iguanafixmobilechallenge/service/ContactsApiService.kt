package loyarte.ivan.iguanafixmobilechallenge.service

import io.reactivex.Observable
import loyarte.ivan.iguanafixmobilechallenge.domain.Contact
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path


interface ContactsApiService {

    @GET("contacts")
    fun getContacts() : Observable<List<Contact>>

    @GET("contacts/{id}")
    fun getContactDetails(@Path("id") id: Int) :Observable<Contact>

    companion object Factory {
        fun create(): ContactsApiService {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(MoshiConverterFactory.create())
                    .baseUrl("https://private-d0cc1-iguanafixtest.apiary-mock.com/")
                    .build()

            return retrofit.create(ContactsApiService::class.java)
        }
    }
}