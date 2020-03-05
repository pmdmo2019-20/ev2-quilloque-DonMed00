package es.iessaladillo.pedrojoya.quilloque.ui.addContact


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import es.iessaladillo.pedrojoya.quilloque.R
import es.iessaladillo.pedrojoya.quilloque.base.observeEvent
import es.iessaladillo.pedrojoya.quilloque.data.CallContactsDatabase
import es.iessaladillo.pedrojoya.quilloque.utils.hideSoftKeyboard
import kotlinx.android.synthetic.main.contact_creation_fragment.*

/**
 * A simple [Fragment] subclass.
 */
class AddContactFragment : Fragment(R.layout.contact_creation_fragment) {


    private val viewmodel: AddContactViewModel by viewModels {
        AddContactViewModelFactory(
            CallContactsDatabase.getInstance
                (this.requireContext()).contactDao, requireActivity().application
        )
    }

    private val phoneNumber: String? by lazy {
        arguments?.getString(getString(R.string.CREATE_ARGS))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
        setupViews()
    }

    private fun setupViews() {
        setupAppBar()
        setupFab()
        observeEvents()
        txtPhoneNumber.setText(phoneNumber?:"")

    }



    private fun observeEvents() {
        viewmodel.message.observeEvent(this) {
            Snackbar.make(txtName, it, Snackbar.LENGTH_SHORT).show()
        }
        viewmodel.onBack.observeEvent(this) {
            if (it) {
                activity!!.onBackPressed()
            }
        }
    }

    private fun setupFab() {
        fabSave.setOnClickListener { saveContact() }

    }

    private fun saveContact() {
        viewmodel.addContact(txtName.text.toString(), txtPhoneNumber.text.toString())
        fabSave.hideSoftKeyboard()
    }


    private fun setupAppBar() {
        (requireActivity() as AppCompatActivity).supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            title = getString(R.string.contact_creation_title)
        }
    }
}
