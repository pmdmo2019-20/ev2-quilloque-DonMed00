package es.iessaladillo.pedrojoya.quilloque.ui.contact

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.iessaladillo.pedrojoya.quilloque.data.CallDao
import es.iessaladillo.pedrojoya.quilloque.data.ContactDao

class ContactViewModelFactory(
    private val contactDao: ContactDao,
    private val callDao: CallDao,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        ContactViewModel(contactDao,callDao, application) as T


}