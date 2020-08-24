package com.target.dealbrowserpoc.dealbrowser.ui

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.target.dealbrowserpoc.dealbrowser.ui.view.DealDetailsFragment
import com.target.dealbrowserpoc.dealbrowser.R
import com.target.dealbrowserpoc.dealbrowser.ui.view.DealListFragment.OnFragmentInteractionListener
import com.target.dealbrowserpoc.dealbrowser.ui.viewModel.Extra_Id
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnFragmentInteractionListener {
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

    override fun onFragmentInteraction(id: String) {
        val args = Bundle()
        args.putString(Extra_Id, id)
        Navigation.findNavController(fragment_navHost.requireView())
                .navigate(R.id.action_dealListFragment_to_detailsFragment, args);
//        val alertDialog = AlertDialog.Builder(this).create()
//        alertDialog.setTitle("Product Id")
//        alertDialog.setMessage(id)
//        alertDialog.setCancelable(true)
//        alertDialog.show()
    }
}