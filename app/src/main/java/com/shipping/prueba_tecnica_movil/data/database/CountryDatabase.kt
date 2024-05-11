package com.shipping.prueba_tecnica_movil.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.shipping.prueba_tecnica_movil.data.database.dao.CountryDao
import com.shipping.prueba_tecnica_movil.data.database.entities.CountryEntity

@Database(entities = [CountryEntity::class], version = 1)
@TypeConverters(Converters::class)

abstract class CountryDatabase: RoomDatabase() {

    abstract fun getCountryDao(): CountryDao
}