package com.shipping.prueba_tecnica_movil.domain.model

import com.shipping.prueba_tecnica_movil.data.database.entities.CountryEntity
import com.shipping.prueba_tecnica_movil.data.model.CountryDto
import com.shipping.prueba_tecnica_movil.data.model.DemonymDto
import com.shipping.prueba_tecnica_movil.data.model.FlagsDto
import com.shipping.prueba_tecnica_movil.data.model.NameDto
import com.shipping.prueba_tecnica_movil.data.model.NativeNameDto
import com.shipping.prueba_tecnica_movil.data.model.PostalCodeDto
import java.io.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.json.JSONObject


data class Country(
    val name: Name,
    val tld: List<String>,
    val independent: Boolean,
    val status: String,
    val unMember: Boolean,
    val currencies: String,
    val capital: List<String>,
    val region: String,
    val subregion: String,
    val languages: Map<String, String>,
    val latlng: List<Double>,
    val landlocked: Boolean,
    val borders: List<String>,
    val area: Double,
    val flag: String,
    val maps: Map<String, String>,
    val population: Int,
    val gini: Map<String, Double>,
    val fifa: String,
    val timezones: List<String>,
    val continents: List<String>,
    val flags: String,
    val startOfWeek: String,
    val capitalInfo: CapitalInfo,
    val postalCode: PostalCode,
    val positionCounter:Int,
    var statusExpandable:Boolean = false,
    val cca2: String,
    val ccn3: String,
    val cca3: String,
    val cioc: String,
    val demonyms: String,
    val coatOfArms:String,
    val car:String,

    ): Serializable




data class Name(
    val common: String = "",
    val official: String = "",
    val nativeName: String ="",

): Serializable

@kotlinx.serialization.Serializable
data class NativeName(
    val ron: Ron
): Serializable
@kotlinx.serialization.Serializable
data class Ron(
    val official: String,
    val common: String
): Serializable
data class CoatOfArms(
    val png: String = "",
    val svg: String = ""
): Serializable
data class CapitalInfo(
    val latlng: List<Double> = emptyList()
): Serializable
data class PostalCode(
    val format: String = "",
    val regex: String = ""
): Serializable



private fun validateName(name: NameDto?): Name =
    name?.let { (common, official ,nativeName ) ->
        Name(common ?: "", official ?: "" ,  validateNativeName( nativeName ).toString() )
    } ?: Name("","","" )


private fun validateNativeName( name:NativeNameDto? ) :String =
    name?.let { data -> data.ron?.let{ ( d1 ,d2 ) -> " ${d1?: ""}  , ${d2?: ""} " } }
        ?: ""
private fun<A> validateList( list: List<A>?) :List<A> =
    list ?: emptyList()

private fun validarString(valor: String?): String {
    return valor ?: "none"
}

private fun validarBoolean(valor: Boolean?): Boolean {
    return valor ?: false
}

private fun validarInt(valor: Int?): Int {
    return valor ?: 0
}
private fun validarDouble(valor: Double?): Double {
    return valor ?: 0.0
}
private fun validarPostalCode(postalCode: PostalCodeDto?): PostalCode {
    return postalCode?.let {
        PostalCode(
            format = it.format ?: "",
            regex = it.regex ?: ""
        )
    } ?: PostalCode("", "")
}
private fun <K, V> validarMap(mapa: Map<K, V>?): Map<K, V> {
    return mapa ?: emptyMap()
}

private fun  validateDataFLags( flags:FlagsDto? ) : String {
    if (flags == null)  return  "none"
    else  return flags.png ?: flags.svg ?: flags.alt ?: "none"
}

fun CountryDto.toDomain( index :Int ): Country {
    return Country(
        name = validateName(name),
        tld = validateList(tld),
        independent = validarBoolean( independent ) ,
        status = validarString( status),
        unMember = validarBoolean( unMember) ,
        currencies = validarString( validarMap(currencies).keys.toList().firstOrNull() ?: "none") ,
        capital = validateList( capital ),
        region = validarString(region ),
        subregion = validarString(subregion),
        languages = validarMap( languages) ,
        latlng = validateList(latlng),
        landlocked = validarBoolean(landlocked),
        borders = validateList(borders) ,
        area = validarDouble(area),
        flag = validarString(flag),
        maps = validarMap( maps ) ,
        population = validarInt(population),
        gini = validarMap( gini ),
        fifa = validarString(fifa),
        timezones = validateList(timezones),
        continents = validateList(continents),
        flags = validateDataFLags(flags),
        startOfWeek = validarString(startOfWeek),
        capitalInfo = CapitalInfo(validateList(capitalInfo?.latlng ?: emptyList() ) ),
        postalCode = validarPostalCode(postalCode),
        cca2    = validarString(cca2) ,
        ccn3    = validarString(ccn3),
        cca3    = validarString(cca3),
        cioc        = validarString(cioc) ,
        demonyms    = validarString( demonyms?.values?.map { e -> " f = ${e.f}, m = ${e.m} "  }.toString() ?: " " ),
        coatOfArms  = validarString(coatOfArms?.png ?: coatOfArms?.svg ?: " " ),
        car         = validarString(car?.side ?: "" ),
        positionCounter = index,
    )
}
fun CountryEntity.toDomain(): Country {
    return Country(
        name = Name(
            common = nameCommon,
            official = nameOfficial,
            nativeName = nativeName
        ),
        tld = tld,
        independent = independent,
        status = status,
        unMember = unMember,
        currencies = currencies,
        capital = capital,
        region = region,
        subregion = subregion,
        languages = convertStringToMap(languages),
        latlng = latlng.map { e -> e.toDouble() },
        landlocked = landlocked,
        borders = borders,
        area = area,
        flag = flag,
        maps = convertStringToMap(maps),
        population = population,
        gini = Json.decodeFromString<Map<String,Double>>(gini),
        fifa = fifa,
        timezones = timezones,
        continents = continents,
        flags = flags,
        startOfWeek = startOfWeek,
        capitalInfo = CapitalInfo( capitalInfoLatlng.map { e -> e.toDouble() } ) ,
        postalCode = PostalCode(format = postalCodeFormat, regex = postalCodeRegex),
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
private fun capitalInfoEntityToDomain(  capitalInfoLatlng :String ):CapitalInfo =
    if ( capitalInfoLatlng.isNotBlank() )
          CapitalInfo( latlng = capitalInfoLatlng.split(",").map { it.toDouble() } )
    else  CapitalInfo(latlng =  emptyList() )

private fun convertStringToMap(data: String): Map<String, String> =
 Json.decodeFromString<Map<String,String>>(data)


