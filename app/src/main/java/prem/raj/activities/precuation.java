package prem.raj.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import static prem.raj.R.id;
import static prem.raj.R.layout;

public class precuation extends AppCompatActivity {
    static TextView details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_precuation);

        details=findViewById(id.precaution_text_detail);
        details.setText("> Wear a mask that covers your nose and mouth to help protect yourself and others.\n" +
                "> Stay 6 feet apart from others who don’t live with you.\n" +
                "> Get a COVID-19 vaccine when it is available to you.\n" +
                "> Avoid crowds and poorly ventilated indoor spaces.\n" +
                "> Wash your hands often with soap and water. Use hand sanitizer if soap and water aren’t available.");
    }
}