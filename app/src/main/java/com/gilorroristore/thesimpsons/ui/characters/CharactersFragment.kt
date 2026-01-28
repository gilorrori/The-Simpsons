package com.gilorroristore.thesimpsons.ui.characters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.gilorroristore.thesimpsons.databinding.FragmentCharactersBinding
import com.gilorroristore.thesimpsons.domain.model.CharactersModel
import com.gilorroristore.thesimpsons.ui.characters.adapter.CharacterAdapter
import com.gilorroristore.thesimpsons.ui.core.helper.EndlessScrollListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharactersFragment : Fragment() {
    private var _binding: FragmentCharactersBinding? = null
    private val binding get() = _binding!!
    private val charactersViewModel by viewModels<CharactersViewModel>()
    private lateinit var characterAdapter: CharacterAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCharactersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
    }

    private fun initUi() {
        charactersViewModel.getAllCharacters()
        getAllCharacters()
    }


    private fun getAllCharacters() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                charactersViewModel.state.collect {
                    evaluateState(it)
                }
            }
        }

    }
    private fun evaluateState(response: CharactersState) {
        when (response) {
            CharactersState.Loading -> loadingState()
            is CharactersState.Success -> successState(response.charactersModel)
            is CharactersState.Error -> errorState(response.error)
            is CharactersState.SuccessNewList -> {}
            CharactersState.None -> {}
        }
    }

    private fun loadingState() {
        binding.pbCharacter.isVisible = true
    }

    private fun successState(charactersModel: CharactersModel) {
        binding.pbCharacter.isVisible = false
        characterAdapter = CharacterAdapter(charactersModel.results) {
            navToDetail(it)
        }

        binding.rvCharacters.adapter = characterAdapter

        //loadNextPage(charactersModel.next ?: "", charactersModel.pages)
    }

    private fun errorState(error: String) {
        binding.pbCharacter.isVisible = false
        Toast.makeText(requireActivity(), error, Toast.LENGTH_SHORT).show()
    }


    private fun navToDetail(id: Int) {
        findNavController().navigate(
            CharactersFragmentDirections.actionCharactersFragmentToDetailCharacterFragment(
                id
            )
        )
    }

    /**
     * Al hacer el scroll en el recyclerview se debe hacer el siguiente consumo web
     */
    private fun loadNextPage(next: String, totalPages: Int) {
        val page = next.substring(47, next.length)
        if (page.toInt() >= totalPages) return

        binding.rvCharacters.addOnScrollListener(object : EndlessScrollListener(page.toInt() - 1) {
            override fun onLoadMore(loadNewPage: Int) {
               // llamar a la nueva pagina
            }
        })
    }
}