package com.example.newsproject.presentation.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.newsproject.R
import com.example.newsproject.databinding.FragmentSavedNewsBinding
import com.example.newsproject.presentation.model.ArticleUi
import com.example.newsproject.presentation.ui.activities.DetailsActivity
import com.example.newsproject.presentation.ui.adapters.NewsAdapter
import com.example.newsproject.presentation.ui.viewModels.CacheArticlesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class SavedNewsFragment :
    Fragment(),
    NewsAdapter.RecyclerOnClickListener {

    private val binding: FragmentSavedNewsBinding by lazy {
        FragmentSavedNewsBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModels<CacheArticlesViewModel>()

    private val adapter: NewsAdapter by lazy {
        NewsAdapter(this@SavedNewsFragment)
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
        binding.newsRv.adapter = adapter

        viewModel.error.onEach {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        }

        lifecycleScope.launchWhenResumed {
            viewModel.allNewsSharedFlow.onEach {
                adapter.articlesList = it
            }
        }
    }

    override fun onClick(articleUi: ArticleUi) {
        DetailsActivity.launchDetailsActivity(requireContext(), articleUi.url.toString())
    }

    override fun onSaveClick(articleUi: ArticleUi) {
        viewModel.deleteSavedArticle(articleUi.url.toString())
    }
}