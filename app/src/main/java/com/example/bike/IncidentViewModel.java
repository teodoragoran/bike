package com.example.bike;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class IncidentViewModel extends AndroidViewModel {
    private IncidentRepository mRepository;

    private LiveData<List<Incident>> mAllIncidents;

    public IncidentViewModel (Application application) {
        super(application);
        mRepository = new IncidentRepository(application);
        mAllIncidents = mRepository.getAllWords();
    }

    LiveData<List<Incident>> getAllIncidents() { return mAllIncidents; }

    public void insert(Incident incident) { mRepository.insert(incident); }
}
