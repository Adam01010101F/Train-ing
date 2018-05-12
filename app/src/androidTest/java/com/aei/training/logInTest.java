package com.aei.training;

import android.support.test.espresso.DataInteraction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static android.support.test.espresso.action.ViewActions.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;

import com.aei.training.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Random;

import static com.aei.training.RegisterAccountTest.childAtPosition;
import static org.hamcrest.Matchers.allOf;
//still don't understand the childPosition and content.
@LargeTest
@RunWith(AndroidJUnit4.class)
public class logInTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<> (MainActivity.class);

    @Test
    public void logInTest()
    {
        Random rand = new Random(System.currentTimeMillis());
        String username = genUser(rand);
        String password = genPass(rand);

        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.emailField),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                5),
                        isDisplayed()));
        appCompatEditText.perform(replaceText("123@me.com"), closeSoftKeyboard());
        pressBack();

        ViewInteraction appCompEditText2  = onView(
            allOf(withId(R.id.passField),
                    childAtPosition(
                            childAtPosition(
                                    withId(android.R.id.content),
                                    0),
                            5),
                    isDisplayed()));
        appCompEditText2.perform(replaceText("e3kjkedjwljd"), closeSoftKeyboard());
        pressBack();

        ViewInteraction appCompatButton = onView(
        allOf(withId(R.id.button), withText("Login"),
                childAtPosition(
                        childAtPosition(
                                withId(android.R.id.content),
                                0),
                        5),
                isDisplayed()));
        appCompatButton.perform(click());


//what test position mean, and I think our log in test case is done unless I should try with a other
        //things
    }
    private String genUser(Random rand){
        String user;
        user = rand.nextInt() + "@fake.com";
        return user;
    }

    private String genPass(Random rand){
        String pass;
        pass = rand.nextInt() + "string";
        return pass;
    }

}