package loyarte.ivan.iguanafixmobilechallenge.adapter


import android.net.Uri
import android.os.Bundle
import android.support.v4.widget.CircularProgressDrawable
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.facebook.drawee.view.SimpleDraweeView
import kotlinx.android.synthetic.main.contact_item.view.*
import loyarte.ivan.iguanafixmobilechallenge.R
import loyarte.ivan.iguanafixmobilechallenge.activity.MainActivity
import loyarte.ivan.iguanafixmobilechallenge.domain.Contact
import loyarte.ivan.iguanafixmobilechallenge.fragment.ContactDetailsFragment

class ContactsAdapter(private val mActivity: MainActivity, private var mContacts: List<Contact>): RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            if (!mActivity.detailsOpened) {
                mActivity.detailsOpened = true
                val item = v.tag as Contact
                val bundle = Bundle()
                val frag = ContactDetailsFragment()
                bundle.putInt("contactId", item.user_id)
                frag.arguments = bundle
                mActivity.addFragment(frag)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mActivity).inflate(R.layout.contact_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mContacts[position]
        with(holder) {
            mView.tag = item
            mView.setOnClickListener(mOnClickListener)
            mContactId.text = item.user_id.toString()
            mContactFullName.text = item.last_name + ", " + item.first_name

            mPicture.hierarchy.setProgressBarImage(CircularProgressDrawable(mActivity))
            mPicture.setImageURI(Uri.parse(item.thumb))
            mView.setOnClickListener(mOnClickListener)

        }
    }

    override fun getItemCount(): Int {
        return mContacts.size
    }

    fun setData(contacts :List<Contact>?){
        if (contacts != null) {
            mContacts = contacts
            notifyDataSetChanged()
        }
    }

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mContactId : TextView = mView.contactId
        val mContactFullName: TextView = mView.contactFullName
        val mPicture : SimpleDraweeView = mView.contactThumb
    }

}
