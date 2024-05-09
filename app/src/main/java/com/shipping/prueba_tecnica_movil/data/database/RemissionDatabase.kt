package com.shipping.prueba_tecnica_movil.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.shipping.prueba_tecnica_movil.data.database.dao.RemissionDao
import com.shipping.prueba_tecnica_movil.data.database.entities.CountryEntity

@Database(entities = [CountryEntity::class], version = 1)
@TypeConverters(Converters::class)

abstract class RemissionDatabase: RoomDatabase() {

    abstract fun getRemissionDao(): RemissionDao
}