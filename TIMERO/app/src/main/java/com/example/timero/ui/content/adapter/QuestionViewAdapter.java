package com.example.timero.ui.content.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.timero.R;
import com.example.timero.data.model.Answer;
import com.example.timero.data.model.Question;
import java.util.ArrayList;
import java.util.List;

public class QuestionViewAdapter extends RecyclerView.Adapter<QuestionViewAdapter.QuestionViewHolder> {

    private final List<Question> questions;
    private final List<Integer> userAnswers;

    public QuestionViewAdapter(List<Question> questions) {
        this.questions = questions;
        this.userAnswers = new ArrayList<>();
        for (int i = 0; i < questions.size(); i++) {
            userAnswers.add(-1);
        }
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_question_view, parent, false);
        return new QuestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
        Question currentQuestion = questions.get(position);
        holder.bind(currentQuestion, position);
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public int calculateScore() {
        int score = 0;
        for (int i = 0; i < questions.size(); i++) {
            int selectedAnswerIndex = userAnswers.get(i);
            if (selectedAnswerIndex != -1) {
                Answer selectedAnswer = questions.get(i).getAnswers().get(selectedAnswerIndex);
                if (selectedAnswer.isCorrect()) {
                    score++;
                }
            }
        }
        return score;
    }

    class QuestionViewHolder extends RecyclerView.ViewHolder {
        private final TextView questionTextView;
        private final RadioGroup answersRadioGroup;

        public QuestionViewHolder(@NonNull View itemView) {
            super(itemView);
            questionTextView = itemView.findViewById(R.id.question_text_view);
            answersRadioGroup = itemView.findViewById(R.id.answers_radio_group);
        }

        public void bind(Question question, final int questionPosition) {
            questionTextView.setText(question.getQuestionText());
            answersRadioGroup.removeAllViews();

            Context context = itemView.getContext();
            for (int i = 0; i < question.getAnswers().size(); i++) {
                Answer answer = question.getAnswers().get(i);
                RadioButton radioButton = new RadioButton(context);
                radioButton.setText(answer.getAnswerText());
                radioButton.setId(i);
                answersRadioGroup.addView(radioButton);
            }

            answersRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
                userAnswers.set(questionPosition, checkedId);
            });
        }
    }
}
