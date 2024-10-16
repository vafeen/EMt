package com.example.emtask.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.emtask.presentation.R
import com.example.emtask.presentation.databinding.ActivityMainBinding
import com.example.emtask.presentation.ui.ui_utils.setNavigationBarColor
import com.example.emtask.presentation.ui.ui_utils.setStatusBarColor
import com.example.emtask.presentation.ui.viewmodels.MainActivityViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val vm: MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch(vm.appCoroutineDispatchers.io) {
            vm.loadData()
        }
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavView.setupWithNavController(navController)

        setStatusBarColor(window, this, R.color.black, R.color.black)
        setNavigationBarColor(window, this, R.color.black, R.color.black)

        lifecycleScope.launch(vm.appCoroutineDispatchers.main) {
            vm.countOfFavourites.collect {
                updateBadgeIcon(it)
            }
        }
    }

    private fun updateBadgeIcon(count: Int) {
        val bottomNavigationView = binding.bottomNavView
        val menuItemId = R.id.navigation_favourites

        if (count > 0) {
            bottomNavigationView.getOrCreateBadge(menuItemId).apply {
                isVisible = true
                number = count
            }
        } else {
            bottomNavigationView.removeBadge(menuItemId)
        }
    }

}