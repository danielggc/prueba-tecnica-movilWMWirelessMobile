package com.shipping.prueba_tecnica_movil.domain

import com.shipping.prueba_tecnica_movil.data.RemissionRepository
import com.shipping.prueba_tecnica_movil.domain.model.Quote
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetQuotesUseCaseTest {

    @RelaxedMockK
    private lateinit var remissionRepository: RemissionRepository

    lateinit var getQuotesUseCase: GetCountryModelUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getQuotesUseCase = GetCountryModelUseCase(remissionRepository)
    }

    @Test
    fun `when the api doesnt return anything then get values from database`() = runBlocking {
        //Given
        coEvery { remissionRepository.getAllCountriesFromApi() } returns emptyList()

        //When
        getQuotesUseCase()

        //Then
        coVerify(exactly = 1) { remissionRepository.getAllCountriesFromDatabase() }
    }

    @Test
    fun `when the api return something then get values from api`() = runBlocking {
        //Given
        val myList = listOf(Quote("Déjame un comentario", "AristiDevs"))
        coEvery { remissionRepository.getAllCountriesFromApi() } returns myList

        //When
        val response = getQuotesUseCase()

        //Then
        coVerify(exactly = 1) { remissionRepository.clearAllCountries() }
        coVerify(exactly = 1) { remissionRepository.insertCountry(any()) }
        coVerify(exactly = 0) { remissionRepository.getAllCountriesFromDatabase() }
        assert(response == myList)
    }
}