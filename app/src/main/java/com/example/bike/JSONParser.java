package com.example.bike;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JSONParser {

    private JSONObject obj = null;
    List<IncidentBean> incidents = new ArrayList<>();

    JSONParser(String string) {
        try {

            obj = new JSONObject(string);

        } catch (Throwable t) {
            Log.e("My App", "Could not parse malformed JSON: \"" + obj + "\"");
        }
    }

    public List<IncidentBean> getIncidentsList() {
        try {
            JSONArray itemsArray = obj.getJSONArray("features");

            for (int i = 0; i < itemsArray.length(); i++) {
                String type = itemsArray.getJSONObject(i).getJSONObject("properties").getString("type");
                JSONArray geometry = itemsArray.getJSONObject(i).getJSONObject("geometry").getJSONArray("coordinates");

                Double lat = geometry.getDouble(0);
                Double longitude = geometry.getDouble(1);

                IncidentBean incidentBean = new IncidentBean(type, lat, longitude);
                incidents.add(incidentBean);

            }

        } catch (Throwable t) {
            Log.e("My App", "Could not parse malformed JSON: \"" + obj + "\"");
        }

        return incidents;
    }
}
