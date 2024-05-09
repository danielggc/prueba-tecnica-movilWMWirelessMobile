    package com.shipping.prueba_tecnica_movil.data.database.dao

    import androidx.room.Dao
    import androidx.room.Insert
    import androidx.room.OnConflictStrategy
    import androidx.room.Query
    import androidx.room.Update
    import com.shipping.prueba_tecnica_movil.data.database.entities.CountryEntity
    import androidx.room.TypeConverters
    import androidx.room.Database
    import com.shipping.prueba_tecnica_movil.data.database.Converters


    @Dao
    interface RemissionDao {

        @Query("SELECT * FROM country_table ORDER BY name_common DESC")
        suspend fun getAllCountries():List<CountryEntity>
        @Query("SELECT * FROM country_table ORDER BY `positionCounter` ASC LIMIT :pageSize OFFSET :offset")
        suspend fun getcountriesInBatches(pageSize: Int, offset: Int): List<CountryEntity>

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertAll(remissionModel:List<CountryEntity>)

        @Query("DELETE FROM country_table")
        suspend fun deleteAllQuotes()

        @Update
        fun updateOrder(items: List<CountryEntity>):Int
    }



