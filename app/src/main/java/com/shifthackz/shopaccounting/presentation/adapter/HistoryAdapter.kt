package com.shifthackz.shopaccounting.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.shifthackz.shopaccounting.R
import com.shifthackz.shopaccounting.presentation.base.BaseAdapter
import com.shifthackz.shopaccounting.presentation.item.HistoryViewHolder
import com.shifthackz.shopaccounting.repository.database.entity.HistoryEntity

class HistoryAdapter(private val context: Context, private val history: List<HistoryEntity>) :
    BaseAdapter<HistoryViewHolder, HistoryEntity>(history as MutableList<HistoryEntity>) {

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): HistoryViewHolder {
        return HistoryViewHolder(LayoutInflater.from(context).inflate(R.layout.item_history, p0, false))
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.bind(history[position])
    }

    override fun getItemCount(): Int {
        return history.size
    }
}
