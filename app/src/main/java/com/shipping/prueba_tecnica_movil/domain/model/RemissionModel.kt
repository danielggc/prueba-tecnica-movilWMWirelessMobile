package com.shipping.prueba_tecnica_movil.domain.model

import com.shipping.prueba_tecnica_movil.data.database.entities.CountryEntity
import com.shipping.prueba_tecnica_movil.data.model.CountryDto


data class Country(
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
    val idd: Idd
)

data class Idd(
    val root: String,
    val suffixes: List<String>
)

fun CountryDto.toDomain(): Country {
    return Country(
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
        idd = Idd( root = idd.root, suffixes = idd.suffixes)
    )
}

fun CountryEntity.toDomain(): Country {
    return Country(
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
        idd = Idd( root = idd.root, suffixes = idd.suffixes)
    )
}