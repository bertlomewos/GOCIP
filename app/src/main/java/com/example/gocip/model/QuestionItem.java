package com.example.gocip.model;

import java.util.ArrayList;
import java.util.List;
public class QuestionItem {
    private String questionText;
    private List<String> options;
    private int correctOptionIndex; // -1 if no correct option selected, or 0-based index

    public QuestionItem() {
        this.questionText = "";
        this.options = new ArrayList<>();
        // Initialize with a default number of empty options, e.g., 4
        for (int i = 0; i < 4; i++) {
            this.options.add("");
        }
        this.correctOptionIndex = -1;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getOption(int index) {
        if (index >= 0 && index < options.size()) {
            return options.get(index);
        }
        return "";
    }

    public void setOption(int index, String optionText) {
        if (index >= 0 && index < options.size()) {
            options.set(index, optionText);
        } else if (index == options.size()) { // Allow adding if it's the next logical option
            options.add(optionText);
        }
    }

    public int getCorrectOptionIndex() {
        return correctOptionIndex;
    }

    public void setCorrectOptionIndex(int correctOptionIndex) {
        this.correctOptionIndex = correctOptionIndex;
    }

    public int getOptionsCount() {
        return options.size();
    }
}
