package com.sumeyyeemre.ecommerceapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sumeyyeemre.ecommerceapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var activityMainBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        activityMainBinding=DataBindingUtil.setContentView(this,R.layout.activity_main)

        val navHostFragment=supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController=navHostFragment.navController  //navController ile item lerin id si ile eşit olan fragmentleri getiriyor
        val bottomNavigation: BottomNavigationView =findViewById(R.id.bottomNavigationView)
        bottomNavigation.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->  //destination ile login ve register sayfalarından bottomview e ulaştık visible ile gizleme işlemini yaptık
            if(destination.id == R.id.loginFragment || destination.id==R.id.registerFragment || destination.id==R.id.cardDetailFragment || destination.id==R.id.forgotPasswordFragment) {

                bottomNavigation.visibility = View.GONE
            } else {

                bottomNavigation.visibility = View.VISIBLE
            }
        }

    }
}