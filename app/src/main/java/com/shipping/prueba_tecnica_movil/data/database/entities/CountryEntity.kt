package com.shipping.prueba_tecnica_movil.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Embedded
import com.shipping.prueba_tecnica_movil.domain.model.Country
import org.json.JSONObject

@Entity(tableName = "country_table")

data class CountryEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long = 0,
    @ColumnInfo(name = "name_common") val nameCommon: String,
    @ColumnInfo(name = "name_official") val nameOfficial: String,
    @ColumnInfo(name = "tld") val tld: List<String>,
    @ColumnInfo(name = "independent") val independent: Boolean,
    @ColumnInfo(name = "status") val status: String,
    @ColumnInfo(name = "unMember") val unMember: Boolean,
    @ColumnInfo(name = "currencies") val currencies: String,
    @ColumnInfo(name = "capital") val capital: String,
    @ColumnInfo(name = "region") val region: String,
    @ColumnInfo(name = "subregion") val subregion: String,
    @ColumnInfo(name = "languages") val languages: String,
    @ColumnInfo(name = "latlng") val latlng: String,
    @ColumnInfo(name = "landlocked") val landlocked: Boolean,
    @ColumnInfo(name = "borders") val borders: String,
    @ColumnInfo(name = "area") val area: Double,
    @ColumnInfo(name = "flag") val flag: String,
    @ColumnInfo(name = "maps") val maps: String,
    @ColumnInfo(name = "population") val population: Int,
    @ColumnInfo(name = "gini") val gini: String,
    @ColumnInfo(name = "fifa") val fifa: String,
    @ColumnInfo(name = "timezones") val timezones: String,
    @ColumnInfo(name = "continents") val continents: String,
    @ColumnInfo(name = "flags") val flags: String,
    @ColumnInfo(name = "startOfWeek") val startOfWeek: String,
    @ColumnInfo(name = "capitalInfo_latlng") val capitalInfoLatlng: String,
    @ColumnInfo(name = "postalCode_format") val postalCodeFormat: String,
    @ColumnInfo(name = "postalCode_regex") val postalCodeRegex: String,
    @ColumnInfo(name = "positionCounter") val positionCounter: Int

)



fun Country.toDatabase(): CountryEntity {
    return CountryEntity(
        nameCommon = name.common,
        nameOfficial = name.official,
        tld = tld,
        independent = independent,
        status = status,
        unMember = unMember,
        currencies = currencies,
        capital = capital.firstOrNull() ?: "",
        region = region,
        subregion = subregion,
        languages = convertMapToString(languages),
        latlng = latlng.joinToString(separator = ","),
        landlocked = landlocked,
        borders = borders.joinToString(separator = ","),
        area = area,
        flag = flag,
        maps = convertMapToString(maps),
        population = population,
        gini = convertMapToString(gini),
        fifa = fifa,
        timezones = timezones.joinToString(separator = ","),
        continents = continents.joinToString(separator = ","),
        flags = flags,
        startOfWeek = startOfWeek,
        capitalInfoLatlng = capitalInfo.latlng.joinToString(separator = ","),
        postalCodeFormat = postalCode.format,
        postalCodeRegex = postalCode.regex,
        positionCounter = positionCounter
    )

}
private fun convertMapToString(map: Map<*, *>): String {
    return JSONObject(map).toString()
}