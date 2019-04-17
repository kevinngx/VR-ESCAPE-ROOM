package com.example.stud_ie_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "https://wordsapiv1.p.rapidapi.com/words/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.wordText);
    }

    public void onButtonPress(View view) throws ExecutionException, InterruptedException {

//        TextView wordText = findViewById(R.id.wordText);
//        TextView sentenceView = findViewById(R.id.sentenceText);
//
//        WordApi wordApi = WordHelper.getWord();
//        OxfordDictionaryApi oxfordDictionaryApi = SentenceHelper.getSentence(wordApi.getWord());
//
//        while (oxfordDictionaryApi == null) {
//            wordApi = WordHelper.getWord();
//            oxfordDictionaryApi = SentenceHelper.getSentence(wordApi.getWord());
//        }
//
//        wordText.setText(wordApi.getWord());
//        String sentence = oxfordDictionaryApi.getSentencesList().get(0).text;
//        System.out.println(sentence);
//        sentenceView.setText(sentence);

        Intent intent = new Intent(this, LevelSelectActivity.class);
        startActivity(intent);

    }

}
