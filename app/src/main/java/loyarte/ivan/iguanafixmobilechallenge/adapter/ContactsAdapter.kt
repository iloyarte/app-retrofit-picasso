package loyarte.ivan.iguanafixmobilechallenge.adapter


import android.net.Uri
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import kotlinx.android.synthetic.main.contact_item.view.*
import loyarte.ivan.iguanafixmobilechallenge.R
import loyarte.ivan.iguanafixmobilechallenge.activity.MainActivity
import loyarte.ivan.iguanafixmobilechallenge.domain.Contact
import loyarte.ivan.iguanafixmobilechallenge.fragment.ContactDetailsFragment

class ContactsAdapter(private val mActivity: MainActivity, private var mContacts: List<Contact>): RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Contact
            var bundle = Bundle()
            bundle.putInt("contactId", item.user_id)
            var frag = ContactDetailsFragment()
            frag.arguments = bundle
            mActivity.addFragment(frag)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mContacts[position]
        with(holder) {
            mView.tag = item
            mView.setOnClickListener(mOnClickListener)
            mContactId.text = item.user_id.toString()
            mContactFullName.text = item.last_name + ", " + item.first_name

            mPicture.setImageURI(Uri.parse(item.thumb))
            mView.setOnClickListener(mOnClickListener)

        }
    }

    override fun getItemCount(): Int {
        return mContacts.size
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mContactId : TextView = mView.contactId
        val mContactFullName: TextView = mView.contactFullName
        val mPicture : ImageView = mView.contactThumb
//        val mProgress : ProgressBar = mView.thumbLoadingWheel
    }

    fun setData(contacts :List<Contact>?){
        if (contacts != null) {
            mContacts = contacts
            notifyDataSetChanged()
        }
    }
}
