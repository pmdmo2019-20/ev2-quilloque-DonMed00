package es.iessaladillo.pedrojoya.quilloque.ui.contact

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.quilloque.base.Event
import es.iessaladillo.pedrojoya.quilloque.data.CallDao
import es.iessaladillo.pedrojoya.quilloque.data.ContactDao
import es.iessaladillo.pedrojoya.quilloque.data.entity.Call
import es.iessaladillo.pedrojoya.quilloque.data.entity.Contact
import java.text.SimpleDateFormat
import java.util.*
import kotlin.concurrent.thread


class ContactViewModel(
    private val contactDao: ContactDao,
    private val callDao: CallDao,
    private val application: Application
) : ViewModel() {

    private val _contacts: MutableLiveData<List<Contact>> = MutableLiveData()
    val contacts: LiveData<List<Contact>>
        get() = _contacts

    private val _message: MutableLiveData<Event<String>> = MutableLiveData()
    val message: LiveData<Event<String>> get() = _message


    init {
        queryAllContacts()

    }
    fun queryAllContacts() {
        _contacts.value = contactDao.queryAllContacts()
    }

    fun queryAllContactsSearch(nameText: String) {
        val search = "%$nameText%"
        _contacts.value = contactDao.queryAllContactsSearch(search)
    }

    fun makeCall(phone: String) {
        val date = Date()
        val formatterDate = SimpleDateFormat("dd/MM/yyyy")
        val formatterTime = SimpleDateFormat("hh:mm:ss")

        val strDate: String = formatterDate.format(date)
        val strTime: String = formatterTime.format(date)
        thread {
            callDao.insertCall(Call(0, phone, "made", strDate, strTime))
            _message.postValue(Event("Llamada realizada"))

        }
    }

    fun makeVideoCall(phone: String) {
        val date = Date()
        val formatterDate = SimpleDateFormat("dd/MM/yyyy")
        val formatterTime = SimpleDateFormat("hh:mm:ss")

        val strDate: String = formatterDate.format(date)
        val strTime: String = formatterTime.format(date)
        thread {
            callDao.insertCall(Call(0, phone, "video", strDate, strTime))
            _message.postValue(Event("Videollamada realizada"))

        }
    }

    fun deleteContact(id: Long) {
        contactDao.deleteContact(contactDao.queryContact(id))
        _message.value = Event("Contacto eliminado correctamente")
        queryAllContacts()

    }


}