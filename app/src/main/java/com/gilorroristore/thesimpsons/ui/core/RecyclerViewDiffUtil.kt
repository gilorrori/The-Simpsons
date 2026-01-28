package com.gilorroristore.thesimpsons.ui.core

import com.gilorroristore.thesimpsons.domain.model.CharacterDetailModel
import androidx.recyclerview.widget.DiffUtil

class RecyclerViewDiffUtil(
    private val oldList: List<CharacterDetailModel>,
    private val newList: List<CharacterDetailModel>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
       return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
       return  oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
       return oldList[oldItemPosition] == newList[newItemPosition]
    }
}