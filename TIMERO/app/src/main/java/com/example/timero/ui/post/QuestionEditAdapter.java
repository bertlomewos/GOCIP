package com.example.timero.ui.post;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.timero.R;
import com.example.timero.data.model.Question;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.util.ArrayList;
import java.util.List;

public class QuestionEditAdapter extends RecyclerView.Adapter<QuestionEditAdapter.QuestionViewHolder> {

    private List<Question> questions = new ArrayList<>();

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_question_edit, parent, false);
        return new QuestionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
        Question currentQuestion = questions.get(position);
        holder.bind(currentQuestion);
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
        notifyDataSetChanged(); // In a real app, use DiffUtil for better performance
    }

    static class QuestionViewHolder extends RecyclerView.ViewHolder {
        private final TextInputEditText questionEditText;
        private final LinearLayout answersContainer;

        public QuestionViewHolder(@NonNull View itemView) {
            super(itemView);
            questionEditText = itemView.findViewById(R.id.question_edit_text);
            answersContainer = itemView.findViewById(R.id.answers_container);
        }

        public void bind(Question question) {
            questionEditText.setText(question.getQuestionText());

            // Dynamically add answer fields
            answersContainer.removeAllViews(); // Clear old views first
            LayoutInflater inflater = LayoutInflater.from(itemView.getContext());
            for (int i = 0; i < question.getAnswers().size(); i++) {
                TextInputLayout answerLayout = (TextInputLayout) inflater.inflate(R.layout.item_answer_edit, answersContainer, false);
                TextInputEditText answerEditText = answerLayout.findViewById(R.id.answer_edit_text);

                if (i == 0) {
                    answerLayout.setHint("Answer " + (i + 1) + " (Correct)");
                } else {
                    answerLayout.setHint("Answer " + (i + 1));
                }

                answerEditText.setText(question.getAnswers().get(i).getAnswerText());
                answersContainer.addView(answerLayout);
            }
        }
    }
}
