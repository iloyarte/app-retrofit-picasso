package loyarte.ivan.iguanafixmobilechallenge.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.contact_item.view.*


import loyarte.ivan.iguanafixmobilechallenge.R
import loyarte.ivan.iguanafixmobilechallenge.domain.Contact

class ContactsAdapter(private val mContacts: List<Contact>?): RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Contact
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (mContacts != null) {
            val item = mContacts[position]
            holder.mName.text = item.last_name + ", " + item.first_name
            with(holder.mView) {
                tag = item
                setOnClickListener(mOnClickListener)
            }
        }
    }

    override fun getItemCount(): Int {
        return mContacts?.size ?: 0
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mName: TextView = mView.name
//        val mContentView: TextView = mView.content

//        override fun toString(): String {
//            return super.toString() + " '" + mContentView.text + "'"
//        }
    }
}
