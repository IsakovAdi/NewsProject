package com.example.newsproject.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.newsproject.R
import com.example.newsproject.databinding.FragmentRootBinding
import com.example.newsproject.presentation.ui.adapters.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator


class RootFragment : Fragment() {
    private val binding: FragmentRootBinding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentRootBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val fragmentList = arrayListOf(
            AllNewsFragment(), TopNewsFragment(), SavedNewsFragment()
        )
        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )
        binding.viewPager.adapter = adapter
        binding.viewPager.isSaveEnabled = false
        TabLayoutMediator(binding.tableLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.setIcon(R.drawable.ic_all)
                1 -> tab.setIcon(R.drawable.ic_top)
                2 -> tab.setIcon(R.drawable.ic_storage)
            }
        }.attach()
        return binding.root
    }
}