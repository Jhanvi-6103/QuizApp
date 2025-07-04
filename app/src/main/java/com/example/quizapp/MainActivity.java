package com.example.quizapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private RadioGroup optionsGroup;
    private Button actionButton;
    private TextView question, result;
    private int currentQuestionIndex = 0;
    private int correctAnswers = 0;

    private String[][] questions = {
            {"What is the capital of France?", "Paris", "London", "Berlin", "Madrid", "Paris"},
            {"Which planet is known as the Red Planet?", "Earth", "Mars", "Jupiter", "Saturn", "Mars"},
            {"Who painted the Mona Lisa?", "Vincent van Gogh", "Leonardo da Vinci", "Pablo Picasso", "Claude Monet", "Leonardo da Vinci"},
            {"What is the largest mammal?", "Elephant", "Blue Whale", "Giraffe", "Hippopotamus", "Blue Whale"},
            {"What is the chemical symbol for water?", "O2", "H2O", "CO2", "NaCl", "H2O"},
            {"Who wrote 'Romeo and Juliet'?", "Charles Dickens", "William Shakespeare", "Mark Twain", "Jane Austen", "William Shakespeare"},
            {"Which gas do plants use for photosynthesis?", "Oxygen", "Carbon Dioxide", "Nitrogen", "Hydrogen", "Carbon Dioxide"},
            {"What is the smallest prime number?", "0", "1", "2", "3", "2"},
            {"Which country is known as the Land of the Rising Sun?", "China", "Japan", "Thailand", "South Korea", "Japan"},
            {"What is the hardest natural substance?", "Iron", "Gold", "Diamond", "Silver", "Diamond"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        optionsGroup = findViewById(R.id.options_group);
        actionButton = findViewById(R.id.next_button);
        question = findViewById(R.id.question);
        result = findViewById(R.id.result);

        actionButton.setEnabled(false);

        loadQuestion();

        actionButton.setOnClickListener(v -> {
            if (currentQuestionIndex < questions.length - 1) {
                currentQuestionIndex++;
                loadQuestion();
                actionButton.setEnabled(false);
            } else {
                showQuizResult();
            }
        });

        optionsGroup.setOnCheckedChangeListener((group, checkedId) -> {
            checkAnswer();
            actionButton.setEnabled(true);
        });
    }

    private void loadQuestion() {
        optionsGroup.clearCheck();
        result.setVisibility(View.GONE);

        question.setText(questions[currentQuestionIndex][0]);
        ((RadioButton) findViewById(R.id.option1)).setText(questions[currentQuestionIndex][1]);
        ((RadioButton) findViewById(R.id.option2)).setText(questions[currentQuestionIndex][2]);
        ((RadioButton) findViewById(R.id.option3)).setText(questions[currentQuestionIndex][3]);
        ((RadioButton) findViewById(R.id.option4)).setText(questions[currentQuestionIndex][4]);

        if (currentQuestionIndex == questions.length - 1) {
            actionButton.setText("Submit");
        } else {
            actionButton.setText("Next");
        }

        actionButton.setEnabled(false);

        for (int i = 0; i < optionsGroup.getChildCount(); i++) {
            optionsGroup.getChildAt(i).setEnabled(true);
        }
    }

    private void checkAnswer() {
        RadioButton selectedAnswer = findViewById(optionsGroup.getCheckedRadioButtonId());
        if (selectedAnswer != null) {
            String selectedText = selectedAnswer.getText().toString();
            String correctAnswer = questions[currentQuestionIndex][5];

            if (selectedText.equals(correctAnswer)) {
                result.setText("Correct!");
                result.setTextColor(getResources().getColor(R.color.parrot_green));
                correctAnswers++;
            } else {
                result.setText("Incorrect!");
                result.setTextColor(getResources().getColor(R.color.red));
            }

            for (int i = 0; i < optionsGroup.getChildCount(); i++) {
                optionsGroup.getChildAt(i).setEnabled(false);
            }

            result.setVisibility(View.VISIBLE);
        }
    }

    private void showQuizResult() {
        Toast.makeText(MainActivity.this, "Quiz Completed!", Toast.LENGTH_SHORT).show();

        result.setText("Your Score: " + correctAnswers + "/" + questions.length);
        result.setVisibility(View.VISIBLE);

        actionButton.setText("Retake Quiz");
        actionButton.setOnClickListener(v -> {
            correctAnswers = 0;
            currentQuestionIndex = 0;
            loadQuestion();
            result.setVisibility(View.GONE);
            actionButton.setText("Next");
        });
    }
}
