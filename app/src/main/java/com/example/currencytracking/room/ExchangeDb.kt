package com.example.currencytracking.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.currencytracking.model.Exchange

@Database(entities = [Exchange::class] , version = 1)
abstract class ExchangeDb : RoomDatabase(){
    abstract fun currencyDao() : ExchangeDao

}