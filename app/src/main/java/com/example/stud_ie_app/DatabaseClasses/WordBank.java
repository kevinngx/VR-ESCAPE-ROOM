package com.example.stud_ie_app.DatabaseClasses;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


public class WordBank {

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
