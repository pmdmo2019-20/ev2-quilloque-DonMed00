package es.iessaladillo.pedrojoya.quilloque.ui.recent

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.quilloque.base.Event
import es.iessaladillo.pedrojoya.quilloque.data.CallDao
import es.iessaladillo.pedrojoya.quilloque.data.ContactCallDao
import es.iessaladillo.pedrojoya.quilloque.data.entity.Call
import es.iessaladillo.pedrojoya.quilloque.data.pojo.ContactWithCall
import java.text.SimpleDateFormat
import java.util.*
import kotlin.concurrent.thread

class RecentViewModel(
    private val contactCallDao: ContactCallDao, private val callDao: CallDao,
    private val application: Application
) : ViewModel() {

    private val _recents: MutableLiveData<List<ContactWithCall>> = MutableLiveData()
    val recents: LiveData<List<ContactWithCall>>
        get() = _recents

    private val _message: MutableLiveData<Event<String>> = MutableLiveData()
    val message: LiveData<Event<String>> get() = _message

    init {
        queryRecents()
    }

    fun queryRecents() {
        _recents.value = contactCallDao.queryRecentCalls()
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
            _recents.postValue(contactCallDao.queryRecentCalls())
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
            _recents.postValue(contactCallDao.queryRecentCalls())

        }

    }

    fun deleteCall(id: Long) {
        callDao.deleteCall(callDao.queryCall(id))
        _message.value = Event("Contacto eliminado correctamente")
        queryRecents()

    }
}