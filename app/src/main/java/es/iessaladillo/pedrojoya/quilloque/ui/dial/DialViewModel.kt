package es.iessaladillo.pedrojoya.quilloque.ui.dial

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import es.iessaladillo.pedrojoya.quilloque.data.CallDao
import es.iessaladillo.pedrojoya.quilloque.data.ContactCallDao
import es.iessaladillo.pedrojoya.quilloque.data.ContactDao
import es.iessaladillo.pedrojoya.quilloque.data.pojo.ContactWithCall

class DialViewModel (
    private val contactDao: ContactDao,
    private val callDao: CallDao,
    private  val contactCallDao: ContactCallDao,
    private val application: Application
) : ViewModel() {

    private val _currentNumber: MutableLiveData<String> = MutableLiveData("")
    val currentNumber: LiveData<String>
        get() = _currentNumber


    fun setCurrentNumber(text: String){
        _currentNumber.value=text
    }

    private val _suggesteds: MutableLiveData<List<ContactWithCall>> = MutableLiveData()
    val suggesteds: LiveData<List<ContactWithCall>>
        get() = _suggesteds

    fun querySuggestedCalls(){
    }
}