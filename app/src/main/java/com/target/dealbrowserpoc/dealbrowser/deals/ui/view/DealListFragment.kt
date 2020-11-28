package com.target.dealbrowserpoc.dealbrowser.deals.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.target.dealbrowserpoc.dealbrowser.DealListItemAdapter
import com.target.dealbrowserpoc.dealbrowser.R
import com.target.dealbrowserpoc.dealbrowser.databinding.FragmentDealListBinding
import com.target.dealbrowserpoc.dealbrowser.service.model.DealItem
import com.target.dealbrowserpoc.dealbrowser.deals.ui.viewModel.DealListViewModel
import com.target.dealbrowserpoc.dealbrowser.deals.ui.viewModel.Extra_Id
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_deal_list.*

@AndroidEntryPoint
class DealListFragment : BaseFragment() {
    val viewModel: DealListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerForErrorDialog(viewModel)
        viewModel.deals.observe(this, Observer {
            var listAdapter = DealListItemAdapter(requireContext(), it, object : DealListItemAdapter.OnItemClickListener {
                override fun onItemClicked(item: DealItem, view: View) {
                    val args = Bundle()
                    args.putString(Extra_Id, item.id)

                    val extraInfoForSharedElement = FragmentNavigatorExtras(
                            view to item.id
                    )
//                    navController.navigate(
//                            R.id.action_dealListFragment_to_detailsFragment, args, null,
//                            extraInfoForSharedElement)
                    navigate(DealDetailsFragment.detailsNavDirections(item.id), extraInfoForSharedElement)
                }
            })
            dealsRV.layoutManager = LinearLayoutManager(context)
            dealsRV.adapter = listAdapter
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding: FragmentDealListBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_deal_list, container, false)
        viewModel.getDeals()
        return binding.root
    }

    private fun navigate(destination: NavDirections, extraInfo: FragmentNavigator.Extras) = with(findNavController()) {
        // 1
        currentDestination?.getAction(destination.actionId)
                ?.let {
                    navigate(destination, extraInfo) //2 }
                }
    }

}