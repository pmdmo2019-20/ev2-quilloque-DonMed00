package es.iessaladillo.pedrojoya.quilloque.ui.contact


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import es.iessaladillo.pedrojoya.quilloque.R
import es.iessaladillo.pedrojoya.quilloque.base.observeEvent
import es.iessaladillo.pedrojoya.quilloque.data.CallContactsDatabase
import es.iessaladillo.pedrojoya.quilloque.data.entity.Contact
import es.iessaladillo.pedrojoya.quilloque.utils.invisibleUnless
import kotlinx.android.synthetic.main.contacts_fragment.*


/**
 * A simple [Fragment] subclass.
 */
class ContactFragment : Fragment(R.layout.contacts_fragment) {

    private lateinit var contactAdapter: ContactFragmentAdapter


    private val viewmodel: ContactViewModel by viewModels {
        ContactViewModelFactory(
            CallContactsDatabase.getInstance
                (this.requireContext()).contactDao,
            CallContactsDatabase.getInstance
                (this.requireContext()).callDao, requireActivity().application
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
        setupViews()
    }

    private fun setupViews() {
        viewmodel.queryAllContacts()
        setupAppBar()
        setupAdapter()
        setupRecyclerView()
        observeLiveData()
        emptyView.setOnClickListener { findNavController().navigate(R.id.navigateToContactCreation) }

    }



    private fun setupSearch() {
        txtSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                search(txtSearch.text.toString())
            }

        })
    }

    private fun search(text: String) {
        if (text.isNotEmpty()) {
            viewmodel.queryAllContactsSearch(text)
        } else {
                viewmodel.queryAllContacts()
        }
    }


    private fun setupRecyclerView() {
        lstContacts.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            adapter = contactAdapter

        }
    }

    private fun setupAdapter() {
        contactAdapter = ContactFragmentAdapter().also {
            it.onButtonCallClickListener = { makeCall(it) }
            it.onButtonVideoCallClickListener = { makeVideoCall(it) }
            it.onButtonDeleteClickListener = { deleteContact(it) }

            it.onItemClickListener = { it ->
                contactAdapter.currentPosition = it
                contactAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun deleteContact(it: Int) {
        viewmodel.deleteContact(contactAdapter.currentList[it].id)
    }

    private fun makeVideoCall(it: Int) {
        viewmodel.makeVideoCall(contactAdapter.currentList[it].phoneNumber)

    }

    private fun makeCall(it: Int) {
        viewmodel.makeCall(contactAdapter.currentList[it].phoneNumber)
    }

    private fun observeLiveData() {
        viewmodel.contacts.observe(this) {
            showContacts(it)
        }
        viewmodel.message.observeEvent(this){
            Snackbar.make(txtSearch,it,Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun showContacts(contacts: List<Contact>) {
        lstContacts.post {
            contactAdapter.submitList(contacts)
            emptyView.invisibleUnless(contacts.isEmpty())
        }

    }


    private fun setupAppBar() {
        (requireActivity() as AppCompatActivity).supportActionBar?.run {
            setDisplayHomeAsUpEnabled(false)
            title = getString(R.string.contacts_title)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.contact_menu_add, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.addContact -> findNavController().navigate(R.id.navigateToContactCreation)
        }
        return super.onOptionsItemSelected(item)
    }

}
