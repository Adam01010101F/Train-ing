package com.aei.training;

import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.aei.training.RegisterAccountTest.childAtPosition;
import static org.hamcrest.CoreMatchers.allOf;

/**
 * Created by Eric on 5/12/2018.
 */

//still don't understand the childPosition and content.
@LargeTest
@RunWith(AndroidJUnit4.class)
public class Change_Password {

    @Rule
    public ActivityTestRule<ChangePassword> mChangePassWordTestRule = new ActivityTestRule<> (ChangePassword.class);

    @Test
    public void ChangePassword()
    {
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction  appCompEditText = onView (
                allOf(withId(R.id.passField),
                        RegisterAccountTest.childAtPosition(
                                RegisterAccountTest.childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                                        isDisplayed()));
        appCompEditText.perform(replaceText("test"), closeSoftKeyboard());
        pressBack();

        ViewInteraction  appCompEditText2 = onView (
                allOf(withId(R.id.confPassField),
                        RegisterAccountTest.childAtPosition(
                                RegisterAccountTest.childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        appCompEditText2.perform(replaceText("test"), closeSoftKeyboard());
        pressBack();

        ViewInteraction appCompatButton = onView(
                Matchers.allOf(withId(R.id.chngPassButton), withText("Submit"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        appCompatButton.perform(click());


    }
    }

