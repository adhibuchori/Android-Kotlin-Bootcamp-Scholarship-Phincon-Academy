package com.adhibuchori.narumi.ui.profile

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.adhibuchori.narumi.domain.auth.IAuthRepository
import com.adhibuchori.narumi.domain.auth.UserModel
import com.adhibuchori.narumi.ui.main.profile.ProfileViewModel
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

class ProfileViewModelTest {
    @get:Rule
    val instantExecutorRule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var authRepository: IAuthRepository

    private lateinit var viewModel: ProfileViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        viewModel = ProfileViewModel(authRepository)
    }


    // Fetching profile data updates userData LiveData with current user information
    @Test
    fun fetch_profile_data_updates_user_data() {
        // Arrange
        val userModel = UserModel(username = "John Doe", email = "john.doe@example.com")
        `when`(authRepository.getCurrentUser()).thenReturn(userModel)
        val viewModel = ProfileViewModel(authRepository)

        // Act
        viewModel.fetchProfileData()

        // Assert
        assertEquals(userModel, viewModel.userData.getOrAwaitValue(100))
    }

    // fetchProfileData handles null or invalid user data gracefully
    @Test
    fun fetch_profile_data_handles_null_user_data() {
        // Arrange
        `when`(authRepository.getCurrentUser()).thenReturn(null)
        val viewModel = ProfileViewModel(authRepository)

        // Act
        viewModel.fetchProfileData()

        // Assert
        assertNull(viewModel.userData.value)
    }


}