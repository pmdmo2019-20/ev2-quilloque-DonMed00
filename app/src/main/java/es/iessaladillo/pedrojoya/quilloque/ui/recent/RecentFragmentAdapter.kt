package es.iessaladillo.pedrojoya.quilloque.ui.recent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.quilloque.R
import es.iessaladillo.pedrojoya.quilloque.data.getCallTypeIcon
import es.iessaladillo.pedrojoya.quilloque.data.pojo.ContactWithCall
import es.iessaladillo.pedrojoya.quilloque.utils.createAvatarDrawable
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.recent_fragment_item.*

class RecentFragmentAdapter :
    ListAdapter<ContactWithCall, RecentFragmentAdapter.ViewHolder>(ContactWithCallDiffCallback) {

    var onItemClickListener: ((Int) -> Unit)? = null

    var currentPosition: Int = -1

    var onButtonAddClickListener: ((Int) -> Unit)? = null
    var onButtonCallClickListener: ((Int) -> Unit)? = null
    var onButtonVideoCallClickListener: ((Int) -> Unit)? = null
    var onButtonDeleteClickListener: ((Int) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.recent_fragment_item, parent, false)
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
            lblCreateContact.setOnClickListener { onButtonAddClickListener?.invoke(adapterPosition) }
            btnCall.setOnClickListener { onButtonCallClickListener?.invoke(adapterPosition) }
            btnVideoCall.setOnClickListener { onButtonVideoCallClickListener?.invoke(adapterPosition) }
            btnDelete.setOnClickListener { onButtonDeleteClickListener?.invoke(adapterPosition) }

        }

        fun bind(contactWithCall: ContactWithCall) {
            contactWithCall.run {
                imgAvatar.setImageDrawable(createAvatarDrawable(contactName?:"?"))
                if(contactName.isNullOrBlank()){
                    lblName.text=phoneNumber
                }else{
                    lblName.text=contactName
                    lblPhoneNumber.text=phoneNumber
                    lblCreateContact.visibility=View.INVISIBLE
                }
                lblDate.text=date
                lblTime.text=time
                imgCallType.setImageResource(getCallTypeIcon(type))
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