package com.aei.training;

import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.pressImeActionButton;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.aei.training.RegisterAccountTest.childAtPosition;
import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.Matchers.is;

/**
 * Created by Eric on 5/12/2018.
 */

//still don't understand the childPosition and content.
@LargeTest
@RunWith(AndroidJUnit4.class)
public class Change_Password {

    @Rule
    public ActivityTestRule<MainActivity> mMainActivityTestRule = new ActivityTestRule<> (MainActivity.class);
//    @Rule
//    public ActivityTestRule<ChangePassword> mChangePassWordTestRule = new ActivityTestRule<> (ChangePassword.class);
    @Test
    public void ChangePassword() {


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ViewInteraction appCompatEditText1 = onView(
                allOf(withId(R.id.emailField),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText1.perform(click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.emailField), withText("ericaguirre@me.com"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatEditText2.perform(pressImeActionButton());

        ViewInteraction appCompEditText3 = onView(
                allOf(withId(R.id.passField),
                        RegisterAccountTest.childAtPosition(
                                RegisterAccountTest.childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompEditText3.perform(replaceText("test12"), closeSoftKeyboard());
        pressBack();



        ViewInteraction appCompatEditText4 = onView(
                Matchers.allOf(withId(R.id.passField), withText("test12"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText4.perform(pressImeActionButton());

        ViewInteraction appCompatButton = onView(
                Matchers.allOf(withId(R.id.button), withText("Log In"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        appCompatButton.perform(click());


        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction toolbar = onView(
                childAtPosition(
                        childAtPosition(
                                withClassName(is("android.widget.ScrollView")),
                                0),
                        0));
        toolbar.perform(scrollTo(), click());

        ViewInteraction navigationMenuItemView = onView(
                Matchers.allOf(childAtPosition(
                        Matchers.allOf(withId(R.id.design_navigation_view),
                                childAtPosition(
                                        withClassName(is("android.support.design.widget.NavigationView")),
                                        0)),
                        3),
                        isDisplayed()));
        navigationMenuItemView.perform(click());

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatEditText6 = onView(
                Matchers.allOf(withId(R.id.passField),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText6.perform(click());

        ViewInteraction appCompEditText7 = onView(
                allOf(withId(R.id.passField),
                        RegisterAccountTest.childAtPosition(
                                RegisterAccountTest.childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompEditText7.perform(replaceText("NewPass12"), closeSoftKeyboard());
        pressBack();

        ViewInteraction appCompatEditText8 = onView(
                Matchers.allOf(withId(R.id.passField), withText("NewPass12"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText8.perform(pressImeActionButton());

        ViewInteraction appCompatEditText9 = onView(
                Matchers.allOf(withId(R.id.confPassField),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompatEditText9.perform(click());

        ViewInteraction appCompEditText10 = onView(
                allOf(withId(R.id.confPassField),
                        RegisterAccountTest.childAtPosition(
                                RegisterAccountTest.childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()));
        appCompEditText10.perform(replaceText("NewPass12"), closeSoftKeyboard());
        pressBack();

        ViewInteraction appCompatEditText11 = onView(
                Matchers.allOf(withId(R.id.confPassField), withText("NewPass12"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatEditText11.perform(pressImeActionButton());

        ViewInteraction appCompatButton2 = onView(
                Matchers.allOf(withId(R.id.chngPassButton), withText("Submit"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                3),
                        isDisplayed()));
        appCompatButton2.perform(click());
    }

        private static Matcher<View> childAtPosition(
        final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }

    }


