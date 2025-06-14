package com.example.timero.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.timero.data.local.dao.AnswerDao;
import com.example.timero.data.local.dao.PostDao;
import com.example.timero.data.local.dao.QuestionDao;
import com.example.timero.data.local.dao.UserDao;
import com.example.timero.data.local.entity.AnswerEntity;
import com.example.timero.data.local.entity.PostEntity;
import com.example.timero.data.local.entity.QuestionEntity;
import com.example.timero.data.local.entity.UserEntity;

@Database(entities = {
        UserEntity.class,
        PostEntity.class,
        QuestionEntity.class,
        AnswerEntity.class
}, version = 1, exportSchema = false)
public abstract class TimeroDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "timero_db";
    private static TimeroDatabase instance;

    public abstract UserDao userDao();
    public abstract PostDao postDao();
    public abstract QuestionDao questionDao();
    public abstract AnswerDao answerDao();

    public static synchronized TimeroDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    TimeroDatabase.class,
                    DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
} 