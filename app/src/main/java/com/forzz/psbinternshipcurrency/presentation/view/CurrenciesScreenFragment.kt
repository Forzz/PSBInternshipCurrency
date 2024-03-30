package com.forzz.psbinternshipcurrency.presentation.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.forzz.psbinternshipcurrency.databinding.FragmentCurrenciesScreenBinding
import com.forzz.psbinternshipcurrency.domain.model.Currency
import com.forzz.psbinternshipcurrency.presentation.adapters.CurrenciesAdapter
import com.forzz.psbinternshipcurrency.presentation.viewmodel.CurrenciesScreenViewModel
import dagger.hilt.android.AndroidEntryPoint
import com.forzz.psbinternshipcurrency.utils.Extensions.Companion.convertToCustomDateString

@AndroidEntryPoint
class CurrenciesScreenFragment : Fragment() {

    private lateinit var binding: FragmentCurrenciesScreenBinding
    private val viewModel: CurrenciesScreenViewModel by viewModels()
    private var currenciesAdapter: CurrenciesAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val layoutManager = LinearLayoutManager(requireContext())
        binding = FragmentCurrenciesScreenBinding.inflate(inflater, container, false)
        binding.currenciesRv.layoutManager = layoutManager
        currenciesAdapter = CurrenciesAdapter()
        binding.currenciesRv.adapter = currenciesAdapter

        viewModel.dailyCurrencies.observe(viewLifecycleOwner) {
            updateCurrenciesList(it.currencies)
        }

        viewModel.lastSuccessUpdateDateText.observe(viewLifecycleOwner) {
            binding.successReceiveData = it
        }

        viewModel.lastCbrUpdateDateText.observe(viewLifecycleOwner) {
            binding.cbrUpdateDate = it
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                binding.loadingCurrenciesPb.visibility = View.VISIBLE
            } else {
                binding.loadingCurrenciesPb.visibility = View.GONE
            }
        }

        return binding.root
    }

    private fun updateCurrenciesList(currenciesList: List<Currency>) {
        currenciesAdapter?.updateData(currenciesList)
    }
}