package com.adhibuchori.narumi.ui.checkout

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.adhibuchori.narumi.domain.transaction.ITransactionRepository
import com.adhibuchori.narumi.domain.trip.ITripRepository
import com.adhibuchori.narumi.domain.trip.TripModel
import com.adhibuchori.narumi.ui.main.checkout.CheckoutViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class CheckoutViewModelTest {
    @get:Rule
    val instantExecutorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var tripRepository: ITripRepository

    @Mock
    private lateinit var transactionRepository: ITransactionRepository

    private lateinit var viewModel: CheckoutViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = CheckoutViewModel(tripRepository, transactionRepository)
    }


    // Fetch trip data by ID and update LiveData
    @Test
    fun fetch_trip_by_id_updates_live_data() {
        // Arrange
        val tripId = "123"
        val tripModel = TripModel(
            uuid = tripId,
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
        `when`(tripRepository.fetchTripById(tripId)).thenReturn(tripModel)

        // Act
        viewModel.fetchTripById(tripId)
        Thread.sleep(100) // Wait for coroutine to finish

        // Assert
        assertEquals(tripModel, viewModel.tripData.value)
    }

    // Fetch trip by ID when trip does not exist
    @Test
    fun fetch_trip_by_id_when_trip_does_not_exist() {
        // Arrange
        val tripId = "nonexistent"
        `when`(tripRepository.fetchTripById(tripId)).thenReturn(null)

        // Act
        viewModel.fetchTripById(tripId)
        Thread.sleep(100) // Wait for coroutine to finish

        // Assert
        assertNull(viewModel.tripData.value)
    }
}