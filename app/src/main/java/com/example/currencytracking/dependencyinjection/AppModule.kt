package com.example.currencytracking.dependencyinjection

import android.content.Context
import androidx.room.Room
import com.example.currencytracking.repo.ExchangeRepository
import com.example.currencytracking.repo.ExchangeRepositoryInterface
import com.example.currencytracking.retrofit.API
import com.example.currencytracking.room.ExchangeDb
import com.example.currencytracking.utils.Util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun injectRoomDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context, ExchangeDb::class.java, "CurrencyDB"
    ).build()

    @Singleton
    @Provides
    fun injectDao(database: ExchangeDb) = database.currencyDao()

    @Singleton
    @Provides
    fun injectRetrofitAPI() : API {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(API::class.java)
    }


    @Singleton
    @Provides
    fun provideCurrencyRepository(database: ExchangeDb, retrofitAPI: API)
            : ExchangeRepositoryInterface = ExchangeRepository(database.currencyDao(), retrofitAPI)

}