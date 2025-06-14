package com.example.timero.data.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.timero.data.local.TimeroDatabase;
import com.example.timero.data.local.dao.AnswerDao;
import com.example.timero.data.local.dao.PostDao;
import com.example.timero.data.local.dao.QuestionDao;
import com.example.timero.data.local.dao.UserDao;
import com.example.timero.data.local.entity.AnswerEntity;
import com.example.timero.data.local.entity.PostEntity;
import com.example.timero.data.local.entity.QuestionEntity;
import com.example.timero.data.local.entity.UserEntity;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TimeroRepository {
    private final UserDao userDao;
    private final PostDao postDao;
    private final QuestionDao questionDao;
    private final AnswerDao answerDao;
    private final ExecutorService executorService;

    public TimeroRepository(Application application) {
        TimeroDatabase database = TimeroDatabase.getInstance(application);
        userDao = database.userDao();
        postDao = database.postDao();
        questionDao = database.questionDao();
        answerDao = database.answerDao();
        // Use a single-thread executor to ensure database operations execute sequentially.
        // This prevents race conditions such as posting before its parent user is saved, which
        // would violate foreign-key constraints and cause inserts to silently fail.
        executorService = Executors.newSingleThreadExecutor();
    }

    // User operations
    public void insertUser(UserEntity user) {
        executorService.execute(() -> userDao.insert(user));
    }

    public void updateUser(UserEntity user) {
        executorService.execute(() -> userDao.update(user));
    }

    public void deleteUser(UserEntity user) {
        executorService.execute(() -> userDao.delete(user));
    }

    public LiveData<UserEntity> getUserById(int userId) {
        return userDao.getUserById(userId);
    }

    public LiveData<List<UserEntity>> getAllUsers() {
        return userDao.getAllUsers();
    }

    // Post operations
    public void insertPost(PostEntity post) {
        executorService.execute(() -> postDao.insert(post));
    }

    public void updatePost(PostEntity post) {
        executorService.execute(() -> postDao.update(post));
    }

    public void deletePost(PostEntity post) {
        executorService.execute(() -> postDao.delete(post));
    }

    public LiveData<PostEntity> getPostById(int postId) {
        return postDao.getPostById(postId);
    }

    public LiveData<List<PostEntity>> getPostsByUser(int userId) {
        return postDao.getPostsByUser(userId);
    }

    public LiveData<List<PostEntity>> getAllPosts() {
        return postDao.getAllPosts();
    }

    // Question operations
    public void insertQuestion(QuestionEntity question) {
        executorService.execute(() -> questionDao.insert(question));
    }

    public void updateQuestion(QuestionEntity question) {
        executorService.execute(() -> questionDao.update(question));
    }

    public void deleteQuestion(QuestionEntity question) {
        executorService.execute(() -> questionDao.delete(question));
    }

    public LiveData<QuestionEntity> getQuestionById(int questionId) {
        return questionDao.getQuestionById(questionId);
    }

    public LiveData<List<QuestionEntity>> getQuestionsByUser(int userId) {
        return questionDao.getQuestionsByUser(userId);
    }

    public LiveData<List<QuestionEntity>> getAllQuestions() {
        return questionDao.getAllQuestions();
    }

    // Answer operations
    public void insertAnswer(AnswerEntity answer) {
        executorService.execute(() -> answerDao.insert(answer));
    }

    public void updateAnswer(AnswerEntity answer) {
        executorService.execute(() -> answerDao.update(answer));
    }

    public void deleteAnswer(AnswerEntity answer) {
        executorService.execute(() -> answerDao.delete(answer));
    }

    public LiveData<AnswerEntity> getAnswerById(int answerId) {
        return answerDao.getAnswerById(answerId);
    }

    public LiveData<List<AnswerEntity>> getAnswersByQuestion(int questionId) {
        return answerDao.getAnswersByQuestion(questionId);
    }

    public LiveData<List<AnswerEntity>> getAllAnswers() {
        return answerDao.getAllAnswers();
    }
} 