package es.iessaladillo.pedrojoya.quilloque.ui.dial


import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import es.iessaladillo.pedrojoya.quilloque.R
import es.iessaladillo.pedrojoya.quilloque.utils.invisibleUnless
import kotlinx.android.synthetic.main.dial_fragment.*


/**
 * A simple [Fragment] subclass.
 */
class DialFragment : Fragment(R.layout.dial_fragment) {


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        setupAppBar()
        setupLbl()
        setupBtnDelete()
    }

    private fun setupBtnDelete() {
        imgBackspace.setOnClickListener { deleteEdit() }

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
        if(lblNumber.text.isEmpty()){
            imgVideo.visibility=View.INVISIBLE
            imgBackspace.visibility=View.INVISIBLE
        }else{
            imgVideo.visibility=View.VISIBLE
            imgBackspace.visibility=View.VISIBLE
        }


    }

    private fun writeLbl(text: String) {
        lblNumber.text=lblNumber.text.toString()+text
    }


    private fun setupAppBar() {
        (requireActivity() as AppCompatActivity).supportActionBar?.run {
            setDisplayHomeAsUpEnabled(false)
            title = getString(R.string.dial_title)
        }
    }

}
