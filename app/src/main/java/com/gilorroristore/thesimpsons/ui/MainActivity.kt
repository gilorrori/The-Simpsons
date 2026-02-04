package com.gilorroristore.thesimpsons.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.gilorroristore.thesimpsons.R
import com.gilorroristore.thesimpsons.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUI()
    }

    private fun initUI() {
        initNavigation()
    }

    private fun initNavigation() {
        val navHost = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController: NavController = navHost.navController
        binding.bottomNavigationView.setupWithNavController(navController)
        observeDestinationChange(navController)
    }

    private fun showBackButton(showBack: Boolean) {
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(showBack)
        }
    }

    private fun observeDestinationChange(navController: NavController) {
        navController.addOnDestinationChangedListener { _: NavController?, destination: NavDestination, _: Bundle? ->
            when (destination.id) {
                R.id.detailCharacterFragment -> {
                    showBackButton(true)
                    binding.bottomNavigationView.isVisible = false
                }

                else -> {
                    binding.bottomNavigationView.isVisible = true
                    showBackButton(false)
                }
            }
        }
    }
}