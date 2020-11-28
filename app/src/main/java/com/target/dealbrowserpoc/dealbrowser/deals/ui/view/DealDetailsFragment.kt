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
import androidx.navigation.NavDirections
import androidx.transition.TransitionInflater
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.target.dealbrowserpoc.dealbrowser.R
import com.target.dealbrowserpoc.dealbrowser.databinding.FragmentDetailsBinding
import com.target.dealbrowserpoc.dealbrowser.deals.ui.viewModel.DealDetailsViewModel
import com.target.dealbrowserpoc.dealbrowser.deals.ui.viewModel.Extra_Id
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.fragment_details.view.*
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class DealDetailsFragment : BaseFragment() {

    val viewModel: DealDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerViewModel(viewModel)
        viewModel.image.observe(this, Observer {
            Picasso.with(activity).load(it).placeholder(R.drawable.image_14741397)
                    .fit().into(dealDetailIV, object : Callback {
                        override fun onSuccess() {
                            startPostponedEnterTransition()
                        }
                        override fun onError() {
                            startPostponedEnterTransition()
                        }
                    })
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(view){
            transitionName = arguments?.getString(Extra_Id)
        }
        setSharedElementTransitionOnEnter()
        postponeEnterTransition()
    }

    private fun setSharedElementTransitionOnEnter() {
        sharedElementEnterTransition = TransitionInflater.from(context)
                .inflateTransition(R.transition.shared_element_transition)
    }

    companion object{
        fun detailsNavDirections(id: String) : NavDirections{
            return DetailNavDirections(id)
        }
    }

    class DetailNavDirections(val id: String) : NavDirections {
        override fun getActionId(): Int {
            return R.id.action_dealListFragment_to_detailsFragment
        }

        override fun getArguments(): Bundle {
            val args = Bundle()
            args.putString(Extra_Id, id)
            return args
        }

    }

}