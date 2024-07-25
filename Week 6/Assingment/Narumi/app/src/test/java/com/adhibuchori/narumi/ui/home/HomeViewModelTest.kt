package com.adhibuchori.narumi.ui.home

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.adhibuchori.narumi.domain.auth.IAuthRepository
import com.adhibuchori.narumi.domain.auth.UserModel
import com.adhibuchori.narumi.domain.trip.ITripRepository
import com.adhibuchori.narumi.domain.trip.TripModel
import com.adhibuchori.narumi.ui.main.home.HomeViewModel
import com.adhibuchori.narumi.util.getOrAwaitValue
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class HomeViewModelTest {
    @get:Rule
    val instantExecutorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var authRepository: IAuthRepository

    @Mock
    private lateinit var tripRepository: ITripRepository

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = HomeViewModel(tripRepository, authRepository)
    }

    // Fetching all data populates popular, recommended, all trips, and user data lists
    @Test
    fun fetch_all_data_populates_all_lists() {
        // Arrange
        val trips = listOf(TripModel("1", 0, "Trip1", "Location1", 100, "OrderDate1", "Duration1", "Date1", "Description1", "Popular"))
        val user = UserModel("User1", "user1@example.com")

        `when`(tripRepository.fetchTrips()).thenReturn(trips)
        `when`(authRepository.getCurrentUser()).thenReturn(user)

        // Act
        viewModel.fetchAllData()

        // Assert
        assertEquals(trips, viewModel.allTrips.getOrAwaitValue(100))
        assertEquals(user, viewModel.userData.getOrAwaitValue(100))
    }

    // Fetching data when repositories return empty lists
    @Test
    fun fetch_data_with_empty_repositories() {
        // Arrange
        val emptyTrips = emptyList<TripModel>()
        val user = UserModel("User1", "user1@example.com")

        `when`(tripRepository.fetchTrips()).thenReturn(emptyTrips)
        `when`(authRepository.getCurrentUser()).thenReturn(user)

        // Act
        viewModel.fetchAllData()

        // Assert
        assertTrue(viewModel.allTrips.getOrAwaitValue(100)!!.isEmpty())
        assertEquals(user, viewModel.userData.getOrAwaitValue(100))
    }

}