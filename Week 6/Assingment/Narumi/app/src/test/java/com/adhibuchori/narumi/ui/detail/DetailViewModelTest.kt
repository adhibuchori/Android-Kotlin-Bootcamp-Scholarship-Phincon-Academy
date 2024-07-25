package com.adhibuchori.narumi.ui.detail

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.adhibuchori.narumi.domain.trip.ITripRepository
import com.adhibuchori.narumi.domain.trip.TripModel
import com.adhibuchori.narumi.ui.main.detail.DetailViewModel
import com.adhibuchori.narumi.util.getOrAwaitValue
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class DetailViewModelTest {
    @get:Rule
    val instantExecutorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var tripRepository: ITripRepository

    private lateinit var viewModel: DetailViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = DetailViewModel(tripRepository)
    }


    // fetchTripById successfully retrieves a trip and updates tripData
    @Test
    fun fetch_trip_by_id_success() {
        // Arrange
        val tripModel = TripModel(
            uuid = "123",
            tripImage = 0,
            tripName = "Trip Name",
            tripLocation = "Location",
            tripDate = "2023-10-10",
            tripOrderDate = "2023-10-01",
            tripDuration = "7 days",
            tripCost = 1000,
            tripDescription = "Description",
            tripCategory = "Category"
        )
        `when`(tripRepository.fetchTripById("123")).thenReturn(tripModel)

        // Act
        viewModel.fetchTripById("123")

        // Assert
        assertEquals(tripModel, viewModel.tripData.getOrAwaitValue(100))
    }

    // fetchTripById is called with an invalid or non-existent ID
    @Test
    fun fetch_trip_by_id_invalid_id() {
        // Arrange
        `when`(tripRepository.fetchTripById("invalid_id")).thenReturn(null)

        // Act
        viewModel.fetchTripById("invalid_id")

        // Assert
        assertNull(viewModel.tripData.value)
    }
}