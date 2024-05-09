package com.shipping.prueba_tecnica_movil.data.model


data class ApiResponse(
    val isError:         Boolean,
    val data:            List<CountryDto>
)
data class CountryDto(
    val name: NameDto?,
    val tld: List<String>?,
    val cca2: String?,
    val ccn3: String?,
    val cca3: String?,
    val cioc: String?,
    val independent: Boolean?,
    val status: String?,
    val unMember: Boolean?,
    val currencies: Map<String, CurrencyDto>?,
    val idd: IddDto?,
    val capital: List<String>?,
    val altSpellings: List<String>?,
    val region: String?,
    val subregion: String?,
    val languages: Map<String, String>?,
    val translations: Map<String, TranslationDto>?,
    val latlng: List<Double>?,
    val landlocked: Boolean?,
    val borders: List<String>?,
    val area: Double?,
    val demonyms: Map<String, DemonymDto>?,
    val flag: String?,
    val maps: Map<String, String>?,
    val population: Int?,
    val gini: Map<String, Double>?,
    val fifa: String?,
    val car: CarDto?,
    val timezones: List<String>?,
    val continents: List<String>?,
    val flags: FlagsDto?,
    val coatOfArms: CoatOfArmsDto?,
    val startOfWeek: String?,
    val capitalInfo: CapitalInfoDto?,
    val postalCode: PostalCodeDto?
)

data class NameDto(
    val common: String?,
    val official: String?,
    val nativeName: NativeNameDto?
)

data class NativeNameDto(
    val ron: RonDto?
)

data class RonDto(
    val official: String?,
    val common: String?
)

data class CurrencyDto(
    val name: String?,
    val symbol: String?
)

data class IddDto(
    val root: String?,
    val suffixes: List<String>?
)

data class TranslationDto(
    val official: String?,
    val common: String?
)

data class DemonymDto(
    val f: String?,
    val m: String?
)

data class CarDto(
    val signs: List<String>?,
    val side: String?
)

data class FlagsDto(
    val png: String?,
    val svg: String?,
    val alt: String?
)

data class CoatOfArmsDto(
    val png: String?,
    val svg: String?
)

data class CapitalInfoDto(
    val latlng: List<Double>?
)

data class PostalCodeDto(
    val format: String?,
    val regex: String?
)