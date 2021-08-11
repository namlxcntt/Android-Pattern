package com.lxn.examplepatternretrofit.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lxn.examplepatternretrofit.data.model.Match
import com.lxn.examplepatternretrofit.databinding.LayoutItemMatchBinding


class MatchAdapter : RecyclerView.Adapter<MatchAdapter.ViewHolder>() {
    private var listMatch: ArrayList<Match> = ArrayList()
    lateinit var binding: LayoutItemMatchBinding


    fun addAllList(list: ArrayList<Match>) {
        this.listMatch.apply {
            clear()
            addAll(list)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchAdapter.ViewHolder {
        binding = LayoutItemMatchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int = listMatch.size


    inner class ViewHolder(private val binding: LayoutItemMatchBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(position: Int) {
            val match = listMatch[position]
            binding.tvDays.text = match.ngay
            binding.tvHour.text = match.gio
            binding.tvNameTeamOne.text = match.doi1
            binding.tvNameTeamTwo.text = match.doi2
            binding.tvRound.text = match.vong
            Glide.with(itemView.context).load(match.quocky1).into(binding.imgTeamOne)
            Glide.with(itemView.context).load(match.quocky2).into(binding.imgTeamTwo)
            itemView.setOnClickListener {
                Log.e("Namlxnctt", "Click click")
            }

        }
    }
}