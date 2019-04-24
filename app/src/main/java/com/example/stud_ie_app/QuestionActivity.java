package com.example.stud_ie_app;

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
    TextView optionA;
    TextView optionB;
    TextView optionC;
    TextView optionD;

    CardView answerA;
    CardView answerB;
    CardView answerC;
    CardView answerD;

    String category;
    ArrayList<String> wordBank;
    int questionNumber = 0;
    String currentSentence;
    int currentAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        questionCategory = (TextView) findViewById(R.id.question_category);
        displaySentence = (TextView) findViewById(R.id.question_sentence);
        optionA = (TextView) findViewById(R.id.question_optionA);
        optionB = (TextView) findViewById(R.id.question_optionB);
        optionC = (TextView) findViewById(R.id.question_optionC);
        optionD = (TextView) findViewById(R.id.question_optionD);

        answerA = (CardView) findViewById(R.id.question_answerA);
        answerB = (CardView) findViewById(R.id.question_answerB);
        answerC = (CardView) findViewById(R.id.question_answerC);
        answerD = (CardView) findViewById(R.id.question_answerD);

        category = getIntent().getExtras().get(DashboardActivity.CATEGORY).toString();
        questionCategory.setText(category);
        wordBank = WordBank.getWordsBank(category);

        getNextQuestion();

    }

    private void getNextQuestion() {
        String word = wordBank.get(questionNumber);
        String sentence = getSentence(word);
        displaySentence.setText(getSentenceWithoutWord(sentence, word));
        getOptions(word);
    }

    // Get synonyms from the API and display it to the user
    private void getOptions(String word) {
        ArrayList<String> synonyms;
        ArrayList<String> options = new ArrayList<>();

        // Set the current answer
        try {
            synonyms = OxfordApiHelper.getSynonyms(word);
            options.add(word);
            for (int i = 0; i < 3; i++) {
                options.add(synonyms.get(i));
                if (synonyms.get(i).equals(word)) {
                    currentAnswer = i;
                }
            }
            Collections.shuffle(options);
            optionA.setText(options.get(0));
            optionB.setText(options.get(1));
            optionC.setText(options.get(2));
            optionD.setText(options.get(3));
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
        //TODO: Add options here
        System.out.println("Answer selected: HUEFWEHWFUIWFH");
        int[] options = {
            R.id.question_answerA,
            R.id.question_answerB,
            R.id.question_answerC,
            R.id.question_answerD
        };
        int answerSelected = 0;
        while (answerSelected < options.length) {
            if (view.getId() == options[answerSelected]) {
                break;
            }
            answerSelected++;
        }

        if (answerSelected == currentAnswer) {
            System.out.println("Incorrect answer!");
        } else {
            System.out.println("Correct answer");
        }
    }

}
