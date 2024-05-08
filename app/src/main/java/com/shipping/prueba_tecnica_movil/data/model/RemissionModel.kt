package com.shipping.prueba_tecnica_movil.data.model


data class ApiResponse(
    val isError: Boolean,
    val data: List<CountryDto>
)
data class CountryDto(
    val name: String,
    val flagUrl: String,
    val capital: String,
    val officialLanguages: List<String>,
    val currency: String,
    val population: Long,
    val region: String,
    val subregion: String,
    val area: Double,
    val borders: List<String>,
    val maps: Map<String, String>,
    val tld: List<String>,
    val idd: Idd,
    val timeZone: List<String>,
    val demonym: String,
    val giniCoefficient: Double,
    val isoCodes: IsoCodes,
    val topLevelDomain: String,
    val callingCodes: List<String>,
    val postalCode: PostalCode
)

data class Idd(
    val root: String,
    val suffixes: List<String>
)

data class IsoCodes(
    val alpha2: String,
    val alpha3: String,
    val numeric: String
)

data class PostalCode(
    val format: String,
    val regex: String
)













