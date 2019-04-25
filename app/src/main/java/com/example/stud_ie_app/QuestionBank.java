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

    public static int getScore(String category) {
        switch (category) {
            case "Transport":
                return 100;
            case "Beach":
                return 200;
            case "Circus":
                return 300;
            case "Jobs":
                return 400;
            case "Weather":
                return 500;
            case "Nature":
                return 600;
            case "Music":
                return 700;
            case "Exercise":
                return 800;
            case "Politics":
                return 900;
            case "Astronomy":
                return 1000;
        }
        return 0;
    }

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
            case "Beach":
                wordBank.add("dive");
                wordBank.add("fish");
                wordBank.add("lake");
                wordBank.add("ocean");
                wordBank.add("sail");
                wordBank.add("tide");
                wordBank.add("underwater");
                wordBank.add("wharf");
                wordBank.add("vacation");
                wordBank.add("pier");
                break;
            case "Circus":
                wordBank.add("clown");
                wordBank.add("coustume");
                wordBank.add("festival");
                wordBank.add("perade");
                wordBank.add("magic");
                wordBank.add("entertainer");
                wordBank.add("cannon");
                wordBank.add("admission");
                wordBank.add("audience");
                wordBank.add("bicycle");
                break;
            case "Jobs":
                wordBank.add("butcher");
                wordBank.add("teacher");
                wordBank.add("lawyer");
                wordBank.add("scientist");
                wordBank.add("nurse");
                wordBank.add("engineer");
                wordBank.add("singer");
                wordBank.add("writer");
                wordBank.add("chef");
                wordBank.add("inventor");
                break;
            case "Weather":
                wordBank.add("sunny");
                wordBank.add("overcast");
                wordBank.add("rain");
                wordBank.add("chilly");
                wordBank.add("freezing");
                wordBank.add("downpour");
                wordBank.add("temperate");
                wordBank.add("smog");
                wordBank.add("frigid");
                wordBank.add("cloudy");
                break;
            case "Nature":
                wordBank.add("flower");
                wordBank.add("forest");
                wordBank.add("organic");
                wordBank.add("environment");
                wordBank.add("decomposition");
                wordBank.add("organism");
                wordBank.add("tropical");
                wordBank.add("farming");
                wordBank.add("climate");
                wordBank.add("valley");
                break;
            case "Music":
                wordBank.add("melody");
                wordBank.add("accompaniment");
                wordBank.add("baroque");
                wordBank.add("encore");
                wordBank.add("ensemble");
                wordBank.add("improvise");
                wordBank.add("medley");
                wordBank.add("recital");
                wordBank.add("tempo");
                wordBank.add("vibration");
                break;
            case "Exercise":
                wordBank.add("endurance");
                wordBank.add("active");
                wordBank.add("energy");
                wordBank.add("training");
                wordBank.add("performance");
                wordBank.add("perspiration");
                wordBank.add("therapeutic");
                wordBank.add("stretch");
                wordBank.add("movement");
                wordBank.add("exertion");
                break;
            case "Politics":
                wordBank.add("cabinet");
                wordBank.add("campaign");
                wordBank.add("candidate");
                wordBank.add("representation");
                wordBank.add("unprecedented");
                wordBank.add("delegate");
                wordBank.add("endorsement");
                wordBank.add("amendment");
                wordBank.add("conservative");
                wordBank.add("allegation");
                break;
            case "Astronomy":
                wordBank.add("illumination");
                wordBank.add("celestial");
                wordBank.add("measurement");
                wordBank.add("meteor");
                wordBank.add("gravity");
                wordBank.add("inertia");
                wordBank.add("spectrum");
                wordBank.add("spherical");
                wordBank.add("galaxy");
                wordBank.add("magnitude");
                break;
        }
        Collections.shuffle(wordBank);
        return wordBank;
    }
}
