package loyarte.ivan.iguanafixmobilechallenge.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_contact_details.*
import loyarte.ivan.iguanafixmobilechallenge.R
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
        repository.getContactDetails(contactID)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe ({
                    result ->
                    // Set adapter and render list


                }, { error ->
                    error.printStackTrace()
                })
    }


    companion object {
        private const val LAYOUT = R.layout.fragment_contact_details
        const val TAG = "ContactDetailsFragment"
        val CONTACT_ID_ARGUMENT = "contactId"
    }

}