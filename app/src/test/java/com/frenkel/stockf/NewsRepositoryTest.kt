package com.frenkel.stockf

import com.frenkel.data.NewsRepository
import com.frenkel.data.NewsRepositoryImpl
import com.frenkel.data.models.RequestResult
import com.frenkel.database.StocksDatabase
import com.frenkel.database.models.NewsDbo
import com.frenkel.finnhub_client.FinnhubApi
import com.frenkel.finnhub_client.models.News
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

@OptIn(ExperimentalCoroutinesApi::class)
class NewsRepositoryTest {

    private val testDispatcher = UnconfinedTestDispatcher()
    private lateinit var finnhubApi: FinnhubApi
    private lateinit var db: StocksDatabase
    private lateinit var repository: NewsRepository

    @Before
    fun setUp() {
        finnhubApi = mock()
        db = mock()
        repository = NewsRepositoryImpl(finnhubApi, db)
    }

    @Test
    fun getMarketNews_Test() = runTest(testDispatcher) {
        val dbData = (1..10).map { NewsDbo(
            id = it.toLong(),
            category = "tech",
            timestamp = 0,
            headline = "heeadline",
            imageUrl = "url",
            related = "",
            source = "source",
            summary = "summary",
            url = "url"
        ) }

        val apiData = (1..10).map { News(
            id = it.toLong(),
            category = "",
            timestamp = 0,
            headline = "",
            imageUrl = "",
            related = "",
            source = "",
            summary = "",
            url = ""
        ) }

        `when`(db.getMarketNews()).thenReturn(dbData)
        `when`(finnhubApi.fetchMarketNews()).thenReturn(Result.success(apiData))

        val requestResults = repository.getMarketNews().toList()

        verify(db, times(1)).getMarketNews()
        verify(finnhubApi, times(1)).fetchMarketNews()
        assertThat(requestResults[0]).isInstanceOf(RequestResult.InProgress::class.java)
        assertThat(requestResults[1]).isInstanceOf(RequestResult.InProgress::class.java)
        assertThat(requestResults[2]).isInstanceOf(RequestResult.Success::class.java)
        assertThat(requestResults[0].data).isNull()
        assertThat(requestResults[1].data).isNotEmpty()
        assertThat(requestResults[2].data).isNotEmpty()
    }
}