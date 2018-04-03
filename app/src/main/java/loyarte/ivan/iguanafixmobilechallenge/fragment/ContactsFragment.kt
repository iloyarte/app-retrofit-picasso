package loyarte.ivan.iguanafixmobilechallenge.fragment

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_contacts.*
import loyarte.ivan.iguanafixmobilechallenge.R
import loyarte.ivan.iguanafixmobilechallenge.activity.MainActivity
import loyarte.ivan.iguanafixmobilechallenge.adapter.ContactsAdapter
import loyarte.ivan.iguanafixmobilechallenge.domain.Contact
import loyarte.ivan.iguanafixmobilechallenge.repository.ContactsRepositoryProvider

class ContactsFragment : Fragment() {

    private val layoutManager: LinearLayoutManager = LinearLayoutManager(activity)
    private lateinit var dataset: List<Contact>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(LAYOUT, container, false).apply { tag = TAG }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        populate()
    }

    // Sets listeners to hide/show buttons when searching
    private fun initViews(){
        searchButton.setOnClickListener {
            searchText.visibility = View.VISIBLE
            backButton.visibility = View.VISIBLE
            title.visibility = View.GONE
            searchButton.visibility = View.GONE
            searchText.requestFocus()
            searchText.callOnClick()
        }
        backButton.setOnClickListener {
            searchText.visibility = View.GONE
            searchText.text.clear()
            backButton.visibility = View.GONE
            title.visibility = View.VISIBLE
            searchButton.visibility = View.VISIBLE

            //Hide keyboard
            (activity?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(view?.windowToken, 0)

        }
        searchText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                filterContacts(p0.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
    }

    private fun filterContacts(filterText : String) {
        val filteredDataSet = dataset.filter {
            it.last_name == filterText || it.last_name.contains(filterText) ||
            it.first_name == filterText || it.first_name.contains(filterText) ||
            it.user_id.toString() == filterText
        }
        (contactList.adapter as ContactsAdapter).setData(filteredDataSet)
    }


    private fun populate() {
        val repository = ContactsRepositoryProvider.provideContactsRepository()
        repository.getContacts({
            // Set adapter and render list
            dataset = it
            with(contactList) {
                layoutManager = this@ContactsFragment.layoutManager
                adapter = ContactsAdapter(this@ContactsFragment.activity as MainActivity, dataset)
            }
        })

    }


    fun back(){
        if (searchButton.visibility == View.GONE)
            backButton.callOnClick()
    }

    companion object {
        const val TAG = "ContactsFragment"
        private const val LAYOUT : Int = R.layout.fragment_contacts

        fun newInstance(): ContactsFragment {
            return ContactsFragment()
        }
    }

}
