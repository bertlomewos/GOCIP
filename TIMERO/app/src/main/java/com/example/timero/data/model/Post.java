package com.example.timero.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Post implements Parcelable {

    private String id;
    private String title;
    private String description;
    private String imageUrl;
    private String postType; // "QUESTION", "DOCUMENT", "VIDEO"
    private int viewCount;
    private int likeCount;
    private List<Question> questions; // New field for questions

    public Post(String id, String title, String description, String imageUrl, String postType, int viewCount, int likeCount, List<Question> questions) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.postType = postType;
        this.viewCount = viewCount;
        this.likeCount = likeCount;
        this.questions = questions;
    }

    // Getters
    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getImageUrl() { return imageUrl; }
    public String getPostType() { return postType; }
    public int getViewCount() { return viewCount; }
    public int getLikeCount() { return likeCount; }
    public List<Question> getQuestions() { return questions; }

    // Setters
    public void setId(String id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public void setPostType(String postType) { this.postType = postType; }
    public void setViewCount(int viewCount) { this.viewCount = viewCount; }
    public void setLikeCount(int likeCount) { this.likeCount = likeCount; } // This was the missing method
    public void setQuestions(List<Question> questions) { this.questions = questions; }


    // Parcelable Implementation
    protected Post(Parcel in) {
        id = in.readString();
        title = in.readString();
        description = in.readString();
        imageUrl = in.readString();
        postType = in.readString();
        viewCount = in.readInt();
        likeCount = in.readInt();
        questions = in.createTypedArrayList(Question.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(imageUrl);
        dest.writeString(postType);
        dest.writeInt(viewCount);
        dest.writeInt(likeCount);
        dest.writeTypedList(questions);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };
}
