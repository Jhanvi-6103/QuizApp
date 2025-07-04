package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Button startQuizButton = findViewById(R.id.start_quiz_button);
        ImageView animatedImage = findViewById(R.id.animated_image);

        Animation bounce = AnimationUtils.loadAnimation(this, R.anim.bounce);
        animatedImage.startAnimation(bounce);

        startQuizButton.setOnClickListener(v -> {
            Intent intent = new Intent(StartActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
