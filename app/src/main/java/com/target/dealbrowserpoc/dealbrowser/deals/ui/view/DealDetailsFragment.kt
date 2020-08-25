package com.target.dealbrowserpoc.dealbrowser.deals.ui.view

import android.graphics.Paint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.squareup.picasso.Picasso
import com.target.dealbrowserpoc.dealbrowser.R
import com.target.dealbrowserpoc.dealbrowser.databinding.FragmentDetailsBinding
import com.target.dealbrowserpoc.dealbrowser.deals.ui.viewModel.DealDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.fragment_details.view.*

@AndroidEntryPoint
class DealDetailsFragment : BaseFragment() {

    val viewModel: DealDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerViewModel(viewModel)
        viewModel.image.observe(this, Observer {
            Picasso.with(activity).load(it).placeholder(R.drawable.image_14741397)
                    .fit().into(dealDetailIV)
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val binding: FragmentDetailsBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_details, container, false)
        val view = binding.root
        view.regPrice.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        view.addToListBtn.setOnClickListener {
            Toast.makeText(context, "Add to List clicked!!", Toast.LENGTH_SHORT).show()
        }
        view.addToCartBtn.setOnClickListener {
            Toast.makeText(context, "Add to Cart clicked!!", Toast.LENGTH_SHORT).show()
        }
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

}