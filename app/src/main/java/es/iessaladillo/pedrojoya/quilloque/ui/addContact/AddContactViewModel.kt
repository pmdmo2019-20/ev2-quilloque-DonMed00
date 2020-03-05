package es.iessaladillo.pedrojoya.quilloque.ui.addContact

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.quilloque.data.ContactDao
import es.iessaladillo.pedrojoya.quilloque.data.entity.Contact
import es.iessaladillo.pedrojoya.quilloque.base.Event
import kotlin.concurrent.thread


class AddContactViewModel(
    private val contactDao: ContactDao,
    private val application: Application
) : ViewModel() {

    private val _onBack: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val onBack: LiveData<Event<Boolean>> get() = _onBack

    private val _message: MutableLiveData<Event<String>> = MutableLiveData()
    val message: LiveData<Event<String>> get() = _message


    fun addContact(name: String, phoneNumber: String) {
        if (name.isEmpty() || phoneNumber.isEmpty()) {
            _message.value = Event("Error, los campos no pueden estar vacíos")
        } else {
            thread {
                try {
                    contactDao.insertContact(Contact(0, name, phoneNumber))
                    _onBack.postValue(Event(true))

                } catch (e: Exception) {
                    _message.postValue(Event("Error, ya existe un contacto con ese Nombre y/o Número"))
                }

            }
        }
    }


}