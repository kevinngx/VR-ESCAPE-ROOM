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
import android.widget.ImageView;
import android.widget.TextView;

import com.example.stud_ie_app.ApiClasses.OxfordApiHelper;
import com.example.stud_ie_app.DatabaseClasses.ImageBank;
import com.example.stud_ie_app.DatabaseClasses.SessionData;
import com.example.stud_ie_app.RecyclerViewAdapters.SentencesRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
    ArrayList<String> sentenceBank;
    ArrayList<String> wordSentences;
    int currentQuestion = 0;
    int currentAnswer;
    int correctInSession = 0;
    int consecutiveCorrect = 0;
    int consecutiveWrong = 0;

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
        sentenceBank = QuestionBank.getSentencesBank(category);
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
        displaySentence.setText(getSentenceWithoutWord(sentenceBank.get(currentQuestion), word));
//        wordSentences = getWordSentences(word);
//        displaySentence.setText(getSentenceWithoutWord(wordSentences.get(0), word));
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
//        ArrayList<String> synonyms;
        ArrayList<String> optionText = new ArrayList<>();

//        // Set the current answer
//        try {
//            synonyms = OxfordApiHelper.getSynonyms(word);
//
//            optionText.add(word);
//            for (int i = 0; i < 3; i++) {
//                optionText.add(synonyms.get(i));
//            }
//
//            Collections.shuffle(optionText);
//
//            for (int i = 0; i < 4; i++) {
//                options[i].setText(optionText.get(i));
//                if (optionText.get(i).equals(word)) {
//                    currentAnswer = i;
//                }
//            }
//
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }


        ArrayList<String> otherOptions = QuestionBank.getOptionsFromWordBank(category, word);
        for (int i = 0; i < 3; i++) {
            optionText.add(otherOptions.get(i));
        }

        optionText.add(word);
        Collections.shuffle(optionText);

        for (int i = 0; i < 4; i++) {
            options[i].setText(optionText.get(i));
            if (optionText.get(i).equals(word)) {
                currentAnswer = i;
            }
        }

    }

    private String getSentenceWithoutWord(String sentence, String word) {
        String regex = String.format("\\s*\\b%s\\b\\s*", word);
        String result = sentence.replaceAll(regex, " [    ?    ] ");

        // Will remove suffix of 's'
        regex = String.format("\\s*\\b%ss\\b\\s*", word);
        result = result.replaceAll(regex, " [    ?    ] ");

        // Will remove suffix of 'd'
        regex = String.format("\\s*\\b%sd\\b\\s*", word);
        result = result.replaceAll(regex, " [    ?    ] ");

        // Will remove suffix of 'd'
        regex = String.format("\\s*\\b%sing\\b\\s*", word);
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
            correctInSession++;
            consecutiveCorrect++;
            consecutiveWrong = 0;
            int score = QuestionBank.getScore(category);
            checkScoreBadges(score);
            checkCategoryBadges();
            checkSessionBadges();
            pointsAllocation.setText(String.format("+%s points!", Integer.toString(score)));
            SessionData.currentUser.setScore(SessionData.currentUser.getScore() + score);
            SessionData.mUserDatabase.mUserDao().updateScore(score, SessionData.currentUser.getUserName());
        } else {
            pointsAllocation.setText("Incorrect Answer");
            consecutiveCorrect = 0;
            consecutiveWrong++;
        }
    }

    private void checkSessionBadges() {
        if (correctInSession == 10) {
            giveBadge(17);
        }
        if (consecutiveCorrect == 5) {
            giveBadge(18);
        }

        if (consecutiveWrong == 5) {
            giveBadge(21);
        }
    }

    private void checkCategoryBadges() {
        if (correctInSession == 5) {
            switch (category) {
                case "Transport":
                    giveBadge(7);
                    break;
                case "Beach":
                    giveBadge(6);
                    break;
                case "Circus":
                    giveBadge(9);
                    break;
                case "Jobs":
                    giveBadge(10);
                    break;
                case "Weather":
                    giveBadge(11);
                    break;
                case "Nature":
                    giveBadge(12);
                    break;
                case "Music":
                    giveBadge(13);
                    break;
                case "Exercise":
                    giveBadge(14);
                    break;
                case "Politics":
                    giveBadge(15);
                    break;
                case "Astronomy":
                    giveBadge(16);
                    break;
            }
        }
    }

    private void checkScoreBadges(int score) {
        // This will check the promotion related badges
        if (SessionData.currentUser.getScore() < 1000 && (SessionData.currentUser.getScore() + score) >= 1000) {
            giveBadge(2); // Promoted to Graduate
        }

        if (SessionData.currentUser.getScore() < 5000 && (SessionData.currentUser.getScore() + score) >= 5000) {
            giveBadge(3); // Promoted to Senior
        }

        if (SessionData.currentUser.getScore() < 10000 && (SessionData.currentUser.getScore() + score) >= 10000) {
            giveBadge(4); // Promoted to Manager
        }

        if (SessionData.currentUser.getScore() < 25000 && (SessionData.currentUser.getScore() + score) >= 25000) {
            giveBadge(5); // Promoted to Exec
        }

    }

    private void giveBadge(int badgeId) {
        // Does not give badge if the user already has it
        if (badgeExists(badgeId)) {
            return;
        }
        Badges badge = SessionData.mBadgeDatabase.mBadgeDao().fetchBadgeByID(badgeId);
        ImageView badgeImage;
        TextView badgeTitle;
        TextView badgeDescription;
        Button popupBack;

        mDialog.setContentView(R.layout.popup_badge_earned);

        badgeImage = (ImageView) mDialog.findViewById(R.id.badge_image);
        badgeTitle = (TextView) mDialog.findViewById(R.id.badge_title);
        badgeDescription = (TextView) mDialog.findViewById(R.id.badge_description);

        popupBack = (Button) mDialog.findViewById(R.id.popup_back);

        popupBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
            }
        });

        badgeImage.setImageResource(ImageBank.badges[badge.getIcon()]);
        badgeTitle.setText(badge.getName());
        badgeDescription.setText(badge.getDescription());

        SessionData.mUsrBadgesDatabase.mUsrBadgesDao().insertSingleBadge(new UsrBadges(SessionData.currentUser.getUserName(),badgeId));

        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        mDialog.show();
    }

    private boolean badgeExists(int badgeId) {
        //todo: check if the user already has the badge
        boolean userHasBadge = false;
        List<UsrBadges> userBadges = SessionData.mUsrBadgesDatabase.mUsrBadgesDao().getAllBadgesByUser(SessionData.currentUser.getUserName());
        for (int i = 0; i < userBadges.size(); i++) {
            if (userBadges.get(i).getBadgeID() == badgeId) {
                userHasBadge = true;
            }
        }
        return userHasBadge;
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
        wordSentences = getWordSentences(wordBank.get(currentQuestion));
        TextView popupWord;
        Button popupBack;
        RecyclerView sentencesRecyclerView;
        mDialog.setContentView(R.layout.popup_sentences);
        popupWord = (TextView) mDialog.findViewById(R.id.popup_word);
        popupBack = (Button) mDialog.findViewById(R.id.popup_back);

        // Setup RecyclerView
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
