package com.example.currencytracking.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.currencytracking.R
import com.example.currencytracking.databinding.FragmentHomeBinding
import com.example.currencytracking.viewmodel.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var viewModel: HomeViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[HomeViewModel::class.java]
        binding = FragmentHomeBinding.bind(view)

        viewModel.fetchExchangeData()

        viewModel.exchangeData.observe(viewLifecycleOwner, Observer { exchangeList ->
            exchangeList.firstOrNull()?.let { exchange ->
                binding.tvUsdBuy.text = exchange.usd?.alis
                binding.tvEurBuy.text = exchange.eur?.alis
                binding.tvGbpBuy.text = exchange.gbp?.alis
                binding.tvBtcBuy.text = exchange.btc?.alis
                binding.tvEthBuy.text = exchange.eth?.alis
                binding.tvXuBuy.text = exchange.xu100?.alis
                binding.tvGaBuy.text = exchange.ga?.alis
                binding.tvGagBuy.text = exchange.gag?.alis
                binding.tvCBuy.text = exchange.c?.alis

                binding.tvUsdSell.text = exchange.usd?.satis
                binding.tvEurSell.text = exchange.eur?.satis
                binding.tvGbpSell.text = exchange.gbp?.satis
                binding.tvBtcSell.text = exchange.btc?.satis
                binding.tvEthSell.text = exchange.eth?.satis
                binding.tvXuSell.text = exchange.xu100?.satis
                binding.tvGaSell.text = exchange.ga?.satis
                binding.tvGagSell.text = exchange.gag?.satis
                binding.tvCSell.text = exchange.c?.satis

            }
        })
    }

}