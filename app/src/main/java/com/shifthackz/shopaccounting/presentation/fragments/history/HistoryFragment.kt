package com.shifthackz.shopaccounting.presentation.fragments.history

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.shifthackz.shopaccounting.R
import com.shifthackz.shopaccounting.di.component.ViewModelComponent
import com.shifthackz.shopaccounting.domain.HistoryViewModel
import com.shifthackz.shopaccounting.presentation.adapter.HistoryAdapter
import com.shifthackz.shopaccounting.presentation.base.BaseFragment
import com.shifthackz.shopaccounting.repository.database.entity.HistoryEntity
import kotlinx.android.synthetic.main.fragment_history.*
import javax.inject.Inject

class HistoryFragment : BaseFragment() {

    var viewModel: HistoryViewModel? = null
        @Inject set

    override fun injectDependency(component: ViewModelComponent) {
        component.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
    }

    override fun onResume() {
        super.onResume()
        viewModel?.getLiveDataItems()?.removeObservers(this)
        initViewModel()
    }

    override fun onDetach() {
        super.onDetach()
        viewModel?.getLiveDataItems()?.removeObservers(this)
    }

    private fun initViewModel() {
        viewModel?.getAllItems()
        val observer: Observer<List<HistoryEntity>> = Observer {
            initRecyclerView(it!!)
        }
        viewModel?.getLiveDataItems()?.observeForever(observer)
    }

    private fun initRecyclerView(list: List<HistoryEntity>) {
        val manager = LinearLayoutManager(this.requireContext())
        val historyAdapter = HistoryAdapter(this.requireContext(), list)
        rvHistory.layoutManager = manager
        rvHistory.adapter = historyAdapter
    }

    companion object {
        @JvmStatic
        fun newInstance(): HistoryFragment {
            return HistoryFragment()
        }
    }
}
