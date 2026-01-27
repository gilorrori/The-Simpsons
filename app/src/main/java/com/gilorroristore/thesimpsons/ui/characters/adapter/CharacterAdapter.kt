package com.gilorroristore.thesimpsons.ui.characters.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gilorroristore.thesimpsons.databinding.ItemCharacterBinding
import com.gilorroristore.thesimpsons.domain.model.CharacterDetailModel

class CharacterAdapter(
    private var listCharacters: MutableList<CharacterDetailModel> = mutableListOf(),
    private val onItemSelected: (Int) -> Unit
) : RecyclerView.Adapter<CharacterViewHolder>() {

    fun updateList(listCharacters: MutableList<CharacterDetailModel> ){
        val start = listCharacters.size
        this.listCharacters = listCharacters
        notifyDataSetChanged()
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