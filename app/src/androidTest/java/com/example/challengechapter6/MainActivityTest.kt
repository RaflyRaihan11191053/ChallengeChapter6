package com.example.challengechapter6


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun mainActivityTest() {
        Thread.sleep(3000)
        val materialTextView = onView(
            allOf(
                withId(R.id.tvGoRegister),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.fragmentContainerView),
                        0
                    ),
                    8
                ),
                isDisplayed()
            )
        )
        materialTextView.perform(click())

        val textInputEditText = onView(
            allOf(
                withId(R.id.etUsernameRegister),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.tilUsernameRegister),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText.perform(replaceText("rafly"), closeSoftKeyboard())

        val textInputEditText2 = onView(
            allOf(
                withId(R.id.etEmailRegister),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.tilEmailRegister),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText2.perform(replaceText("aa"), closeSoftKeyboard())

        val textInputEditText3 = onView(
            allOf(
                withId(R.id.etPasswordRegister),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.tilPasswordRegister),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText3.perform(replaceText("123"), closeSoftKeyboard())

        val textInputEditText4 = onView(
            allOf(
                withId(R.id.etConfirmPassword),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.tilConfirmPassword),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText4.perform(replaceText("123"), closeSoftKeyboard())

        val textInputEditText5 = onView(
            allOf(
                withId(R.id.etConfirmPassword), withText("123"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.tilConfirmPassword),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText5.perform(pressImeActionButton())

        val materialButton = onView(
            allOf(
                withId(R.id.btnRegister), withText("Register"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.fragmentContainerView),
                        0
                    ),
                    8
                ),
                isDisplayed()
            )
        )
        materialButton.perform(click())

        val textInputEditText6 = onView(
            allOf(
                withId(R.id.etUsernameLogin),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.tilUsernameLogin),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText6.perform(replaceText("rafly"), closeSoftKeyboard())

        val textInputEditText7 = onView(
            allOf(
                withId(R.id.etPasswordLogin),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.tilPasswordLogin),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText7.perform(replaceText("123"), closeSoftKeyboard())

        val materialButton2 = onView(
            allOf(
                withId(R.id.btnLogin), withText("Login"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.fragmentContainerView),
                        0
                    ),
                    6
                ),
                isDisplayed()
            )
        )
        materialButton2.perform(click())
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
