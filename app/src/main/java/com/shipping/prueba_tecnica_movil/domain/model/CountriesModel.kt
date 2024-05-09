package com.shipping.prueba_tecnica_movil.domain.model

import com.google.gson.JsonObject
import com.shipping.prueba_tecnica_movil.data.database.entities.CountryEntity
import com.shipping.prueba_tecnica_movil.data.model.CountryDto
import com.shipping.prueba_tecnica_movil.data.model.FlagsDto
import com.shipping.prueba_tecnica_movil.data.model.NameDto
import com.shipping.prueba_tecnica_movil.data.model.PostalCodeDto
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
)


data class Name(
    val common: String = "",
    val official: String = ""
)

data class CoatOfArms(
    val png: String = "",
    val svg: String = ""
)

data class CapitalInfo(
    val latlng: List<Double> = emptyList()
)

data class PostalCode(
    val format: String = "",
    val regex: String = ""
)



private fun validateName(name: NameDto?): Name =
    name?.let { (common, official) ->
        Name(common ?: "", official ?: "")
    } ?: Name()
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
        positionCounter = index,
    )
}
fun CountryEntity.toDomain(): Country {
    return Country(
        name = Name(
            common = nameCommon,
            official = nameOfficial
        ),
        tld = tld,
        independent = independent,
        status = status,
        unMember = unMember,
        currencies = currencies,
        capital = listOf(capital),
        region = region,
        subregion = subregion,
        languages = convertStringToMap(languages),
        latlng = latlng.split(",").map { it.toDouble() },
        landlocked = landlocked,
        borders = borders.split(","),
        area = area,
        flag = flag,
        maps = convertStringToMap(maps),
        population = population,
        gini = convertStringToMap(gini).mapValues { entry: Map.Entry<String, String> ->  entry.value.toDouble() },
        fifa = fifa,
        timezones = timezones.split(","),
        continents = continents.split(","),
        flags = flags,
        startOfWeek = startOfWeek,
        capitalInfo = capitalInfoEntityToDomain( capitalInfoLatlng ) ,
        postalCode = PostalCode(format = postalCodeFormat, regex = postalCodeRegex),
        positionCounter = positionCounter
    )
}
private fun capitalInfoEntityToDomain(  capitalInfoLatlng :String ):CapitalInfo =
    if ( capitalInfoLatlng.isNotBlank() )
          CapitalInfo( latlng = capitalInfoLatlng.split(",").map { it.toDouble() } )
    else  CapitalInfo(latlng =  emptyList() )

private fun convertStringToMap(string: String): Map<String, String> {
    val jsonObject = JSONObject(string)
    val map = mutableMapOf<String, String>()
    val keys = jsonObject.keys()
    while (keys.hasNext()) {
        val key = keys.next()
        map[key] = jsonObject.getString(key)
    }
    return map
}

fun JSONObject.toMap(): Map<String, String> {
    val map = mutableMapOf<String, String>()
    val keys = this.keys()
    while (keys.hasNext()) {
        val key = keys.next()
        val value = this.getString(key)
        map[key] = value
    }
    return map
}
