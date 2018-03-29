package loyarte.ivan.iguanafixmobilechallenge.domain

import java.util.*


data class Contact(
    val user_id : Int,
    val created_at: String,
    val birth_date: String,
    val first_name: String,
    val last_name: String,
    val phones: List<ContactPhone>,
    val thumb: String,
    val photo: String
)

data class ContactPhone(
        val type: String,
        val number: String
)


