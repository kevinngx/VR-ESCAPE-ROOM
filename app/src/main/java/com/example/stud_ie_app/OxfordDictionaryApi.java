package com.example.stud_ie_app;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class OxfordDictionaryApi {

    ArrayList<SentenceResult> results;

    public ArrayList<SentenceResult.lexicalEntries.Sentence> getSentencesList() {
        return this.results.get(0).lexicalEntries.get(0).sentences;
    }

    @Override
    public String toString() {
        return "OxfordDictionaryApi{" +
                "results=" + results +
                '}';
    }

    class SentenceResult {
        String id;
        ArrayList<lexicalEntries> lexicalEntries;

        class lexicalEntries {
            String language;
            String lexicalCategory;
            String text;
            ArrayList<Sentence> sentences;

            public ArrayList<Sentence> getSentences() {
                return sentences;
            }

            class Sentence {
                String text;

            }
        }
    }

}
