package com.forzz.psbinternshipcurrency.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.forzz.psbinternshipcurrency.databinding.FragmentCurrenciesScreenBinding
import com.forzz.psbinternshipcurrency.domain.model.Currency
import com.forzz.psbinternshipcurrency.presentation.adapters.CurrenciesAdapter
import com.forzz.psbinternshipcurrency.presentation.viewmodel.CurrenciesScreenViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrenciesScreenFragment : Fragment() {

    private lateinit var binding: FragmentCurrenciesScreenBinding
    private val viewModel: CurrenciesScreenViewModel by viewModels()
    private var currenciesAdapter: CurrenciesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        currenciesAdapter = CurrenciesAdapter()
        viewModel.loadCurrencies()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val layoutManager = LinearLayoutManager(requireContext())

        binding = FragmentCurrenciesScreenBinding.inflate(inflater, container, false)
        binding.currenciesRv.layoutManager = layoutManager
        binding.currenciesRv.adapter = currenciesAdapter

        viewModel.dailyCurrencies.observe(viewLifecycleOwner, Observer {
            binding.lastRefreshDateTv.text = it.date.toString()
            updateCurrenciesList(it.currencies)
        })

        return binding.root
    }

    private fun updateCurrenciesList(currenciesList: List<Currency>) {
        currenciesAdapter?.updateData(currenciesList)
    }
}