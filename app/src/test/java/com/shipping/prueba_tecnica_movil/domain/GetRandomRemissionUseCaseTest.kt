package com.shipping.prueba_tecnica_movil.domain

import com.shipping.prueba_tecnica_movil.data.RemissionRepository
import com.shipping.prueba_tecnica_movil.domain.model.Quote
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class GetRandomRemissionUseCaseTest {
    @RelaxedMockK
    private lateinit var remissionRepository: RemissionRepository

    lateinit var getRandomRemissionUseCase: GetRandomRemissionUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        getRandomRemissionUseCase = GetRandomRemissionUseCase(remissionRepository)
    }

    @Test
    fun `when database is empty then return null`() = runBlocking {
        coEvery { remissionRepository.getAllCountriesFromDatabase() } returns emptyList()

        val response = getRandomQuoteUseCase()

        assert(response == null)
    }

    @Test
    fun `when database is not empty then return quote`() = runBlocking {
        val quoteList = listOf(Quote("Holi", "AristiDevs"))

        coEvery { remissionRepository.getAllCountriesFromDatabase() } returns quoteList

        val response = getRandomQuoteUseCase()

        assert(response == quoteList.first())
    }
}