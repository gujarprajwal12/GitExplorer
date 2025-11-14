package com.psg.gitexplorer.presentation.search

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.psg.gitexplorer.R
import com.psg.gitexplorer.data.model.Repository
import com.psg.gitexplorer.databinding.FragmentSearchBinding
import com.psg.gitexplorer.presentation.search.adapter.RepositoryAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment(R.layout.fragment_search) {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val vm: SearchViewModel by viewModels()
    private lateinit var adapter: RepositoryAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentSearchBinding.bind(view)

        adapter = RepositoryAdapter { repo ->
            val action = com.psg.gitexplorer.presentation.search.SearchFragmentDirections.actionSearchToDetails(repo)
            findNavController().navigate(action)
        }



        binding.rvRepos.layoutManager = LinearLayoutManager(requireContext())
        binding.rvRepos.adapter = adapter

        binding.btnSearch.setOnClickListener {
            val q = binding.edtSearch.text.toString().trim()
            if (q.isNotEmpty()) vm.processIntent(SearchIntent.Search(q))
            else Toast.makeText(requireContext(), "Enter a query", Toast.LENGTH_SHORT).show()
        }

        lifecycleScope.launchWhenStarted {
            vm.state.collectLatest { state ->
                binding.progressBar.visibility = if (state.isLoading) View.VISIBLE else View.GONE
                state.error?.let { Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show() }
                adapter.submitList(state.repos)
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
