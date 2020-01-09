package com.example.bike;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Incident.class}, version = 1, exportSchema = false)
public abstract class IncidentRoomDatabase extends RoomDatabase {

    public abstract IncidentDao incidentDao();
    private static IncidentRoomDatabase INSTANCE;

    static IncidentRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (IncidentRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            IncidentRoomDatabase.class, "incidents_database")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            // Migration is not part of this practical.
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}