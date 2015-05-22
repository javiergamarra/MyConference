package com.nhpatt.myconference.usecases;

import android.util.Log;

import com.google.gson.JsonArray;
import com.nhpatt.myconference.MyConferenceApp;
import com.nhpatt.myconference.entities.Talk;
import com.nhpatt.myconference.entities.TalkEvent;
import com.nhpatt.myconference.network.TalkService;
import com.path.android.jobqueue.Job;
import com.path.android.jobqueue.Params;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import de.greenrobot.event.EventBus;
import retrofit.RestAdapter;

/**
 * @author Javier Gamarra
 */
public class TalksUseCase implements Runnable {

    public void run() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://private-5d28cf-talks.apiary-mock.com/")
                .build();

        TalkService service =
                restAdapter.create(TalkService.class);
        JsonArray talks = service.listTalks();
        Log.e(MyConferenceApp.TAG, talks.toString());

        EventBus.getDefault().post(new TalkEvent(talks));
    }

    public void alternativeRun() {
        Executor executor = Executors.newFixedThreadPool(3);
        executor.execute(this);
    }
}
