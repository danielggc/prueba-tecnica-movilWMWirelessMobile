package com.shipping.prueba_tecnica_movil

/*
@ExperimentalCoroutinesApi
class QuoteViewModelUnitTest {

    @RelaxedMockK
    private lateinit var getQuotesUseCase: GetRemissionModelUseCase

    @RelaxedMockK
    private lateinit var getRandomRemissionUseCase: GetRandomRemissionUseCase

    private lateinit var quoteViewModel: QuoteViewModel

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        quoteViewModel = QuoteViewModel(getQuotesUseCase, getRandomRemissionUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter() {
        Dispatchers.resetMain()
    }

    @Test
    fun `when randomQuoteUseCase return a quote set on the livedata`() = runTest {
        //Given
        val quote = Quote("Holi", "Aris")
        coEvery { getRandomRemissionUseCase() } returns quote

        //When
        quoteViewModel.randomQuote()

        //Then
        assert(quoteViewModel.quoteModel.value == quote)
    }

    @Test
    fun `if randomQuoteUseCase return null keep the last value`() = runTest{
        //Given
        val quote = Quote("Aris", "Aris")
        quoteViewModel.quoteModel.value = quote
        coEvery { getRandomRemissionUseCase() } returns null

        //When
        quoteViewModel.randomQuote()

        //Then
        assert(quoteViewModel.quoteModel.value == quote)
    }

    @Test
    fun `when viewmodel is created at the first time, get all quotes and set the first value`() = runTest{
        //Given
        val quote = listOf(Quote("Holi", "Aris"), Quote("Dame un like", "Otro Aris "))
        coEvery { getQuotesUseCase() } returns quote

        //When
        quoteViewModel.onCreate()

        //Then
        assert(quoteViewModel.quoteModel.value == quote.first())
    }
}

 */