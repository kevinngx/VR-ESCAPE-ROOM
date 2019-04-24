package com.example.stud_ie_app;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.example.stud_ie_app.ApiClasses.OxfordApiHelper;
import com.example.stud_ie_app.DatabaseClasses.WordBank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutionException;

public class QuestionActivity extends AppCompatActivity {

    TextView questionCategory;
    TextView displaySentence;

    CardView[] answers = new CardView[4];
    TextView[] options = new TextView[4];

    String category;
    ArrayList<String> wordBank;
    int questionNumber = 0;
    int currentAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        questionCategory = (TextView) findViewById(R.id.question_category);
        displaySentence = (TextView) findViewById(R.id.question_sentence);

        options[0] = (TextView) findViewById(R.id.question_optionA);
        options[1] = (TextView) findViewById(R.id.question_optionB);
        options[2] = (TextView) findViewById(R.id.question_optionC);
        options[3] = (TextView) findViewById(R.id.question_optionD);

        answers[0] = (CardView) findViewById(R.id.question_answerA);
        answers[1] = (CardView) findViewById(R.id.question_answerB);
        answers[2] = (CardView) findViewById(R.id.question_answerC);
        answers[3] = (CardView) findViewById(R.id.question_answerD);

        // Sets up level settings
        category = getIntent().getExtras().get(DashboardActivity.CATEGORY).toString();
        questionCategory.setText(category);
        wordBank = WordBank.getWordsBank(category);

        refreshQuestion();

    }

    private void refreshQuestion() {
        // Set up current question
        clearCardColors();
        String word = wordBank.get(questionNumber);
        String sentence = getSentence(word);
        displaySentence.setText(getSentenceWithoutWord(sentence, word));
        getOptions(word);
    }

    // Get synonyms from the API and display it to the user
    private void getOptions(String word) {
        ArrayList<String> synonyms;
        ArrayList<String> optionText = new ArrayList<>();

        // Set the current answer
        try {
            synonyms = OxfordApiHelper.getSynonyms(word);

            optionText.add(word);
            for (int i = 0; i < 3; i++) {
                optionText.add(synonyms.get(i));
            }

            Collections.shuffle(optionText);

            for (int i = 0; i < 4; i++) {
                options[i].setText(optionText.get(i));
                if (optionText.get(i).equals(word)) {
                    currentAnswer = i;
                }
            }

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String getSentence(String word) {
        try {
            return OxfordApiHelper.getSentence(word);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getSentenceWithoutWord(String sentence, String word) {
        String regex = String.format("\\s*\\b%s\\b\\s*", word);
        String result = sentence.replaceAll(regex, " [    ?    ] ");

        // Will remove suffix of 's'
        regex = String.format("\\s*\\b%ss\\b\\s*", word);
        result = result.replaceAll(regex, " [    ?    ] ");

        // Will remove suffix of 's'
        regex = String.format("\\s*\\b%sd\\b\\s*", word);
        result = result.replaceAll(regex, " [    ?    ] ");

        System.out.println(result);

        return result;

    }

    public void onAnswerSelect(View view) {
        int[] options = {
            R.id.question_answerA, // 0
            R.id.question_answerB, // 1
            R.id.question_answerC, // 2
            R.id.question_answerD // 3
        };

        int answerSelected = 0;
        while (answerSelected < options.length) {
            if (view.getId() == options[answerSelected]) {
                break;
            }
            answerSelected++;
        }

        if (answerSelected == currentAnswer) {
            System.out.println("Correct answer selected! " + answerSelected);
        } else {
            System.out.println("Incorrect answer selected! " + answerSelected);
        }
        setCardColor(answerSelected);

    }

    public void setCardColor(int selectedAnswer) {
        System.out.println("current answer: " + currentAnswer);
        answers[selectedAnswer].setCardBackgroundColor(Color.parseColor("#F57C00"));
        answers[currentAnswer].setCardBackgroundColor(Color.parseColor("#8BC34A"));
    }

    public void clearCardColors() {
        for (int i = 0; i < 4; i++) {
            answers[i].setCardBackgroundColor(Color.WHITE);
        }
    }

}
