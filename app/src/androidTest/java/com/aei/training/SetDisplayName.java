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
 * Created by Eric on 5/13/2018.
 */
@LargeTest
@RunWith(AndroidJUnit4.class)

public class SetDisplayName {

    @Rule
    public ActivityTestRule<EditDisplayInfo> mChangeDisplayInfo = new ActivityTestRule<> (EditDisplayInfo.class);

    @Test
    public void ChangeDispalyInfo()
    {

        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompEditText = onView (
                allOf(withId(R.id.uNameField),
                        RegisterAccountTest.childAtPosition(
                                RegisterAccountTest.childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        appCompEditText.perform(replaceText("Bob"), closeSoftKeyboard());
        pressBack();

        ViewInteraction appCompatButton = onView(
                Matchers.allOf(withId(R.id.submitDisplayInfo), withText("Submit"),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        appCompatButton.perform(click());



    }
}
