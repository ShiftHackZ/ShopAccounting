package com.shifthackz.shopaccounting.presentation.item

import android.support.v7.widget.RecyclerView
import android.text.format.DateFormat
import android.view.View
import com.shifthackz.shopaccounting.repository.database.entity.HistoryEntity
import kotlinx.android.synthetic.main.item_history.view.*
import java.util.*

class HistoryViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private var historyEntity: HistoryEntity? = null

    fun bind(historyEntity: HistoryEntity) {
        this.historyEntity = historyEntity
        setupItem()
    }

    private fun setupItem() {
        view.txtHistoryCount.text = historyEntity?.oldCount.toString().plus(" шт")
        view.txtHistoryDate.text = DateFormat.format("dd.MM.yyyy HH:mm:ss", Date(historyEntity!!.time)).toString()
        view.txtHistoryPrice.text = historyEntity?.oldPrice.toString().plus(" грн")
        view.txtHistoryReason.text = historyEntity?.reason
        view.txtHistoryName.text = historyEntity?.name
    }
}