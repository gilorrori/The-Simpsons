package com.gilorroristore.thesimpsons.ui.characters.adapter

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gilorroristore.thesimpsons.BuildConfig
import com.gilorroristore.thesimpsons.R
import com.gilorroristore.thesimpsons.core.enum.StatusCharacter
import com.gilorroristore.thesimpsons.databinding.ItemCharacterBinding
import com.gilorroristore.thesimpsons.domain.model.CharacterDetailModel

class CharacterViewHolder(
    private val binding: ItemCharacterBinding,
    private val onItemSelected: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    /**
     * Pinta toda la información del personaje en cada Item
     * del RecyclerView
     * @author gil_orrori_94@hotmail.com
     */
    fun addItem(characterDetail: CharacterDetailModel) {
        val context = binding.root.context
        val base = BuildConfig.BASE_URL_IMAGE

        Glide.with(context).load(base + characterDetail.portraitPath).into(binding.ivCharacter)
        binding.tvCharacterName.text = characterDetail.name
        binding.tvOccupation.text = characterDetail.occupation
        val status = characterDetail.status

        if (status.contains(StatusCharacter.Alive.toString())){
            binding.ltAlive.setAnimation(R.raw.live_character)
            binding.tvStatusCharacter.text = context.getString(R.string.generic_alive)
        } else if (status.contains(StatusCharacter.Deceased.toString())) {
            binding.ltAlive.setAnimation(R.raw.deceased_character)
            binding.tvStatusCharacter.text = context.getString(R.string.generic_deceased)
        } else {
            binding.ltAlive.isVisible = false
        }

        binding.root.setOnClickListener {
            onItemSelected(characterDetail.id)
        }
    }
}