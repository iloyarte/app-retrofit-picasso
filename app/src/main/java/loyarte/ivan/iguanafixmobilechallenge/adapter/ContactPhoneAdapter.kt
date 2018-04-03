package loyarte.ivan.iguanafixmobilechallenge.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.contact_detail_item.view.*
import loyarte.ivan.iguanafixmobilechallenge.R
import loyarte.ivan.iguanafixmobilechallenge.domain.ContactPhone

class ContactPhoneAdapter(private var mContactPhones: List<ContactPhone>): RecyclerView.Adapter<ContactPhoneAdapter.ViewHolder>() {


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contactPhone : ContactPhone = mContactPhones[position]
        if (!contactPhone.number.isNullOrEmpty()) {
            when (contactPhone.type) {
                "Cellphone" -> holder.itemView.type.setImageResource(R.drawable.ic_smartphone)
                "Home" -> holder.itemView.type.setImageResource(R.drawable.ic_home)
                "Office" -> holder.itemView.type.setImageResource(R.drawable.ic_work)
            }
            holder.itemView.detail.text = contactPhone.number
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_detail_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mContactPhones.size
    }


    inner class ViewHolder(mView: View): RecyclerView.ViewHolder(mView)

}
