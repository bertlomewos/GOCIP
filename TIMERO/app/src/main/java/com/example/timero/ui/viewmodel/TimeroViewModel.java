package com.example.timero.ui.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.timero.data.local.entity.AnswerEntity;
import com.example.timero.data.local.entity.PostEntity;
import com.example.timero.data.local.entity.QuestionEntity;
import com.example.timero.data.local.entity.UserEntity;
import com.example.timero.data.repository.TimeroRepository;

import java.util.List;

public class TimeroViewModel extends AndroidViewModel {
    private final TimeroRepository repository;

    public TimeroViewModel(Application application) {
        super(application);
        repository = new TimeroRepository(application);
    }

    // User operations
    public void insertUser(UserEntity user) {
        repository.insertUser(user);
    }

    public void updateUser(UserEntity user) {
        repository.updateUser(user);
    }

    public void deleteUser(UserEntity user) {
        repository.deleteUser(user);
    }

    public LiveData<UserEntity> getUserById(int userId) {
        return repository.getUserById(userId);
    }

    public LiveData<List<UserEntity>> getAllUsers() {
        return repository.getAllUsers();
    }

    // Post operations
    public void insertPost(PostEntity post) {
        repository.insertPost(post);
    }

    public void updatePost(PostEntity post) {
        repository.updatePost(post);
    }

    public void deletePost(PostEntity post) {
        repository.deletePost(post);
    }

    public LiveData<PostEntity> getPostById(int postId) {
        return repository.getPostById(postId);
    }

    public LiveData<List<PostEntity>> getPostsByUser(int userId) {
        return repository.getPostsByUser(userId);
    }

    public LiveData<List<PostEntity>> getAllPosts() {
        return repository.getAllPosts();
    }

    // Question operations
    public void insertQuestion(QuestionEntity question) {
        repository.insertQuestion(question);
    }

    public void updateQuestion(QuestionEntity question) {
        repository.updateQuestion(question);
    }

    public void deleteQuestion(QuestionEntity question) {
        repository.deleteQuestion(question);
    }

    public LiveData<QuestionEntity> getQuestionById(int questionId) {
        return repository.getQuestionById(questionId);
    }

    public LiveData<List<QuestionEntity>> getQuestionsByUser(int userId) {
        return repository.getQuestionsByUser(userId);
    }

    public LiveData<List<QuestionEntity>> getAllQuestions() {
        return repository.getAllQuestions();
    }

    // Answer operations
    public void insertAnswer(AnswerEntity answer) {
        repository.insertAnswer(answer);
    }

    public void updateAnswer(AnswerEntity answer) {
        repository.updateAnswer(answer);
    }

    public void deleteAnswer(AnswerEntity answer) {
        repository.deleteAnswer(answer);
    }

    public LiveData<AnswerEntity> getAnswerById(int answerId) {
        return repository.getAnswerById(answerId);
    }

    public LiveData<List<AnswerEntity>> getAnswersByQuestion(int questionId) {
        return repository.getAnswersByQuestion(questionId);
    }

    public LiveData<List<AnswerEntity>> getAllAnswers() {
        return repository.getAllAnswers();
    }
} 