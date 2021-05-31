package prem.raj;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import prem.raj.R;

import java.util.Objects;

public class BaseActivity extends AppCompatActivity {
    private long backPressTime;
    private Toast backToast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.colorDark));
        Objects.requireNonNull(getSupportActionBar()).setTitle("APP");
    }
    @Override
    public void onBackPressed() {

        if (backPressTime + 800 > System.currentTimeMillis()) {
            backToast.cancel();
            super.onBackPressed();
            return;
        } else {
            backToast = Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressTime = System.currentTimeMillis();
    }
    public void disease(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void weather(View view) {
        Intent intent = new Intent(this, MainActivity1.class);
        startActivity(intent);
    }
}