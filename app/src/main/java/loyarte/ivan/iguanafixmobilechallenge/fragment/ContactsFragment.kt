package loyarte.ivan.iguanafixmobilechallenge.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import loyarte.ivan.iguanafixmobilechallenge.R
import loyarte.ivan.iguanafixmobilechallenge.adapter.ContactsAdapter
import loyarte.ivan.iguanafixmobilechallenge.domain.Contact
import loyarte.ivan.iguanafixmobilechallenge.repository.ContactsRepositoryProvider

class ContactsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var dataset: List<Contact>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getContacts()
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_contacts,
                container, false).apply { tag = TAG }

        recyclerView = rootView.findViewById(R.id.contacts_list) as RecyclerView

        // LinearLayoutManager is used here, this will layout the elements in a similar fashion
        // to the way ListView would layout elements. The RecyclerView.LayoutManager defines how
        // elements are laid out.
        layoutManager = LinearLayoutManager(activity)




        return rootView
    }

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
                    with(recyclerView) {
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
