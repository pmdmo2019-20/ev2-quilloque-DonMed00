package es.iessaladillo.pedrojoya.quilloque.ui.dial

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.quilloque.R
import es.iessaladillo.pedrojoya.quilloque.data.pojo.ContactWithCall
import es.iessaladillo.pedrojoya.quilloque.utils.createAvatarDrawable
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.dial_fragment_item.*

class DialFragmentAdapter:
    ListAdapter<ContactWithCall, DialFragmentAdapter.ViewHolder>(ContactWithCallDiffCallback) {

    var onItemClickListener: ((Int) -> Unit)? = null

    var currentPosition: Int = -1



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.dial_fragment_item, parent, false)
        return ViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contactWithCall : ContactWithCall = currentList[position]
        holder.bind(contactWithCall)
    }


    inner class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {



        init {
            containerView.setOnClickListener { onItemClickListener?.invoke(adapterPosition) }

        }

        fun bind(contactWithCall: ContactWithCall) {
            contactWithCall.run {
                imgAvatar.setImageDrawable(createAvatarDrawable(contactName?:"?"))
                if(contactName.isNullOrBlank()){
                    lblContactName.text=phoneNumber
                }else{
                    lblContactName.text=contactName
                    lblPhoneNumber.text=phoneNumber
                    lblContactName.visibility=View.INVISIBLE
                }

            }

        }
    }

    object ContactWithCallDiffCallback : DiffUtil.ItemCallback<ContactWithCall>() {
        override fun areItemsTheSame(oldItem: ContactWithCall, newItem: ContactWithCall): Boolean {
            return oldItem.callId == newItem.callId
        }

        override fun areContentsTheSame(oldItem: ContactWithCall, newItem: ContactWithCall): Boolean {
            return oldItem == newItem
        }

    }

}