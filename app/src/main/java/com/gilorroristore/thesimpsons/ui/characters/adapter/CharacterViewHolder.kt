package com.gilorroristore.thesimpsons.ui.characters.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gilorroristore.thesimpsons.BuildConfig
import com.gilorroristore.thesimpsons.databinding.ItemCharacterBinding
import com.gilorroristore.thesimpsons.domain.model.CharacterDetailModel

class CharacterViewHolder(
    private val binding: ItemCharacterBinding,
    private val onItemSelected: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun addItem(characterDetail: CharacterDetailModel) {
        val context = binding.root.context
        val base = BuildConfig.BASE_URL_IMAGE

        Glide.with(context).load(base + characterDetail.portraitPath).into(binding.ivCharacter)
        binding.tvCharacterName.text = characterDetail.name

        binding.root.setOnClickListener {
            onItemSelected(characterDetail.id)
        }
    }
}