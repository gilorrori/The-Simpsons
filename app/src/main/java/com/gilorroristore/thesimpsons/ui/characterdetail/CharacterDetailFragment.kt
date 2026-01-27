package com.gilorroristore.thesimpsons.ui.characterdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.gilorroristore.thesimpsons.BuildConfig
import com.gilorroristore.thesimpsons.databinding.FragmentDetailCharacterBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterDetailFragment : Fragment() {

    private val characterDetailViewModel by viewModels<CharacterDetailViewModel>()
    private val args: CharacterDetailFragmentArgs by navArgs()
    private lateinit var _binding: FragmentDetailCharacterBinding
    private val binding get() = _binding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailCharacterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        characterDetailViewModel.getDetailCharacter(args.idCharacter)
        getCharacterDetail()
    }

    private fun getCharacterDetail() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                characterDetailViewModel.state.collect {
                    when(it){
                        CharacterDetailState.Loading -> loadingState()
                        is CharacterDetailState.Success -> successState(it)
                        is CharacterDetailState.Error -> errorState(it.error)
                    }
                }
            }
        }
    }


    private fun loadingState(){
        binding.pbCharacter.isVisible = true
    }

    private fun successState(success: CharacterDetailState.Success) {
        binding.pbCharacter.isVisible = false
        Glide.with(requireActivity()).load(BuildConfig.BASE_URL_IMAGE + success.characterDetailModel.portraitPath).circleCrop().into(binding.ivDetailCharacter)
        binding.tvCharacterDetailName.text = success.characterDetailModel.name
        binding.tvBirthdate.text = success.characterDetailModel.birthdate
    }

    private fun errorState(error: String) {
        binding.pbCharacter.isVisible = false
    }

}