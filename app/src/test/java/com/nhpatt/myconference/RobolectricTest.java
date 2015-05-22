package com.nhpatt.myconference;

import android.view.View;

import com.nhpatt.myconference.activities.AgendaActivity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.annotation.Config;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;


/**
 * @author Javier Gamarra
 */
@RunWith(RobolectricManifestTestRunner.class)
@Config(constants = BuildConfig.class, emulateSdk = 18)
public class RobolectricTest {

    @Test
    public void shouldCallUserSessionDomain() throws Exception {
        AgendaActivity activity = Robolectric.setupActivity(AgendaActivity.class);
        View view = activity.findViewById(R.id.title_day_1);

        assertThat(view, notNullValue());
    }

    //hide & show
    //preferences
    //"open" an activity
    //execute a lifecycle event
}
