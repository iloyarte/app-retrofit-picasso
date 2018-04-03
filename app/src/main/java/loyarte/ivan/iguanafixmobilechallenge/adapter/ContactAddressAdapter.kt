package loyarte.ivan.iguanafixmobilechallenge.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.contact_detail_item.view.*
import loyarte.ivan.iguanafixmobilechallenge.R
import loyarte.ivan.iguanafixmobilechallenge.activity.MainActivity
import loyarte.ivan.iguanafixmobilechallenge.domain.ContactAddress

class ContactAddressAdapter(private val activity: MainActivity, private var mContactAddresses: List<ContactAddress>): RecyclerView.Adapter<ContactAddressAdapter.ViewHolder>() {


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contactAddress : ContactAddress = mContactAddresses[position]
        if (!contactAddress.home.isEmpty()){
            holder.itemView.detail.text = contactAddress.home
            holder.itemView.type.setImageResource(R.drawable.ic_home)
        } else {
            holder.itemView.detail.text = contactAddress.work
            holder.itemView.type.setImageResource(R.drawable.ic_work)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(activity).inflate(R.layout.contact_detail_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mContactAddresses.size
    }



    inner class ViewHolder(val mView: View): RecyclerView.ViewHolder(mView)

}
