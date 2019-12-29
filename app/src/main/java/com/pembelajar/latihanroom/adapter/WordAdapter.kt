package com.pembelajar.latihanroom.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.pembelajar.latihanroom.R
import com.pembelajar.latihanroom.databinding.ItemWordBinding
import com.pembelajar.latihanroom.model.Word

class WordAdapter internal constructor(private val context: Context): RecyclerView.Adapter<WordAdapter.ViewHolder>(){

    private var data = emptyList<Word>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(context)
        val itemBinding: ItemWordBinding = DataBindingUtil.inflate(inflater, R.layout.item_word, parent, false)
        return ViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data, position)
    }

    inner class ViewHolder(private val binding: ItemWordBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(data: List<Word>, position: Int){
            val currentData = data[position]
            binding.textData.text = currentData.word
        }
    }

    internal fun setWord(data: List<Word>){
        this.data = data
        notifyDataSetChanged()
    }

}