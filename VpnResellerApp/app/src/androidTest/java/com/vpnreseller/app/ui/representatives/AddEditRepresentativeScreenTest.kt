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
class AddEditRepresentativeScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun addEditRepresentativeScreen_displaysFields() {
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.add_representative_title))
            .assertIsDisplayed()

        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.label_full_name))
            .assertIsDisplayed()

        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.label_admin_username_marzban))
            .assertIsDisplayed()

        // Additional field checks can be added here
    }
}
