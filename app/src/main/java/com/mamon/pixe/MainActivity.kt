package com.mamon.pixe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.mamon.pixe.databinding.ActivityMainBinding
import com.mamon.pixe.utils.hide
import com.mamon.pixe.utils.show
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)


        setUpNav()

    }



    private fun setUpNav() {
        val navFragment =
            supportFragmentManager.findFragmentById(R.id.home_nav_host_fragment) as NavHostFragment
        NavigationUI.setupWithNavController(binding.homeBottomNavigation, navFragment.navController)
        navFragment.navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.videoViewer ->{ hideElements() }
                R.id.photoViewer ->{ hideElements() }
                else -> { showElements() }
            }
        }
    }



    // hide navigation bottom and action bar
    private fun hideElements(){
        binding.homeBottomNavigation.hide()
        supportActionBar?.hide()
    }

    // show navigation bottom and action bar
    private fun showElements(){
        binding.homeBottomNavigation.show()
        supportActionBar?.show()
    }





}