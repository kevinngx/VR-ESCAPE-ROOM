package com.example.stud_ie_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.stud_ie_app.ApiClasses.OxfordDictionaryApi;
import com.example.stud_ie_app.ApiClasses.SentenceHelper;
import com.example.stud_ie_app.ApiClasses.WordApi;
import com.example.stud_ie_app.ApiClasses.WordHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ExecutionException;

public class QuestionActivity extends AppCompatActivity {

    TextView category;
    TextView sentence;
    TextView optionA;
    TextView optionB;
    TextView optionC;
    TextView optionD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        category = (TextView) findViewById(R.id.question_category);
        sentence = (TextView) findViewById(R.id.question_sentence);
        optionA = (TextView) findViewById(R.id.question_optionA);
        optionB = (TextView) findViewById(R.id.question_optionB);
        optionC = (TextView) findViewById(R.id.question_optionC);
        optionD = (TextView) findViewById(R.id.question_optionD);

        //TODO: Change word here

        try {
            sentence.setText(getSentence("hello"));
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private String getSentence(String word) throws ExecutionException, InterruptedException  {

        OxfordDictionaryApi oxfordDictionaryApi = SentenceHelper.getSentence(word);

//        String sentence = oxfordDictionaryApi.getSentencesList().get(0).text;
        ArrayList<OxfordDictionaryApi.SentenceResult.lexicalEntries.Sentence> sentences = oxfordDictionaryApi.getSentencesList();
        Collections.shuffle(sentences);
        String sentence = sentences.get(0).text;
        System.out.println("Sentence pulled = " + sentence);

        return sentence;
    }

}
