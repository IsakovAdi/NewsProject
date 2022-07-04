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
import com.example.newsprojectj200.presentation.adapters.NewsAdapter
import com.example.newsprojectj200.R
import com.example.newsprojectj200.data.RepositoryImpl
import com.example.newsprojectj200.databinding.FragmentAllNewsBinding
import com.example.newsprojectj200.presentation.activities.DetailsActivity


class AllNewsFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    private val binding: FragmentAllNewsBinding by lazy {
        FragmentAllNewsBinding.inflate(layoutInflater)
    }

    private val viewModel: AllNewsFragmentViewModel by lazy {
        ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)
        )[AllNewsFragmentViewModel::class.java]
    }

    private val newsAdapter: NewsAdapter by lazy {
        NewsAdapter()
    }

    private val lanAdapter: ParamsAdapter by lazy {
        ParamsAdapter()
    }

    private val sortByAdapter: ParamsAdapter by lazy {
        ParamsAdapter()
    }

    private var sortBy:String = RepositoryImpl.sortBy.PUBLISHEDAT
    private var language:String  = RepositoryImpl.ISO_639_1.RUSSIA

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewModel.isFirst.observe(viewLifecycleOwner){
//            if (it){
//                viewModel.updateLanguage(Api.Companion.ISO_639_1.RUSSIA)
//                viewModel.changeIsFirst()
//            }
//            else{
//                savedInstanceState?.getString(LANGUAGE)?.let { selectedLanguage ->
//                    viewModel.updateLanguage(selectedLanguage)
//                    Log.d("Language", "onViewStateRestored")
//                }
//
//            }
//        }
//        if (savedInstanceState != null) {
//            val lan = savedInstanceState.getString(LANGUAGE)
//            if (lan != null) {
//                viewModel.getAllNews("",viewModel.getSortByParam(), viewModel.getLanguage())
//            }
//        }
        setupRecyclerView()
        setupLanguageRecyclerView()
        setupSortByRV()
        binding.mainSwipeRefresh.setOnRefreshListener(this@AllNewsFragment)

        lanAdapter.paramsList = viewModel.getLanguage().toMutableList()
        sortByAdapter.paramsList = viewModel.getSortByParam().toMutableList()
        viewModel.getAllNews("", sortBy, language)
        viewModel.allNews.observe(viewLifecycleOwner) { news ->
            newsAdapter.submitList(news)
        }
    }

    private fun setupSortByRV() {
        with(binding.sortByRV) {
            hasFixedSize()
            adapter = sortByAdapter
            recycledViewPool.setMaxRecycledViews(
                ParamsAdapter.VIEW_TYPE_ENABLED,
                ParamsAdapter.MAX_POOL_SIZE_ENABLED
            )
            recycledViewPool.setMaxRecycledViews(
                ParamsAdapter.VIEW_TYPE_DISABLED,
                ParamsAdapter.MAX_POOL_SIZE_DISABLED
            )
        }
        setupSortByAdapterClickListener()
    }

    private fun setupSortByAdapterClickListener() {
        sortByAdapter.onParamClickListener = {
            sortByAdapter.changeEnableState(it)
            sortBy = it.param
            viewModel.getAllNews("", it.param, language)
        }
    }

    private fun setupLanguageRecyclerView() {
        with(binding.languageRV) {
            hasFixedSize()
            adapter = lanAdapter
            recycledViewPool.setMaxRecycledViews(
                ParamsAdapter.VIEW_TYPE_ENABLED,
                ParamsAdapter.MAX_POOL_SIZE_ENABLED
            )
            recycledViewPool.setMaxRecycledViews(
                ParamsAdapter.VIEW_TYPE_DISABLED,
                ParamsAdapter.MAX_POOL_SIZE_DISABLED
            )
        }
        setupLanAdapterClickListener()
    }

    private fun setupLanAdapterClickListener() {
        lanAdapter.onParamClickListener = {
            lanAdapter.changeEnableState(it)
            viewModel.getAllNews("", sortBy, it.param)
            language = it.param
        }
    }

    private fun setupRecyclerView() {
        with(binding.mainRv) {
            hasFixedSize()
            adapter = newsAdapter
            recycledViewPool.setMaxRecycledViews(
                NewsAdapter.VIEW_TYPE,
                NewsAdapter.MAX_POOL_SIZE
            )
        }
        setupClickListener()
    }
    private fun setupClickListener() {
        newsAdapter.onNewsItemClickListener = {
            if (it.url!=null){
                val intent = DetailsActivity.newIntentEditItem(requireContext(), it.url)
                startActivity(intent)
            }
            else Toast.makeText(requireActivity().baseContext, "NOT", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onRefresh() {
        viewModel.getAllNews("", sortBy, language)
        binding.mainSwipeRefresh.isRefreshing = false
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.main_menu, menu)
        val searchView = menu.findItem(R.id.search)?.actionView as SearchView
        searchView.queryHint = "Search news"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(keyword: String): Boolean {
                viewModel.getAllNews(keyword, sortBy, language)
                return false
            }

            override fun onQueryTextChange(keyword: String): Boolean {
                viewModel.getAllNews(keyword, sortBy, language)
                return false
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(LANGUAGE, language)
        Log.d("Language", "onSaveInstanceState: $language")
    }
//
//    override fun onViewStateRestored(savedInstanceState: Bundle?) {
//        super.onViewStateRestored(savedInstanceState)
//        savedInstanceState?.getString(LANGUAGE)?.let { selectedLanguage ->
//            if (selectedLanguage.isEmpty()) {
//                viewModel.updateLanguage(Api.Companion.ISO_639_1.RUSSIA)
//            } else {
//                viewModel.updateLanguage(selectedLanguage)
//                Log.d("Language", "onViewStateRestored")
//            }
//        }
//    }


    companion object {
        const val LANGUAGE = "language"
    }
}