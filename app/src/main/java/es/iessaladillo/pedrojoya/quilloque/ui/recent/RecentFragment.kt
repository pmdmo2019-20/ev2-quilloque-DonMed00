package es.iessaladillo.pedrojoya.quilloque.ui.recent


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import es.iessaladillo.pedrojoya.quilloque.R
import es.iessaladillo.pedrojoya.quilloque.base.observeEvent
import es.iessaladillo.pedrojoya.quilloque.data.CallContactsDatabase
import es.iessaladillo.pedrojoya.quilloque.data.pojo.ContactWithCall
import es.iessaladillo.pedrojoya.quilloque.utils.invisibleUnless
import kotlinx.android.synthetic.main.recent_fragment.*

/**
 * A simple [Fragment] subclass.
 */
class RecentFragment : Fragment(R.layout.recent_fragment) {

    private lateinit var recentAdapter: RecentFragmentAdapter


    private val viewmodel: RecentViewModel by viewModels {
        RecentViewModelFactory(
            CallContactsDatabase.getInstance
                (this.requireContext()).contactCallDao,CallContactsDatabase.getInstance
                (this.requireContext()).callDao, requireActivity().application
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        viewmodel.queryRecents()//Para cuando vuelva de addContact, se actualize
        setupAppBar()
        setupAdapter()
        setupRecyclerView()
        observeLiveData()
    }


    private fun setupAppBar() {
        (requireActivity() as AppCompatActivity).supportActionBar?.run {
            setDisplayHomeAsUpEnabled(false)
            title = getString(R.string.recent_title)
        }
    }

    private fun setupRecyclerView() {
        lstCalls.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            adapter = recentAdapter

        }
    }

    private fun setupAdapter() {
        recentAdapter = RecentFragmentAdapter().also {
            it.onButtonCallClickListener = { makeCall(it) }
            it.onButtonVideoCallClickListener = { makeVideoCall(it) }
            it.onButtonDeleteClickListener = { deleteCall(it) }
            it.onButtonAddClickListener={addContact(it)}

            it.onItemClickListener = { it ->
                recentAdapter.currentPosition = it
                recentAdapter.notifyDataSetChanged()
            }

        }
    }



    private fun deleteCall(it: Int) {
        viewmodel.deleteCall(recentAdapter.currentList[it].callId)
    }

    private fun makeVideoCall(it: Int) {
        viewmodel.makeVideoCall(recentAdapter.currentList[it].phoneNumber)

    }

    private fun makeCall(it: Int) {
        viewmodel.makeCall(recentAdapter.currentList[it].phoneNumber)
    }

    private fun addContact(it: Int) {
        var item = recentAdapter.currentList[it]
        findNavController().navigate(
            R.id.navigateToCreateContact,
            bundleOf(getString(R.string.CREATE_ARGS) to item.phoneNumber)
        )
    }

    private fun observeLiveData() {
        viewmodel.recents.observe(this) {
            showRecents(it)
        }
        viewmodel.message.observeEvent(this){
            Snackbar.make(lstCalls,it,Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun showRecents(contactWithCall: List<ContactWithCall>) {
        lstCalls.post {
            recentAdapter.submitList(contactWithCall)
            emptyView.invisibleUnless(contactWithCall.isEmpty())
        }

    }
}
