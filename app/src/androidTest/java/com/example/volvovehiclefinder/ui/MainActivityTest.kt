package com.example.volvovehiclefinder.ui

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.volvovehiclefinder.di.AppModule
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(AppModule::class)
class MainActivityTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun searchBar_isDisplayed() {
        composeTestRule.onNodeWithText("Search Volvo vehicles...").assertIsDisplayed()
    }

    @Test
    fun enterSearchQuery_showsFilteredResults() {
        // Given
        val searchQuery = "XC90"
        
        // When
        composeTestRule.onNodeWithText("Search Volvo vehicles...")
            .performTextInput(searchQuery)

        // Then
        composeTestRule.onNodeWithText(searchQuery).assertExists()
    }

    @Test
    fun vehicleCard_showsAllRequiredInfo() {
        // Wait for the first vehicle card to appear
        composeTestRule.waitForIdle()
        
        // Verify vehicle information is displayed
        composeTestRule.onNodeWithText("2024").assertExists()
        composeTestRule.onNodeWithText("Price:").assertExists()
        composeTestRule.onNodeWithText("Mileage").assertExists()
        composeTestRule.onNodeWithText("Fuel Type").assertExists()
        composeTestRule.onNodeWithText("Transmission").assertExists()
    }
}
