package com.example.stud_ie_app;

public class WordResult {

    String word;
    Syllables syllables;

    public WordResult() {
    }

    public WordResult(String word, Syllables syllables) {
        this.word = word;
        this.syllables = syllables;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public Syllables getSyllables() {
        return syllables;
    }

    public void setSyllables(Syllables syllables) {
        this.syllables = syllables;
    }

    @Override
    public String toString() {
        return "WordResult{" +
                "word='" + word + '\'' +
                ", syllables=" + syllables +
                '}';
    }
}
