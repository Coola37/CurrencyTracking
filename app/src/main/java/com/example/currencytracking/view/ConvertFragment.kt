package com.example.currencytracking.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.currencytracking.R
import com.example.currencytracking.databinding.FragmentConvertBinding
import com.example.currencytracking.viewmodel.ConvertViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ConvertFragment : Fragment(R.layout.fragment_convert) {

    private lateinit var binding: FragmentConvertBinding
    private lateinit var viewModel: ConvertViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[ConvertViewModel::class.java]
        binding = FragmentConvertBinding.bind(view)

        val spinnerItems = listOf("Select the currency type.","USD", "EUR", "GBP", "GA", "C", "GAG", "BTC", "ETH", "XU100")

        val adapter =
            ArrayAdapter(requireActivity(), android.R.layout.simple_spinner_item, spinnerItems)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.bottomNavigationViewHome.setOnNavigationItemReselectedListener {
            when(it.itemId){
                R.id.ic_action_home -> {
                    findNavController().navigate(R.id.action_convertFragment_to_homeFragment)
                    false
                }
                R.id.ic_action_convert -> {
                    findNavController().navigate(R.id.action_homeFragment_to_convertFragment)
                    false
                }

                else -> false
            }
        }
        val menuItem = binding.bottomNavigationViewHome.menu.getItem(1)
        menuItem.isChecked = true

        binding.spinner1.adapter = adapter

        binding.spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                viewModel.fetchExchangeData()
                viewModel.exchangeData.observe(viewLifecycleOwner, Observer { exchangeData ->
                    exchangeData.firstOrNull()?.let { data ->
                        val baseValue = 0
                        val currency = when (position) {
                            1 -> data.usd?.satis?.toFloat()
                            2 -> data.eur?.satis?.toFloat()
                            3 -> data.gbp?.satis?.toFloat()
                            4 -> data.ga?.satis?.toFloat()
                            5 -> data.c?.satis?.toFloat()
                            6 -> data.gag?.satis?.toFloat()
                            7 -> data.btc?.satis?.toFloat()
                            8 -> data.eth?.satis?.toFloat()
                            9 -> data.xu100?.satis?.toFloat()
                            else -> baseValue.toFloat()
                        }

                        binding.buttonConvert.setOnClickListener {
                            val inputText = binding.editTextInput.text.toString().toFloat()
                            val result = currency?.times(inputText)
                            binding.textViewResult.text = "${result.toString()}TL"
                        }
                    }
                })
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }


    }


}