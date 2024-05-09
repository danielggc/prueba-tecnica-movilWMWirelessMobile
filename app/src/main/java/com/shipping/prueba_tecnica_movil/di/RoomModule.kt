package com.shipping.prueba_tecnica_movil.di

import android.content.Context
import androidx.room.Room
import com.shipping.prueba_tecnica_movil.data.database.RemissionDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val COUNTRIES_DATABASE_NAME = "countries_table"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, RemissionDatabase::class.java, COUNTRIES_DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideQuoteDao(db: RemissionDatabase ) = db.getRemissionDao()
}