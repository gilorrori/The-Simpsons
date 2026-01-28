package com.gilorroristore.thesimpsons.ui.characters.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gilorroristore.thesimpsons.databinding.ItemCharacterBinding
import com.gilorroristore.thesimpsons.domain.model.CharacterDetailModel
import com.gilorroristore.thesimpsons.ui.core.RecyclerViewDiffUtil

class CharacterAdapter(
    private var listCharacters: List<CharacterDetailModel>,
    private val onItemSelected: (Int) -> Unit
) : RecyclerView.Adapter<CharacterViewHolder>() {

    fun updateList(newList: List<CharacterDetailModel> ){
        val characterDetailDiff = RecyclerViewDiffUtil(listCharacters, newList)
        val result = DiffUtil.calculateDiff(characterDetailDiff)
        listCharacters = newList as MutableList<CharacterDetailModel>
        result.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CharacterViewHolder(
            binding = ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onItemSelected = onItemSelected
        )

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
       holder.addItem(listCharacters[position])
    }

    override fun getItemCount() = listCharacters.size
}