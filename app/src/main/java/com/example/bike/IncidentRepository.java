package com.example.bike;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class IncidentRepository {
    private IncidentDao incidentDao;
    private LiveData<List<Incident>> mAllIncidents;

    IncidentRepository(Application application) {
        IncidentRoomDatabase db = IncidentRoomDatabase.getDatabase(application);
        incidentDao = db.incidentDao();
        mAllIncidents = incidentDao.getAllWords();
    }

    LiveData<List<Incident>> getAllWords() {
        return mAllIncidents;
    }

    public void insert (Incident word) {
        new insertAsyncTask(incidentDao).execute(word);
    }

    private static class insertAsyncTask extends AsyncTask<Incident, Void, Void> {

        private IncidentDao mAsyncTaskDao;

        insertAsyncTask(IncidentDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Incident... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}

