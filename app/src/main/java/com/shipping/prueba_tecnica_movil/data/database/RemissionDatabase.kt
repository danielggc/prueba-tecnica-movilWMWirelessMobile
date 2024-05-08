package com.shipping.prueba_tecnica_movil.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.shipping.prueba_tecnica_movil.data.database.dao.RemissionDao
import com.shipping.prueba_tecnica_movil.data.database.entities.CountryEntity

@Database(entities = [CountryEntity::class], version = 1)
abstract class RemissionDatabase: RoomDatabase() {

    abstract fun getRemissionDao(): RemissionDao
}