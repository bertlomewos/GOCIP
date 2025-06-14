package com.example.timero.ui.post;

import android.net.Uri;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.timero.data.model.Answer;
import com.example.timero.data.model.Post;
import com.example.timero.data.model.Question;
import com.example.timero.data.repository.PostRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class PostViewModel extends ViewModel {

    private final PostRepository repository;

    public MutableLiveData<String> title = new MutableLiveData<>();
    public MutableLiveData<String> description = new MutableLiveData<>();

    private final MutableLiveData<List<Question>> _questions = new MutableLiveData<>();
    public LiveData<List<Question>> questions = _questions;

    private final MutableLiveData<Uri> _selectedImageUri = new MutableLiveData<>();
    public LiveData<Uri> selectedImageUri = _selectedImageUri;

    // LiveData to signal when the post creation is complete
    private final MutableLiveData<Boolean> _postCreated = new MutableLiveData<>();
    public LiveData<Boolean> postCreated = _postCreated;

    public PostViewModel() {
        this.repository = PostRepository.getInstance();
        List<Question> initialQuestions = new ArrayList<>();
        initialQuestions.add(new Question("", new ArrayList<>(Arrays.asList(new Answer("", true), new Answer("", false)))));
        _questions.setValue(initialQuestions);
    }

    /**
     * Creates a new Post object with all entered data and adds it to the repository.
     * @param postType The type of post ("File" or "Question").
     */
    public void createAndSavePost(String postType) {
        String newTitle = title.getValue() != null ? title.getValue() : "No Title";
        String newDescription = description.getValue() != null ? description.getValue() : "";
        String imageUriString = _selectedImageUri.getValue() != null ? _selectedImageUri.getValue().toString() : "";

        List<Question> questionData = postType.equals("Question") ? _questions.getValue() : null;

        Post newPost = new Post(
                UUID.randomUUID().toString(),
                newTitle,
                newDescription,
                imageUriString,
                postType,
                0, // initial views
                0,  // initial likes
                questionData
        );

        repository.addNewPost(newPost);
        _postCreated.setValue(true); // Signal that the post is created
    }

    /**
     * Resets the post creation event status.
     */
    public void onPostCreationComplete() {
        _postCreated.setValue(false);
    }


    public void setSelectedImageUri(Uri uri) {
        _selectedImageUri.setValue(uri);
    }

    public void addQuestion() {
        List<Question> currentQuestions = _questions.getValue();
        if (currentQuestions != null) {
            ArrayList<Question> updatedList = new ArrayList<>(currentQuestions);
            updatedList.add(new Question("", new ArrayList<>(Arrays.asList(new Answer("", true), new Answer("", false)))));
            _questions.setValue(updatedList);
        }
    }

    public void removeQuestion(int position) {
        List<Question> currentQuestions = _questions.getValue();
        if (currentQuestions != null && position >= 0 && position < currentQuestions.size()) {
            ArrayList<Question> updatedList = new ArrayList<>(currentQuestions);
            updatedList.remove(position);
            _questions.setValue(updatedList);
        }
    }

    public void addAnswer(int questionPosition) {
        List<Question> currentQuestions = _questions.getValue();
        if (currentQuestions != null && questionPosition >= 0 && questionPosition < currentQuestions.size()) {
            ArrayList<Question> updatedList = new ArrayList<>(currentQuestions);
            Question questionToUpdate = updatedList.get(questionPosition);
            questionToUpdate.getAnswers().add(new Answer("", false));
            _questions.setValue(updatedList);
        }
    }
}
