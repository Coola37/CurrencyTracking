package com.example.currencytracking.viewmodel

import android.app.Application
import com.example.currencytracking.repo.FakeExchangeRepository
import com.example.currencytracking.retrofit.API
import org.junit.Before
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.currencytracking.model.ExchangeDetail
import com.example.currencytracking.model.ExchangeResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import retrofit2.Call
import retrofit2.Response
import kotlin.text.Typography.times


@ExperimentalCoroutinesApi
class HomeViewModelTest {

    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var fakeAPI: API

    private lateinit var fakeRepository: FakeExchangeRepository

    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        fakeRepository = FakeExchangeRepository()
        homeViewModel = HomeViewModel(fakeRepository, fakeAPI, Application())
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun cleanup() {
        testScope.cleanupTestCoroutines()
        Dispatchers.resetMain()
    }

    @Test
    fun `fetchExchangeData`() = testScope.runBlockingTest {
        // Ana akışı TestCoroutineDispatcher ile değiştir
        Dispatchers.setMain(testDispatcher)

        val exchangeDetail = ExchangeDetail(
            satis = "5.0",
            alis = "4.5",
            degisim = "0.5",
            d_oran = "1.2",
            d_yon = "up"
        )
        val exchangeResponse = ExchangeResponse(
            usd = exchangeDetail,
            eur = exchangeDetail,
            gbp = exchangeDetail,
            ga = exchangeDetail,
            c = exchangeDetail,
            gag = exchangeDetail,
            btc = exchangeDetail,
            eth = exchangeDetail,
            xu100 = exchangeDetail
        )
        val mockCall = mock(Call::class.java) as Call<ExchangeResponse>
        `when`(fakeAPI.getExchangeData()).thenReturn(mockCall)
        `when`(mockCall.execute()).thenReturn(Response.success(exchangeResponse))

        homeViewModel.fetchExchangeData()

        assert(fakeRepository.exchange.size == 1)

        // Ana akışı geri al
        Dispatchers.resetMain()
    }

    @Test
    fun `refreshExchangeData`() = testScope.runBlockingTest {
        val exchangeDetail = ExchangeDetail(
            satis = "5.0",
            alis = "4.5",
            degisim = "0.5",
            d_oran = "1.2",
            d_yon = "up"
        )
        val exchangeResponse = ExchangeResponse(
            usd = exchangeDetail,
            eur = exchangeDetail,
            gbp = exchangeDetail,
            ga = exchangeDetail,
            c = exchangeDetail,
            gag = exchangeDetail,
            btc = exchangeDetail,
            eth = exchangeDetail,
            xu100 = exchangeDetail
        )
        val mockCall = mock(Call::class.java) as Call<ExchangeResponse>
        `when`(fakeAPI.getExchangeData()).thenReturn(mockCall)
        `when`(mockCall.execute()).thenReturn(Response.success(exchangeResponse))

        val isLoadingObserver = mock(Observer::class.java) as Observer<Boolean>
        homeViewModel.isLoading.observeForever(isLoadingObserver)

        homeViewModel.refreshExchangeData()


        verify(isLoadingObserver, times(2)).onChanged(false)
    }

}
