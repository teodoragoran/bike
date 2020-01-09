package com.example.bike;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "incidents_table")
public class Incident {
    @NonNull
    @ColumnInfo(name = "type")
    @PrimaryKey
    private String type;

    @ColumnInfo(name = "latitude")
    private double latitude;

    @ColumnInfo(name = "longitude")
    private double longitude;

    public Incident(@NonNull String type, @NonNull double latitude, @NonNull double longitude) {
        this.type = type;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getType() {
        return this.type;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getLongitude() {
        return this.longitude;
    }
}
