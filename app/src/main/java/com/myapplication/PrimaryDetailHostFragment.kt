package com.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.navArgs
import androidx.slidingpanelayout.widget.SlidingPaneLayout
import com.myapplication.databinding.FragmentPrimaryDetailHostBinding


class PrimaryDetailHostFragment : Fragment() {

    private val args by navArgs<PrimaryDetailHostFragmentArgs>()
    private val primaryViewModel: PrimaryViewModel by viewModels()

    private var _binding: FragmentPrimaryDetailHostBinding? = null
    private val binding get() = _binding!!

    private lateinit var callback: TwoPaneOnBackPressedCallback

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPrimaryDetailHostBinding.inflate(inflater, container, false)

        binding.slidingPaneLayout.lockMode = SlidingPaneLayout.LOCK_MODE_LOCKED

        callback = TwoPaneOnBackPressedCallback(binding.slidingPaneLayout)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

        //https://developer.android.com/guide/navigation/navigation-pass-data#start
        //Primary and detail nav graphs require data passed to them to initialize them with their tab number.
        val primaryNavHostFragment =
            childFragmentManager.findFragmentById(R.id.primary_nav_host_fragment) as NavHostFragment
        var navController = primaryNavHostFragment.navController
        navController.setGraph(
            R.navigation.primary_nav_graph, bundleOf(
                TAB_NUMBER to args.tabNumber
            )
        )

        val detailNavHostFragment =
            childFragmentManager.findFragmentById(R.id.detail_nav_host_fragment) as NavHostFragment
        navController = detailNavHostFragment.navController
        navController.setGraph(
            R.navigation.details_nav_graph, bundleOf(
                TAB_NUMBER to args.tabNumber,
                PRIMARY_SELECTION to "Initialized with tab number ${args.tabNumber}"
            )
        )

        primaryViewModel.selection.observe(viewLifecycleOwner) {
            val directions =
                DetailNavGraphDirections.openDetail(args.tabNumber, "Selection observed: $it")
            detailNavHostFragment.navController.navigate(directions)
            binding.root.open()
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        callback.onTabResumed()
    }

    override fun onPause() {
        super.onPause()
        callback.onTabPaused()
    }

    companion object {
        const val TAB_NUMBER = "tab_number"
        const val PRIMARY_SELECTION = "primary_selection"

        /**
         * Returns a NavHostFragment with a PrimaryDetailHost SlidingPaneLayout, 1 per tab
         */
        fun newInstance(tabNumber: Int): NavHostFragment {
            return NavHostFragment.create(
                R.navigation.primary_details_host_nav_graph, bundleOf(
                    TAB_NUMBER to tabNumber
                )
            )
        }
    }
}
