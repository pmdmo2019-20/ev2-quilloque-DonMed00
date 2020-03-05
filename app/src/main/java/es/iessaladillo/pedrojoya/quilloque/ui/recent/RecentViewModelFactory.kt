package es.iessaladillo.pedrojoya.quilloque.ui.recent

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import es.iessaladillo.pedrojoya.quilloque.data.CallDao
import es.iessaladillo.pedrojoya.quilloque.data.ContactCallDao

class RecentViewModelFactory(
    private val contactCallDao: ContactCallDao,
    private val callDao: CallDao,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        RecentViewModel(contactCallDao,callDao, application) as T


}