package com.example.gocip.adapter;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gocip.R;
import com.example.gocip.databinding.ItemQuestionLayoutBinding; // Assuming your item layout is item_question_layout.xml
import com.example.gocip.model.QuestionItem;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;
public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.QuestionViewHolder>{
    private List<QuestionItem> questionList;

    public QuestionAdapter(List<QuestionItem> questionList) {
        this.questionList = questionList;
    }

    @NonNull
    @Override
    public QuestionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemQuestionLayoutBinding binding = ItemQuestionLayoutBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new QuestionViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull QuestionViewHolder holder, int position) {
        QuestionItem currentQuestion = questionList.get(position);
        holder.bind(currentQuestion, position);
    }

    @Override
    public int getItemCount() {
        return questionList.size();
    }

    public void addQuestion(QuestionItem question) {
        questionList.add(question);
        notifyItemInserted(questionList.size() - 1);
    }

    public void removeQuestion(int position) {
        if (position >= 0 && position < questionList.size()) {
            questionList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, questionList.size()); // To update subsequent positions if needed
        }
    }

    public List<QuestionItem> getQuestionList() {
        return questionList;
    }


    class QuestionViewHolder extends RecyclerView.ViewHolder {
        private ItemQuestionLayoutBinding binding;
        private TextInputEditText[] optionEditTexts;
        private RadioButton[] optionRadioButtons;

        public QuestionViewHolder(@NonNull ItemQuestionLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            optionEditTexts = new TextInputEditText[]{
                    binding.editTextItemOption1,
                    binding.editTextItemOption2,
                    binding.editTextItemOption3,
                    binding.editTextItemOption4
            };
            optionRadioButtons = new RadioButton[]{
                    binding.radioButtonItemOption1,
                    binding.radioButtonItemOption2,
                    binding.radioButtonItemOption3,
                    binding.radioButtonItemOption4
            };
        }

        public void bind(final QuestionItem question, final int itemPosition) {
            binding.editTextItemQuestionText.setText(question.getQuestionText());

            for (int i = 0; i < optionEditTexts.length; i++) {
                if (i < question.getOptionsCount()) {
                    optionEditTexts[i].setText(question.getOption(i));
                    optionRadioButtons[i].setVisibility(ViewGroup.VISIBLE);
                    optionEditTexts[i].setVisibility(ViewGroup.VISIBLE);
                    ((ViewGroup)optionRadioButtons[i].getParent()).setVisibility(ViewGroup.VISIBLE);

                } else { // Should not happen if options are fixed at 4 in QuestionItem constructor
                    optionRadioButtons[i].setVisibility(ViewGroup.GONE);
                    optionEditTexts[i].setVisibility(ViewGroup.GONE);
                    ((ViewGroup)optionRadioButtons[i].getParent()).setVisibility(ViewGroup.GONE);
                }
            }

            // Set correct option radio button
            binding.radioGroupItemCorrectOption.clearCheck();
            if (question.getCorrectOptionIndex() >= 0 && question.getCorrectOptionIndex() < optionRadioButtons.length) {
                optionRadioButtons[question.getCorrectOptionIndex()].setChecked(true);
            }

            // --- Listeners to update model ---
            binding.editTextItemQuestionText.addTextChangedListener(new TextWatcher() {
                @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                    questionList.get(getAdapterPosition()).setQuestionText(s.toString());
                }
                @Override public void afterTextChanged(Editable s) {}
            });

            for (int i = 0; i < optionEditTexts.length; i++) {
                final int optionIndex = i;
                optionEditTexts[i].addTextChangedListener(new TextWatcher() {
                    @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                    @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if (getAdapterPosition() != RecyclerView.NO_POSITION) { // Check for valid position
                            questionList.get(getAdapterPosition()).setOption(optionIndex, s.toString());
                        }
                    }
                    @Override public void afterTextChanged(Editable s) {}
                });
            }

            binding.radioGroupItemCorrectOption.setOnCheckedChangeListener((group, checkedId) -> {
                if (getAdapterPosition() != RecyclerView.NO_POSITION) { // Check for valid position
                    int correctIndex = -1;
                    if (checkedId == R.id.radioButton_item_option1) correctIndex = 0;
                    else if (checkedId == R.id.radioButton_item_option2) correctIndex = 1;
                    else if (checkedId == R.id.radioButton_item_option3) correctIndex = 2;
                    else if (checkedId == R.id.radioButton_item_option4) correctIndex = 3;
                    questionList.get(getAdapterPosition()).setCorrectOptionIndex(correctIndex);
                }
            });

            binding.buttonRemoveQuestionItem.setOnClickListener(v -> {
                if (getAdapterPosition() != RecyclerView.NO_POSITION) { // Check for valid position
                    removeQuestion(getAdapterPosition());
                }
            });
        }
    }
}
