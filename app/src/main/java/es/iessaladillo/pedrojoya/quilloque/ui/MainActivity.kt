package es.iessaladillo.pedrojoya.quilloque.ui

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import es.iessaladillo.pedrojoya.quilloque.R
import es.iessaladillo.pedrojoya.quilloque.data.CallContactsDatabase
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : AppCompatActivity() {
    private val navController: NavController by lazy {
        findNavController(R.id.navHostFragment)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        CallContactsDatabase.getInstance(this).contactDao.queryAllContacts()
        NavigationUI.setupWithNavController(bottomNavigationView,navController)
        setupAppBar()


    }

    private fun setupAppBar() {
        setSupportActionBar(toolbar)

    }
//preguntar
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }



}
