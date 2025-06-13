package com.example.timero.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Post implements Parcelable{    private String id;
    private String title;
    private String description;
    private String imageUrl;
    private String postType; // "QUESTION", "DOCUMENT", "VIDEO"
    private int viewCount;
    private int likeCount;

    public Post(String id, String title, String description, String imageUrl, String postType, int viewCount, int likeCount) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.postType = postType;
        this.viewCount = viewCount;
        this.likeCount = likeCount;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getPostType() {
        return postType;
    }

    public int getViewCount() {
        return viewCount;
    }

    public int getLikeCount() {
        return likeCount;
    }

    // Parcelable Implementation
    protected Post(Parcel in) {
        id = in.readString();
        title = in.readString();
        description = in.readString();
        imageUrl = in.readString();
        postType = in.readString();
        viewCount = in.readInt();
        likeCount = in.readInt();
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

    @Override
    public int describeContents() {
        return 0;
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
    }

}
