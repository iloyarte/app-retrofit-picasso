package loyarte.ivan.iguanafixmobilechallenge.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_contacts.*
import loyarte.ivan.iguanafixmobilechallenge.R
import loyarte.ivan.iguanafixmobilechallenge.adapter.ContactsAdapter
import loyarte.ivan.iguanafixmobilechallenge.domain.Contact
import loyarte.ivan.iguanafixmobilechallenge.repository.ContactsRepositoryProvider

class ContactsFragment : Fragment() {

    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var dataset: List<Contact>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_contacts,
                container, false).apply { tag = TAG }

        layoutManager = LinearLayoutManager(activity)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar()
        getContacts()
    }


    // Sets listeners to hide/show buttons when searching
    private fun initToolbar(){
        searchButton.setOnClickListener {
            searchText.visibility = View.VISIBLE
            backButton.visibility = View.VISIBLE
            title.visibility = View.GONE
            filterButton.visibility = View.GONE
            searchButton.visibility = View.GONE
            searchText.requestFocus()
            searchText.callOnClick()
        }
        backButton.setOnClickListener {
            searchText.visibility = View.GONE
            searchText.text.clear()
            backButton.visibility = View.GONE
            title.visibility = View.VISIBLE
            filterButton.visibility = View.VISIBLE
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
        (contactList.adapter as ContactsAdapter).setData(filteredDataSet)    }

    private fun getContacts() {
        val repository = ContactsRepositoryProvider.provideContactsRepository()
        repository.getContacts()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe ({
                    result ->
                    // Set adapter and render list
                    dataset = result
                    // Set CustomAdapter as the adapter for RecyclerView.
                    with(contactList) {
                        layoutManager = this@ContactsFragment.layoutManager
                        adapter = ContactsAdapter(dataset)
                    }
                    Log.d("Result", "There are ${result.size} contacts")
                }, { error ->
                    error.printStackTrace()
                })

    }

    companion object {
        private val TAG = "ContactsFragment"
    }

}
