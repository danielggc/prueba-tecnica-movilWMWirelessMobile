package com.shipping.prueba_tecnica_movil.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Embedded
import com.shipping.prueba_tecnica_movil.domain.model.Country
import com.shipping.prueba_tecnica_movil.domain.model.NativeName
import kotlinx.serialization.json.Json
import org.json.JSONObject
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
@Entity(tableName = "country_table")

data class CountryEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Long = 0,
    @ColumnInfo(name = "native_name") val nativeName: String,
    @ColumnInfo(name = "name_common") val nameCommon: String,
    @ColumnInfo(name = "name_official") val nameOfficial: String,
    @ColumnInfo(name = "tld") val tld: List<String>,
    @ColumnInfo(name = "independent") val independent: Boolean,
    @ColumnInfo(name = "status") val status: String,
    @ColumnInfo(name = "unMember") val unMember: Boolean,
    @ColumnInfo(name = "currencies") val currencies: String,
    @ColumnInfo(name = "capital") val capital: List<String>,
    @ColumnInfo(name = "region") val region: String,
    @ColumnInfo(name = "subregion") val subregion: String,
    @ColumnInfo(name = "languages") val languages: String,
    @ColumnInfo(name = "latlng") val latlng: List<String>,
    @ColumnInfo(name = "landlocked") val landlocked: Boolean,
    @ColumnInfo(name = "borders") val borders:List<String>,
    @ColumnInfo(name = "area") val area: Double,
    @ColumnInfo(name = "flag") val flag: String,
    @ColumnInfo(name = "maps") val maps: String,
    @ColumnInfo(name = "population") val population: Int,
    @ColumnInfo(name = "gini") val gini: String,
    @ColumnInfo(name = "fifa") val fifa: String,
    @ColumnInfo(name = "timezones") val timezones: List<String>,
    @ColumnInfo(name = "continents") val continents: List<String>,
    @ColumnInfo(name = "flags") val flags: String,
    @ColumnInfo(name = "startOfWeek") val startOfWeek: String,
    @ColumnInfo(name = "capitalInfo_latlng") val capitalInfoLatlng: List<String>,
    @ColumnInfo(name = "postalCode_format") val postalCodeFormat: String,
    @ColumnInfo(name = "postalCode_regex") val postalCodeRegex: String,
    @ColumnInfo(name = "positionCounter") val positionCounter: Int,

    @ColumnInfo(name = "cca2") val cca2: String,
    @ColumnInfo(name = "ccn3") val ccn3: String,
    @ColumnInfo(name = "cca3") val cca3: String,
    @ColumnInfo(name = "cioc") val cioc: String,
    @ColumnInfo(name = "demonyms") val demonyms: String,
    @ColumnInfo(name = "coatOfArms") val coatOfArms: String,
    @ColumnInfo(name = "car") val car: String,




)



fun Country.toDatabase(): CountryEntity {
    return CountryEntity(
        nameCommon = name.common,
        nameOfficial = name.official,
        nativeName = name.nativeName ,
        tld = tld,
        independent = independent,
        status = status,
        unMember = unMember,
        currencies = currencies,
        capital = capital,
        region = region,
        subregion = subregion,
        languages = Json.encodeToString<Map<String,String>>(languages),
        latlng = latlng.map { e -> e.toString() },
        landlocked = landlocked,
        borders = borders,
        area = area,
        flag = flag,
        maps =  Json.encodeToString<Map<String,String>>(maps),
        population = population,
        gini =  Json.encodeToString<Map<String,String>>(gini),
        fifa = fifa,
        timezones = timezones,
        continents = continents,
        flags = flags,
        startOfWeek = startOfWeek,
        capitalInfoLatlng = capitalInfo.latlng.map { e -> e.toString() },
        postalCodeFormat = postalCode.format,
        postalCodeRegex = postalCode.regex,
        cca2=cca2,
        ccn3=ccn3,
        cca3=cca3,
        cioc=cioc,
        demonyms=demonyms,
        coatOfArms=coatOfArms,
        car=car,
        positionCounter = positionCounter
    )

}
