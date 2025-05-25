package com.vpnreseller.app.ui.representatives

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.vpnreseller.app.MainActivity
import com.vpnreseller.app.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RepresentativeListScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun representativeListScreen_displaysTitleAndFab() {
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.representatives_title))
            .assertIsDisplayed()

        composeTestRule.onNodeWithContentDescription(composeTestRule.activity.getString(R.string.add_representative_fab_description))
            .assertIsDisplayed()
    }

    @Test
    fun searchField_acceptsInput() {
        val searchHint = composeTestRule.activity.getString(R.string.search_representatives_hint)
        val testQuery = "Test"

        composeTestRule.onNodeWithText(searchHint)
            .performTextInput(testQuery)
            .assertTextEquals(testQuery)
    }

    // Additional tests can be added for list items, navigation, empty state, etc.
}
