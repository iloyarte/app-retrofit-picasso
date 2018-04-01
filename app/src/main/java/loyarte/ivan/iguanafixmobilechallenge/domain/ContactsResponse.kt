package loyarte.ivan.iguanafixmobilechallenge.domain


data class Contact(
    val user_id : Int,
    val created_at: String,
    val birth_date: String,
    val first_name: String,
    val last_name: String,
    val phones: List<ContactPhone>,
    val addresses: List<ContactAddress>,
    val thumb: String,
    val photo: String
)

data class ContactPhone(
        val type: String,
        val number: String
)

data class ContactAddress(
        val home: String,
        val work: String
)


