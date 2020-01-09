package com.example.bike;

import android.os.AsyncTask;

public class FetchIncidents extends AsyncTask {
    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
    }

    @Override
    protected Object doInBackground(Object[] objects) {
        return NetworkUtils.getIncidentsInfo();
    }
}
