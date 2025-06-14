package com.example.timero.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Answer implements Parcelable {
    private String answerText;
    private boolean isCorrect;

    public Answer(String answerText, boolean isCorrect) {
        this.answerText = answerText;
        this.isCorrect = isCorrect;
    }

    // Getters and Setters
    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }


    // --- Parcelable Implementation ---
    protected Answer(Parcel in) {
        answerText = in.readString();
        isCorrect = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(answerText);
        dest.writeByte((byte) (isCorrect ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Answer> CREATOR = new Creator<Answer>() {
        @Override
        public Answer createFromParcel(Parcel in) {
            return new Answer(in);
        }

        @Override
        public Answer[] newArray(int size) {
            return new Answer[size];
        }
    };
}
