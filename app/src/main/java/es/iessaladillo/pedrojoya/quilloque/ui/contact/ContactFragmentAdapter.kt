package es.iessaladillo.pedrojoya.quilloque.ui.contact

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.quilloque.R
import es.iessaladillo.pedrojoya.quilloque.data.entity.Contact
import es.iessaladillo.pedrojoya.quilloque.utils.createAvatarDrawable
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.contacts_fragment_item.*


class ContactFragmentAdapter :
    ListAdapter<Contact, ContactFragmentAdapter.ViewHolder>(ContactDiffCallback) {

    var onItemClickListener: ((Int) -> Unit)? = null
    var onButtonCallClickListener: ((Int) -> Unit)? = null
    var onButtonVideoCallClickListener: ((Int) -> Unit)? = null
    var onButtonDeleteClickListener: ((Int) -> Unit)? = null




    var currentPosition: Int = -1


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.contacts_fragment_item, parent, false)
        return ViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contact: Contact = currentList[position]
        holder.bind(contact)
    }


    inner class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {

        init {
            containerView.setOnClickListener { onItemClickListener?.invoke(adapterPosition) }
            btnCall.setOnClickListener { onButtonCallClickListener?.invoke(adapterPosition) }
            btnVideoCall.setOnClickListener { onButtonVideoCallClickListener?.invoke(adapterPosition) }
            btnDelete.setOnClickListener { onButtonDeleteClickListener?.invoke(adapterPosition) }



        }

        fun bind(contact: Contact) {
            contact.run {
                imgAvatar.setImageDrawable(createAvatarDrawable(name))
                lblName.text = name
                lblPhoneNumber.text = phoneNumber
            }
        }
    }

    object ContactDiffCallback : DiffUtil.ItemCallback<Contact>() {
        override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem == newItem
        }

    }

}