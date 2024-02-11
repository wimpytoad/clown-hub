package com.toadfrogson.clownhub.viewmodel

import app.cash.turbine.test
import com.toadfrogson.clownhub.data.model.JokeEntry
import com.toadfrogson.clownhub.data.model.JokeModel
import com.toadfrogson.clownhub.data.model.exception.ClownException
import com.toadfrogson.clownhub.data.model.response.ApiResponse
import com.toadfrogson.clownhub.data.repo.JokesRepo
import com.toadfrogson.clownhub.presentation.model.JokeType
import com.toadfrogson.clownhub.presentation.viewmodel.MainViewModel
import com.toadfrogson.clownhub.presentation.viewmodel.UiEvents
import com.toadfrogson.clownhub.viewmodel.MainViewModelTest.MockedData.jokeModelList
import com.toadfrogson.clownhub.viewmodel.MainViewModelTest.MockedData.jokesModel2
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.mockito.kotlin.doAnswer
import org.mockito.kotlin.stub

@RunWith(JUnit4::class)
class MainViewModelTest {

    @Mock
    lateinit var jokesRepo: JokesRepo

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        viewModel = MainViewModel(jokesRepo)
    }

    @Test
    fun `Given the viewModel loadContent function is called, then state holds data`() = runTest {
        jokesRepo.stub {
            onBlocking { fetchJokes() } doAnswer {
                ApiResponse.Success(jokeModelList)
            }
        }


        viewModel.loadContent()


        viewModel.uiState.test {
            assertEquals(jokeModelList, awaitItem().content)
        }
    }

    @Test
    fun `Given repo returns error response, then ui events emit error snackbar`() = runTest {
        jokesRepo.stub {
            onBlocking { fetchJokes() } doAnswer {
                ApiResponse.Failure(
                    "No content found in cache",
                    ClownException.NoContentFoundInCache
                )
            }
        }
        viewModel.uiEvents.test {
            viewModel.loadContent()
            val item = awaitItem()
            assertEquals(UiEvents.ErrorSnackbarEvent, item)
        }
    }

    @Test
    fun `Given user filters joke content, then viewModel changes state`() = runTest {
        jokesRepo.stub {
            onBlocking { fetchJokes() } doAnswer {
                ApiResponse.Success(jokeModelList)
            }
        }

        viewModel.loadContent()

        viewModel.uiState.test {
            assertEquals(jokeModelList, awaitItem().content)
        }

        viewModel.filterContent(JokeType.Miscellaneous)

        viewModel.uiState.test {
            assertEquals(listOf(jokesModel2), awaitItem().content)
        }

        viewModel.filterContent(JokeType.AllJokes)

        viewModel.uiState.test {
            assertEquals(jokeModelList, awaitItem().content)
        }
    }


    object MockedData {
        private val jokeEntry1 = JokeEntry(
            category = "Programming",
            type = "single",
            joke = "Generic joke about Java slow muh duh",
            flags = emptyMap(),
            id = 123,
            safe = true,
            lang = "english"
        )

        private val jokeEntry2 = JokeEntry(
            category = "Misc",
            type = "single",
            joke = "Generic joke about chicken crossing the road",
            flags = emptyMap(),
            id = 124,
            safe = true,
            lang = "english"
        )
        val jokeModel1 = JokeModel.mapFromEntity(jokeEntry1)
        val jokesModel2 = JokeModel.mapFromEntity(jokeEntry2)
        val jokeModelList = listOf(jokeModel1, jokesModel2)

    }
}
