package es.iessaladillo.pedrojoya.quilloque.ui.dial


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.quilloque.R
import es.iessaladillo.pedrojoya.quilloque.data.CallContactsDatabase
import es.iessaladillo.pedrojoya.quilloque.utils.invisibleUnless
import kotlinx.android.synthetic.main.dial_fragment.*


/**
 * A simple [Fragment] subclass.
 */
class DialFragment : Fragment(R.layout.dial_fragment) {


    private lateinit var dialAdapter: DialFragmentAdapter


    private val viewmodel: DialViewModel by viewModels {
        DialViewModelFactory(
            CallContactsDatabase.getInstance
                (this.requireContext()).contactDao,
            CallContactsDatabase.getInstance
                (this.requireContext()).callDao, CallContactsDatabase.getInstance
                (this.requireContext()).contactCallDao, requireActivity().application
        )
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        setupAppBar()
        setupAdapter()
        setupRecyclerView()
        setupLbl()
        setupBtnDelete()
        observeLiveData()
        lblCreateContact.setOnClickListener {
            findNavController().navigate(
                R.id.navToCreateContact,
                bundleOf(getString(R.string.CREATE_ARGS) to lblNumber.text.toString())
            )
        }
        setupSearch()
    }

    private fun setupSearch() {
        lblNumber.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                search(lblNumber.text.toString())

            }

        })
    }
    private fun search(text: String) {
        if (text.isNotEmpty()) {
        }
    }

    private fun observeLiveData() {
        viewmodel.currentNumber.observe(this) {

            imgVideo.invisibleUnless(it.isNotEmpty())
            imgBackspace.invisibleUnless(it.isNotEmpty())
        }
    }

    private fun setupBtnDelete() {
        imgBackspace.setOnClickListener { deleteEdit() }
        imgBackspace.setOnLongClickListener { deleteAll() }

    }

    private fun deleteAll(): Boolean {
        lblNumber.text = ""
        return false
    }

    private fun deleteEdit() {
        var str: String = lblNumber.text.toString().trim()
        if (str.isNotEmpty()) {
            str = str.substring(0, str.length - 1)
            lblNumber.text = str
        }
    }


    private fun setupLbl() {
        lblOne.setOnClickListener { writeLbl(lblOne.text.toString()) }
        lblTwo.setOnClickListener { writeLbl(lblTwo.text.toString()) }
        lblThree.setOnClickListener { writeLbl(lblThree.text.toString()) }
        lblFour.setOnClickListener { writeLbl(lblFour.text.toString()) }
        lblFive.setOnClickListener { writeLbl(lblFive.text.toString()) }
        lblSix.setOnClickListener { writeLbl(lblSix.text.toString()) }
        lblSeven.setOnClickListener { writeLbl(lblSeven.text.toString()) }
        lblEight.setOnClickListener { writeLbl(lblEight.text.toString()) }
        lblNine.setOnClickListener { writeLbl(lblNine.text.toString()) }
        lblAstherisc.setOnClickListener { writeLbl(lblAstherisc.text.toString()) }
        lblZero.setOnClickListener { writeLbl(lblZero.text.toString()) }
        lblPound.setOnClickListener { writeLbl(lblPound.text.toString()) }
    }

    private fun writeLbl(text: String) {
        lblNumber.text = lblNumber.text.toString() + text
        viewmodel.setCurrentNumber(lblNumber.text.toString())
    }


    private fun setupAppBar() {
        (requireActivity() as AppCompatActivity).supportActionBar?.run {
            setDisplayHomeAsUpEnabled(false)
            title = getString(R.string.dial_title)
        }
    }

    private fun setupRecyclerView() {
        lstSuggestions.run {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            adapter = dialAdapter

        }
    }

    private fun setupAdapter() {
        dialAdapter = DialFragmentAdapter().also { it ->
            it.onItemClickListener = {
                dialAdapter.currentPosition = it
                dialAdapter.notifyDataSetChanged()
            }

        }
    }

}
