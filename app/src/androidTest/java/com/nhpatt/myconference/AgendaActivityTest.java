package com.nhpatt.myconference;

import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;

import com.nhpatt.myconference.activities.AgendaActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class AgendaActivityTest {

    @Rule
    public ActivityTestRule<AgendaActivity> mActivityRule = new ActivityTestRule(AgendaActivity.class);

    @Test
    public void testDrawer() {

        ViewInteraction usernameInput = onView(withId(R.id.drawer_frame));

        usernameInput.perform(click());
    }
}
