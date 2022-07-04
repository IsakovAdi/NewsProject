package com.example.newsprojectj200.presentation.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.newsprojectj200.presentation.adapters.ParamsAdapter
import com.example.newsprojectj200.R
import com.example.newsprojectj200.data.RepositoryImpl
import com.example.newsprojectj200.databinding.FragmentTopNewsBinding
import com.example.newsprojectj200.presentation.activities.DetailsActivity
import com.example.newsprojectj200.presentation.adapters.NewsAdapter


class TopNewsFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    private val binding: FragmentTopNewsBinding by lazy {
        FragmentTopNewsBinding.inflate(layoutInflater)
    }
    private val viewModel: TopNewsFragmentVM by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[TopNewsFragmentVM::class.java]
    }
    private val newsAdapter: NewsAdapter by lazy {
        NewsAdapter()
    }
    private val countryAdapter: ParamsAdapter by lazy {
        ParamsAdapter()
    }
    private val categoryAdapter: ParamsAdapter by lazy {
        ParamsAdapter()
    }

    private var country = RepositoryImpl.ISO_3166_1.RUSSIA
    private var category = RepositoryImpl.categories.BUSINESS

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupCountryRV()
        setupCategoryRV()
        setupMainRv()
        binding.mainSwipeRefreshTopFragment.setOnRefreshListener(this@TopNewsFragment)
        countryAdapter.paramsList = viewModel.getCountry().toMutableList()
        categoryAdapter.paramsList = viewModel.getCategory().toMutableList()

        viewModel.getTopNews("", country, category )
        viewModel.allTopNews.observe(viewLifecycleOwner) {
            newsAdapter.submitList(it)
        }
    }

    private fun setupCategoryRV() {
        with(binding.categoryRV) {
            hasFixedSize()
            adapter = categoryAdapter
            recycledViewPool.setMaxRecycledViews(
                ParamsAdapter.VIEW_TYPE_ENABLED,
                ParamsAdapter.MAX_POOL_SIZE_ENABLED
            )
            recycledViewPool.setMaxRecycledViews(
                ParamsAdapter.VIEW_TYPE_DISABLED,
                ParamsAdapter.MAX_POOL_SIZE_DISABLED
            )
        }
        setupCategoryRvClickListener()
    }

    private fun setupCategoryRvClickListener() {
        categoryAdapter.onParamClickListener = {
            categoryAdapter.changeEnableState(it)
            category = it.param
            viewModel.getTopNews("", country, category)
        }
    }

    private fun setupCountryRV() {
        with(binding.countryRV) {
            hasFixedSize()
            adapter = countryAdapter
            recycledViewPool.setMaxRecycledViews(
                ParamsAdapter.VIEW_TYPE_ENABLED,
                ParamsAdapter.MAX_POOL_SIZE_ENABLED
            )
            recycledViewPool.setMaxRecycledViews(
                ParamsAdapter.VIEW_TYPE_DISABLED,
                ParamsAdapter.MAX_POOL_SIZE_DISABLED
            )
        }
        setupCountryRvClickListener()
    }

    private fun setupCountryRvClickListener() {
        countryAdapter.onParamClickListener = {
            countryAdapter.changeEnableState(it)
            country = it.param
            viewModel.getTopNews("", country, category)
        }
    }

    private fun setupMainRv() {
        with(binding.mainRvTopFrag) {
            hasFixedSize()
            adapter = newsAdapter
            recycledViewPool.setMaxRecycledViews(
                NewsAdapter.VIEW_TYPE,
                NewsAdapter.MAX_POOL_SIZE
            )
        }
        setupMainRvClickListener()
    }

    private fun setupMainRvClickListener() {
        newsAdapter.onNewsItemClickListener = {
            if (it.url!=null){
                val intent = DetailsActivity.newIntentEditItem(requireContext(), it.url)
                startActivity(intent)
            }
            else Toast.makeText(requireActivity().baseContext, "NOT", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.main_menu, menu)
        val searchView = menu.findItem(R.id.search)?.actionView as SearchView
        searchView.queryHint = "Search news"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(keyword: String): Boolean {
                viewModel.getTopNews(keyword, country, category)
                return false
            }

            override fun onQueryTextChange(keyword: String): Boolean {
                viewModel.getTopNews(keyword, country, category)
                return false
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onRefresh() {
        viewModel.getTopNews("", country, category)
        binding.mainSwipeRefreshTopFragment.isRefreshing = false
    }

}