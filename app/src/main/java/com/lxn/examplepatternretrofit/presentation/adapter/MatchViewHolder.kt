package com.lxn.examplepatternretrofit.presentation.adapter

import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.lxn.examplepatternretrofit.R
import com.lxn.examplepatternretrofit.data.model.Match
import com.lxn.examplepatternretrofit.platform.recycleview.BaseViewHolder
import kotlinx.android.synthetic.main.layout_item_match.view.*

class MatchViewHolder(viewGroup: ViewGroup) : BaseViewHolder<Match, MatchAdapter.ActionHolder>(
    viewGroup,
    R.layout.layout_item_match
) {
    override fun onBind(data: Match) {
        val match = data
        itemView.tv_days.text = match.ngay
        itemView.tv_hour.text = match.gio
        itemView.tv_name_team_one.text = match.doi1
        itemView.tv_name_team_two.text = match.doi2
        itemView.tv_round.text = match.vong

        Glide.with(itemView.context).load(match.quocky1).into(itemView.img_team_one)
        Glide.with(itemView.context).load(match.quocky2).into(itemView.img_team_two)

        itemView.setOnClickListener {
            listener?.onItemHolderClicked(MatchAdapter.ActionHolder.CLICK_ITEM_MATCH,data,adapterPosition)
        }
    }
}