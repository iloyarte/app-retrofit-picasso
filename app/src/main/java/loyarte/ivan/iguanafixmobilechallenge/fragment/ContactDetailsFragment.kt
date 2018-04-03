package loyarte.ivan.iguanafixmobilechallenge.fragment

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.CircularProgressDrawable
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.birthday_item.*
import kotlinx.android.synthetic.main.fragment_contact_details.*
import loyarte.ivan.iguanafixmobilechallenge.R
import loyarte.ivan.iguanafixmobilechallenge.activity.MainActivity
import loyarte.ivan.iguanafixmobilechallenge.adapter.ContactAddressAdapter
import loyarte.ivan.iguanafixmobilechallenge.adapter.ContactPhoneAdapter
import loyarte.ivan.iguanafixmobilechallenge.domain.Contact
import loyarte.ivan.iguanafixmobilechallenge.repository.ContactsRepositoryProvider

class ContactDetailsFragment : Fragment() {

    private lateinit var mContact : Contact
    private var contactID: Int = 0

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        contactID = arguments!!.getInt(CONTACT_ID_ARGUMENT)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(LAYOUT, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        populate()
    }


    private fun initViews() {
    }

    private fun populate() {
        val repository = ContactsRepositoryProvider.provideContactsRepository()
        repository.getContactDetails(contactID, {
            // Populate
            profilePicture.hierarchy.setProgressBarImage(CircularProgressDrawable(context!!))
            profilePicture.setImageURI(Uri.parse(it.photo))
            collapsing_toolbar.title = it.first_name + " " + it.last_name
            birthDate.text = it.birth_date
            with(phoneList){
                adapter = ContactPhoneAdapter(it.phones.filter { !it.number.isNullOrEmpty() })
                layoutManager = LinearLayoutManager(activity)
            }
            with(addressList){
                adapter = ContactAddressAdapter((activity as MainActivity) ,it.addresses.filter { !it.isNullOrEmpty() })
                layoutManager = LinearLayoutManager(activity)
            }
            progress.visibility = View.GONE
            detailsLayout.visibility = View.VISIBLE
        })
    }

    companion object {
        private const val LAYOUT = R.layout.fragment_contact_details
        const val TAG = "ContactDetailsFragment"
        val CONTACT_ID_ARGUMENT = "contactId"
    }

}