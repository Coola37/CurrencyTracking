package com.example.currencytracking.viewmodel

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.example.currencytracking.repo.ExchangeRepositoryInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: ExchangeRepositoryInterface,
    application: Application
) : BaseViewModel(application){

    val exchangeData = repository.getExchange()

    // Function to refresh the data
    fun refreshData() {
        launch {
            repository.getExchange()
        }
    }

}