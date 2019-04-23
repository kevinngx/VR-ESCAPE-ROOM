package com.example.stud_ie_app;

import com.example.stud_ie_app.ApiClasses.OxfordApiHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutionException;

public class QuestionBank {
    String category;
    ArrayList<String> words;
    ArrayList<String> sentences;
    ArrayList<Options> options;

    public class Options {
        ArrayList<String> options;

        public Options(String word) {
            ArrayList<String> synonyms;
            ArrayList<String> options = new ArrayList<>();
            try {
                synonyms = OxfordApiHelper.getSynonyms(word);
                options.add(word);
                for (int i = 0; i < 3; i++) {
                    options.add(synonyms.get(i));
                }
                Collections.shuffle(options);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public QuestionBank(String category) {
        this.category = category;
        words = getWordsBank(category);

    }

    public int getQuestionBankSize() {
        return words.size();
    }

    public static ArrayList<String> getWordsBank(String category) {
        ArrayList<String> wordBank = new ArrayList<>();
        switch (category) {
            case "Transport":
                wordBank.add("fly");
                wordBank.add("train");
                wordBank.add("car");
                wordBank.add("swim");
                wordBank.add("boat");
                wordBank.add("plane");
                wordBank.add("balloon");
                wordBank.add("canoe");
                wordBank.add("soar");
                wordBank.add("run");
                break;
            case "Animals":
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                break;
            case "Sports":
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                break;
            case "Jobs":
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                break;
            case "Weather":
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                break;
            case "Nature":
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                break;
            case "Instrument":
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                break;
            case "Exercise":
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                break;
            case "Politics":
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                break;
            case "Astronomy":
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                wordBank.add("");
                break;
        }
        Collections.shuffle(wordBank);
        return wordBank;
    }
}
