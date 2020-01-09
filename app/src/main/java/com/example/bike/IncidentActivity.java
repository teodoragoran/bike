package com.example.bike;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class IncidentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incident);
        this.setTitle("Report a new incident");

        Button report = findViewById(R.id.button2);
        final EditText editText   = findViewById(R.id.editText);
        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String description = editText.getText().toString();
                Log.d("click", description);

            }
        });
    }
}
