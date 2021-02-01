package dev.esgi.quiveutgagnerdesmillions;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.analytics.FirebaseAnalytics;

public class Gameover extends AppCompatActivity {
    private Button back_to_menu, save_score, crash;
    private TextView text, winText;
    private FirebaseAnalytics firebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        firebase = FirebaseAnalytics.getInstance(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameover);

        back_to_menu = findViewById(R.id.btn_back_to_menu);
        save_score = findViewById(R.id.btn_save_score);
        text = findViewById(R.id.scoreText);
        winText = findViewById(R.id.gameoverText);
        crash = findViewById(R.id.btn_crash);

        crash.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                throw new RuntimeException("test");

            }

        });

        text.setText("score : " + MainActivity.score);
        Boolean status = false;
        try {
            status = getIntent().getExtras().getBoolean("win");
        } catch (NullPointerException e) {
        }

        if (status) {
            winText.setText("You WIN !");
        } else {
            winText.setText("YOU LOSE !");
        }

        Bundle gameStatus = new Bundle();
        gameStatus.putBoolean(FirebaseAnalytics.Param.SUCCESS, status);
        firebase.logEvent(FirebaseAnalytics.Event.LEVEL_END, gameStatus);
        back_to_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.score = 0;
                Intent i = new Intent(Gameover.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

    }
}
