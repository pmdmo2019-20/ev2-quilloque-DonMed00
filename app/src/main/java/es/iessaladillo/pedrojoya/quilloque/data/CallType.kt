package es.iessaladillo.pedrojoya.quilloque.data

import androidx.annotation.DrawableRes
import es.iessaladillo.pedrojoya.quilloque.R

const val CALL_TYPE_MADE = "made"
const val CALL_TYPE_RECEIVED = "received"
const val CALL_TYPE_MISSED = "missed"
const val CALL_TYPE_VIDEO = "video"

@DrawableRes
fun getCallTypeIcon(callType: String): Int =
    when (callType) {
        CALL_TYPE_MADE -> R.drawable.ic_call_made_black_24dp
        CALL_TYPE_RECEIVED -> R.drawable.ic_call_received_black_24dp
        CALL_TYPE_MISSED -> R.drawable.ic_call_missed_black_24dp
        CALL_TYPE_VIDEO -> R.drawable.ic_video_call_black_24dp
        else -> R.drawable.ic_call_black_24dp
    }
