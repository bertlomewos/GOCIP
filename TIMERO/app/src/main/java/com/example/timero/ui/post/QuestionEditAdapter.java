package com.example.timero.ui.post;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.timero.R;
import com.example.timero.databinding.ItemQuestionEditBinding;
import com.example.timero.data.model.Answer;
import com.example.timero.data.model.Question;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import java.util.ArrayList;
import java.util.List;

public class QuestionEditAdapter extends RecyclerView.Adapter<QuestionEditAdapter.QuestionViewHolder> {

    private List<Question> questions = new ArrayList<>();
    private final OnRemoveClickListener removeClickListener;
    private final OnAddAnswerClickListener addAnswerClickListener;

    public interface OnRemoveClickListener {
        void onRemoveClicked(int position);
    }

    public interface OnAddAnswerClickListener {
        void onAddAnswerClicked(int position);
    }

    public QuestionEditAdapter(OnRemoveClickListener removeClickListener, OnAddAnswerClickListener addAnswerClickListener) {
        this.removeClickListener = removeClickListener;
        this.addAnswerClickListener = addAnswerClickListener;
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemQuestionEditBinding binding = ItemQuestionEditBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new QuestionViewHolder(binding, removeClickListener, addAnswerClickListener);
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
        notifyDataSetChanged();
    }

    static class QuestionViewHolder extends RecyclerView.ViewHolder {
        private final ItemQuestionEditBinding binding;

        public QuestionViewHolder(@NonNull ItemQuestionEditBinding binding, OnRemoveClickListener removeClickListener, OnAddAnswerClickListener addAnswerClickListener) {
            super(binding.getRoot());
            this.binding = binding;
            binding.removeQuestionButton.setOnClickListener(v -> {
                if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                    removeClickListener.onRemoveClicked(getAdapterPosition());
                }
            });
            binding.addAnswerButton.setOnClickListener(v -> {
                if (getAdapterPosition() != RecyclerView.NO_POSITION) {
                    addAnswerClickListener.onAddAnswerClicked(getAdapterPosition());
                }
            });
        }

        public void bind(Question question) {
            if (binding.questionEditText.getTag() instanceof TextWatcher) {
                binding.questionEditText.removeTextChangedListener((TextWatcher) binding.questionEditText.getTag());
            }
            binding.questionEditText.setText(question.getQuestionText());
            TextWatcher questionWatcher = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {}
                @Override
                public void afterTextChanged(Editable s) {
                    question.setQuestionText(s.toString());
                }
            };
            binding.questionEditText.addTextChangedListener(questionWatcher);
            binding.questionEditText.setTag(questionWatcher);
            binding.answersContainer.removeAllViews();
            LayoutInflater inflater = LayoutInflater.from(itemView.getContext());
            for (int i = 0; i < question.getAnswers().size(); i++) {
                final Answer currentAnswer = question.getAnswers().get(i);
                TextInputLayout answerLayout = (TextInputLayout) inflater.inflate(R.layout.item_answer_edit, binding.answersContainer, false);
                TextInputEditText answerEditText = answerLayout.findViewById(R.id.answer_edit_text);
                answerLayout.setHint(i == 0 ? "Answer " + (i + 1) + " (Correct)" : "Answer " + (i + 1));
                if (answerEditText.getTag() instanceof TextWatcher) {
                    answerEditText.removeTextChangedListener((TextWatcher) answerEditText.getTag());
                }
                answerEditText.setText(currentAnswer.getAnswerText());
                TextWatcher answerWatcher = new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {}
                    @Override
                    public void afterTextChanged(Editable s) {
                        currentAnswer.setAnswerText(s.toString());
                    }
                };
                answerEditText.addTextChangedListener(answerWatcher);
                answerEditText.setTag(answerWatcher);
                binding.answersContainer.addView(answerLayout);
            }
        }
    }
}