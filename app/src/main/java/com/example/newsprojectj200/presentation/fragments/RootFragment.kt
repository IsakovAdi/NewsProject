package com.example.newsprojectj200.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.newsprojectj200.R
import com.example.newsprojectj200.databinding.FragmentRootBinding
import com.example.newsprojectj200.presentation.adapters.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class RootFragment : Fragment() {
    private val binding: FragmentRootBinding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentRootBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentList = arrayListOf(
            AllNewsFragment(), TopNewsFragment()
        )
        val adapter = ViewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle
        )
        binding.viewPager.adapter = adapter
        binding.viewPager.isSaveEnabled = false
        TabLayoutMediator(binding.tableLayout, binding.viewPager){ tab, position->
            when (position){
                0-> tab.setIcon(R.drawable.ic_all)
                1-> tab.setIcon(R.drawable.ic_top)
            }
        }.attach()
        return binding.root
    }
}