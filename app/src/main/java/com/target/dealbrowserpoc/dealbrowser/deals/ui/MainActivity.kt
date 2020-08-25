package com.target.dealbrowserpoc.dealbrowser.deals.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.target.dealbrowserpoc.dealbrowser.R
import com.target.dealbrowserpoc.dealbrowser.deals.ui.viewModel.Extra_Id
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        NavigationUI.setupActionBarWithNavController(
                this, Navigation.findNavController(fragment_navHost.requireView()))
    }

    override fun onSupportNavigateUp(): Boolean {
        return Navigation.findNavController(fragment_navHost.requireView())
                .navigateUp() || super.onSupportNavigateUp();
    }

}