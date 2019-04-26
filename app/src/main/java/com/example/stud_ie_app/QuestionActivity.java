package com.example.stud_ie_app;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.stud_ie_app.ApiClasses.OxfordApiHelper;
import com.example.stud_ie_app.DatabaseClasses.SessionData;
import com.example.stud_ie_app.RecyclerViewAdapters.SentencesRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutionException;

public class QuestionActivity extends AppCompatActivity {
    private static final String TAG = "QuestionActivity";

    Dialog mDialog;
    CardView pointsCard;
    TextView pointsAllocation;

    TextView questionCategory;
    TextView displaySentence;
    CardView seeWordList;

    CardView[] answers = new CardView[4];
    TextView[] options = new TextView[4];

    String category;
    ArrayList<String> wordBank;
    ArrayList<String> wordSentences;
    int currentQuestion = 0;
    int currentAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate: start");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        questionCategory = (TextView) findViewById(R.id.question_category);
        displaySentence = (TextView) findViewById(R.id.question_sentence);

        pointsCard = (CardView) findViewById(R.id.points_card);
        pointsAllocation= (TextView) findViewById(R.id.points_allocation);

        options[0] = (TextView) findViewById(R.id.question_optionA);
        options[1] = (TextView) findViewById(R.id.question_optionB);
        options[2] = (TextView) findViewById(R.id.question_optionC);
        options[3] = (TextView) findViewById(R.id.question_optionD);

        answers[0] = (CardView) findViewById(R.id.question_answerA);
        answers[1] = (CardView) findViewById(R.id.question_answerB);
        answers[2] = (CardView) findViewById(R.id.question_answerC);
        answers[3] = (CardView) findViewById(R.id.question_answerD);

        seeWordList = (CardView) findViewById(R.id.btn_seeWordList);
        mDialog = new Dialog(this);

        // Sets up level settings
        category = getIntent().getExtras().get(DashboardActivity.CATEGORY).toString();
        questionCategory.setText(category);
        Log.d(TAG, "onCreate: Category is: " + category);
        wordBank = QuestionBank.getWordsBank(category);
        Log.d(TAG, "onCreate: Word Bank downloaded");
        refreshQuestion();

    }

    private void refreshQuestion() {
        enableAnswers(true);
        // Set up current question
        clearCardColors();
        seeWordList.setVisibility(View.INVISIBLE);
        seeWordList.setEnabled(false);
        pointsCard.setVisibility(View.INVISIBLE);
        String word = wordBank.get(currentQuestion);
        wordSentences = getWordSentences(word);
        displaySentence.setText(getSentenceWithoutWord(wordSentences.get(0), word));
        getOptions(word);

    }

    private ArrayList<String> getWordSentences(String word) {
        try {
            return OxfordApiHelper.getSentenceList(word);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
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
        enableAnswers(false);

        // Mark answer
        int answer = getAnswerSelected(view);
        markAnswer(answer);

        pointsCard.setVisibility(View.VISIBLE);
        seeWordList.setVisibility(View.VISIBLE);
        seeWordList.setEnabled(true);

        setCardColor(answer);
    }

    private void markAnswer(int answer) {
        if (answer == currentAnswer) {
            int score = QuestionBank.getScore(category);
            pointsAllocation.setText(String.format("+%s points!", Integer.toString(score)));
            SessionData.currentUser.setScore(SessionData.currentUser.getScore() + score);
            SessionData.mUserDatabase.mUserDao().updateScore(score, SessionData.currentUser.getUserName());
        } else {
            pointsAllocation.setText("Incorrect Answer");
        }
    }

    private int getAnswerSelected(View selectedCard) {
        // Get options
        int[] options = {
                R.id.question_answerA, // 0
                R.id.question_answerB, // 1
                R.id.question_answerC, // 2
                R.id.question_answerD // 3
        };

        // Get answer
        int answerSelected = 0;
        while (answerSelected < options.length) {
            if (selectedCard.getId() == options[answerSelected]) {
                break;
            }
            answerSelected++;
        }
        return answerSelected;
    }

    private void enableAnswers(boolean state) {
        for (int i = 0; i < answers.length; i++) {
            answers[i].setEnabled(state);
        }
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

    public void onShowSentenceList(View view) {
        // Opens a dialog window containing sentence list
        TextView popupWord;
        Button popupBack;
        RecyclerView sentencesRecyclerView;
        mDialog.setContentView(R.layout.popup_sentences);
        popupWord = (TextView) mDialog.findViewById(R.id.popup_word);
        popupBack = (Button) mDialog.findViewById(R.id.popup_back);

        sentencesRecyclerView = (RecyclerView) mDialog.findViewById(R.id.popup_sentencesRecyclerView);
        sentencesRecyclerView.setHasFixedSize(true);
        SentencesRecyclerViewAdapter recyclerViewAdapter = new SentencesRecyclerViewAdapter(mDialog.getContext(), wordSentences);
        sentencesRecyclerView.setLayoutManager(new LinearLayoutManager(mDialog.getContext()));
        sentencesRecyclerView.setAdapter(recyclerViewAdapter);
        popupBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
            }
        });
        popupWord.setText(wordBank.get(currentQuestion));
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        mDialog.show();
    }

    public void onBackButton(View view) {
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
    }

    public void onNextQuestion(View view) {
        if (currentQuestion < wordBank.size() - 1) {
            currentQuestion++;
            refreshQuestion();
        } else {
            // TODO: Level Complete
            Intent intent = new Intent(this, DashboardActivity.class);
            startActivity(intent);
        }
    }

}
