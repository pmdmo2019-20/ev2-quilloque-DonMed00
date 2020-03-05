package es.iessaladillo.pedrojoya.quilloque.ui.dial

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.iessaladillo.pedrojoya.quilloque.data.CallDao
import es.iessaladillo.pedrojoya.quilloque.data.ContactCallDao
import es.iessaladillo.pedrojoya.quilloque.data.ContactDao

@Suppress("UNCHECKED_CAST")
class DialViewModelFactory(
    private val contactDao: ContactDao,
    private val callDao: CallDao,
    private  val contactCallDao: ContactCallDao,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        DialViewModel(contactDao,callDao,contactCallDao, application) as T
}