package com.example.newsproject.presentation.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.newsproject.R
import com.example.newsproject.databinding.FragmentAllNewsBinding
import com.example.newsproject.presentation.model.ArticleUi
import com.example.newsproject.presentation.ui.activities.DetailsActivity
import com.example.newsproject.presentation.ui.adapters.NewsAdapter
import com.example.newsproject.presentation.ui.viewModels.AllNewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class AllNewsFragment : Fragment(),
    NewsAdapter.RecyclerOnClickListener,
    AdapterView.OnItemSelectedListener {

    private val binding: FragmentAllNewsBinding by lazy {
        FragmentAllNewsBinding.inflate(layoutInflater)
    }

    private val spinnerAdapter by lazy {
        ArrayAdapter.createFromResource(requireContext(),
            R.array.all_news_spinner_categories,
            (android.R.layout.simple_spinner_item))
    }

    private val newsAdapter: NewsAdapter by lazy {
        NewsAdapter(this@AllNewsFragment)
    }

    private val viewModel by viewModels<AllNewsViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUi()
        observeViewModel()
    }

    private fun setUi() {
        binding.categorySpinner.onItemSelectedListener = this@AllNewsFragment
        binding.categorySpinner.adapter = spinnerAdapter
        binding.newsRv.adapter = newsAdapter
    }

    private fun observeViewModel() {
        viewModel.error.onEach {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        lifecycleScope.launchWhenResumed {
            viewModel.allNewsSharedFlow.collect {
                newsAdapter.articlesList = it
            }
        }
    }

    override fun onClick(articleUi: ArticleUi) {
        startActivity(DetailsActivity.launchDetailsActivity(requireContext(),
            articleUi.url.toString()))
    }

    override fun onSaveClick(articleUi: ArticleUi) {
        viewModel.saveArticle(articleUi = articleUi)
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
        when (position) {
            0 -> viewModel.updateSortBy(RELEVANCY)
            1 -> viewModel.updateSortBy(POPULARITY)
            2 -> viewModel.updateSortBy(PUBLISHED_AT)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu, menu)
        val searchView = menu.findItem(R.id.search)?.actionView as SearchView
        searchView.queryHint = "Search news"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.updateKeyword(query.toString())
                Log.d("MyText", query.toString())
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.updateKeyword(newText.toString())
                Log.d("MyText", newText.toString())
                return false
            }
        })
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onNothingSelected(p0: AdapterView<*>?) = Unit

    private companion object {
        const val RELEVANCY = "relevancy"
        const val POPULARITY = "popularity"
        const val PUBLISHED_AT = "publishedAt"
    }

}