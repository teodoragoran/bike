package com.example.bike;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface IncidentDao {
    @Insert
    void insert(Incident incident);

    @Query("SELECT * from incidents_table")
    LiveData<List<Incident>> getAllWords();
}
