package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    private TextView resultSummary, score;
    private Button retakeQuizButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        resultSummary = findViewById(R.id.result_summary);
        score = findViewById(R.id.score);
        retakeQuizButton = findViewById(R.id.retake_quiz);

        // Get the score passed from MainActivity
        int correctAnswers = getIntent().getIntExtra("correct_answers", 0);
        int totalQuestions = getIntent().getIntExtra("total_questions", 0);

        // Display the score
        score.setText("Score: " + correctAnswers + "/" + totalQuestions);

        // Set OnClickListener for Retake Quiz button
        retakeQuizButton.setOnClickListener(v -> {
            // Start MainActivity to retake the quiz
            Intent intent = new Intent(ResultActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
