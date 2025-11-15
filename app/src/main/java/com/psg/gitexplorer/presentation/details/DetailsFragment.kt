package com.psg.gitexplorer.presentation.details

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.psg.gitexplorer.R
import com.psg.gitexplorer.data.local.FavoriteEntity
import com.psg.gitexplorer.data.model.Repository
import com.psg.gitexplorer.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class DetailsFragment : Fragment(R.layout.fragment_details) {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val vm: DetailsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentDetailsBinding.bind(view)


        val repo: Repository = DetailsFragmentArgs.fromBundle(requireArguments()).repo


        binding.txtFullName.text = repo.full_name
        binding.txtDescription.text = repo.description ?: "No description"
        binding.txtStats.text =
            " ${repo.stargazers_count} | Forks: ${repo.forks_count} | ${repo.language ?: "—"}"

        Glide.with(requireContext())
            .load(repo.owner.avatar_url)
            .into(binding.imgAvatar)


        vm.setInitial(repo.id)

        lifecycleScope.launchWhenStarted {
            vm.state.collectLatest { s ->
                binding.btnFavorite.text =
                    if (s.isFavorite) "Remove Favorite" else "Add Favorite"

                s.error?.let { Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show() }
            }
        }


        binding.btnFavorite.setOnClickListener {
            val entity = FavoriteEntity(
                id = repo.id,
                name = repo.name,
                fullName = repo.full_name,
                description = repo.description,
                ownerLogin = repo.owner.login,
                ownerAvatar = repo.owner.avatar_url,
                stars = repo.stargazers_count,
                forks = repo.forks_count,
                language = repo.language
            )
            vm.toggleFavorite(entity)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}
