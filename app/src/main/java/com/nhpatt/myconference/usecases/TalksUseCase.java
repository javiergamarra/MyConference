package com.nhpatt.myconference.usecases;

import android.util.Log;

import com.google.gson.JsonArray;
import com.nhpatt.myconference.MyConferenceApp;
import com.nhpatt.myconference.entities.Talk;
import com.nhpatt.myconference.entities.TalkResponse;
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
public class TalksUseCase extends Job implements Runnable {

    public TalksUseCase() {
        super(new Params(1).requireNetwork());
    }


    @Override
    public void onRun() throws Throwable {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://private-5d28cf-talks.apiary-mock.com/")
                .build();

        TalkService service =
                restAdapter.create(TalkService.class);
        JsonArray talks = service.listTalks();
        Log.e(MyConferenceApp.TAG, talks.toString());

        try {
            List<Talk> oldTalks = Talk.listAll(Talk.class);

            Talk talk = new Talk("title", "room");
            talk.save();
        } catch (Exception e) {
            Log.e(MyConferenceApp.TAG, "Error!", e);
        }
        EventBus.getDefault().post(new TalkResponse(talks));
    }

    public void run() {
//        MyConferenceApp.getInstance().getJobManager().addJobInBackground(this);
        try {
            onRun();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

    @Override
    protected void onCancel() {

    }

    @Override
    public void onAdded() {

    }

    @Override
    protected boolean shouldReRunOnThrowable(Throwable throwable) {
        return false;
    }

    public void alternativeRun() {
        Executor executor = Executors.newFixedThreadPool(3);
        executor.execute(this);
    }
}
