package es.iessaladillo.pedrojoya.quilloque.data.pojo

data class ContactWithCall(
    val callId: Long,
    val phoneNumber: String,
    val type: String,
    val date: String,
    val time: String,
    val contactId: Long,
    val contactName: String?
)