package br.com.denisferreira.whitelabelapp.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import br.com.denisferreira.whitelabelapp.R
import br.com.denisferreira.whitelabelapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


// Ponto de entrada para gráfico de dependências
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()


    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration
    private var cartCounter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph)

        binding.toolbarMain.setupWithNavController(navController, appBarConfiguration)
        setSupportActionBar(binding.toolbarMain)

        viewModel.shoppingCart.observe(this, { cart ->
            cartCounter = cart.products.size
            invalidateOptionsMenu()
        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.nav_host_fragment)
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if (menu != null) {
            val cartTextView = menu.getItem(0).actionView
            val text = cartTextView.findViewById<TextView>(R.id.cart_text)
            text.text = cartCounter.toString()
        }
        return super.onPrepareOptionsMenu(menu)
    }
}