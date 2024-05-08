package com.shipping.prueba_tecnica_movil.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Embedded

import com.shipping.prueba_tecnica_movil.domain.model.Country
@Entity(tableName = "country_table")
data class CountryEntity(
    @PrimaryKey
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "flag_url") val flagUrl: String,
    @ColumnInfo(name = "capital") val capital: String,
    @ColumnInfo(name = "official_languages") val officialLanguages: List<String>,
    @ColumnInfo(name = "currency") val currency: String,
    @ColumnInfo(name = "population") val population: Long,
    @ColumnInfo(name = "region") val region: String,
    @ColumnInfo(name = "subregion") val subregion: String,
    @ColumnInfo(name = "area") val area: Double,
    @ColumnInfo(name = "borders") val borders: List<String>,
    @ColumnInfo(name = "maps") val maps: Map<String, String>,
    @ColumnInfo(name = "tld") val tld: List<String>,
    @Embedded(prefix = "idd_") val idd: IddEntity
)

data class IddEntity(
    @ColumnInfo(name = "root") val root: String,
    @ColumnInfo(name = "suffixes") val suffixes: List<String>
)

fun Country.toDatabase(): CountryEntity {
    return CountryEntity(
        name = name,
        flagUrl = flagUrl,
        capital = capital,
        officialLanguages = officialLanguages,
        currency = currency,
        population = population,
        region = region,
        subregion = subregion,
        area = area,
        borders = borders,
        maps = maps,
        tld = tld,
        idd = IddEntity(root = idd.root, suffixes = idd.suffixes)
    )
}