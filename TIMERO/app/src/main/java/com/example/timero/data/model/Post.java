package com.example.timero.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class Post implements Parcelable {
    @SerializedName("id") private String id;
    @SerializedName("title") private String title;
    @SerializedName("description") private String description;
    @SerializedName("imageurl") private String imageurl;
    @SerializedName("posttype") private String postType;
    @SerializedName("viewcount") private int viewCount;
    @SerializedName("likecount") private int likeCount;
    @SerializedName("userid") private String userid;
    @SerializedName("Questions") private List<Question> questions;

    public Post(String id, String title, String description, String imageurl, String postType, int viewCount, int likeCount, String userid, List<Question> questions) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.imageurl = imageurl;
        this.postType = postType;
        this.viewCount = viewCount;
        this.likeCount = likeCount;
        this.userid = userid;
        this.questions = questions;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getImageUrl() { return imageurl; }
    public String getPostType() { return postType; }
    public int getViewCount() { return viewCount; }
    public int getLikeCount() { return likeCount; }
    public String getUserid() { return userid; }
    public List<Question> getQuestions() { return questions; }

    public void setId(String id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setImageurl(String imageurl) { this.imageurl = imageurl; }
    public void setPostType(String postType) { this.postType = postType; }
    public void setViewCount(int viewCount) { this.viewCount = viewCount; }
    public void setLikeCount(int likeCount) { this.likeCount = likeCount; }
    public void setUserid(String userid) { this.userid = userid; }
    public void setQuestions(List<Question> questions) { this.questions = questions; }

    protected Post(Parcel in) {
        id = in.readString();
        title = in.readString();
        description = in.readString();
        imageurl = in.readString();
        postType = in.readString();
        viewCount = in.readInt();
        likeCount = in.readInt();
        userid = in.readString();
        questions = in.createTypedArrayList(Question.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(imageurl);
        dest.writeString(postType);
        dest.writeInt(viewCount);
        dest.writeInt(likeCount);
        dest.writeString(userid);
        dest.writeTypedList(questions);
    }

    @Override
    public int describeContents() { return 0; }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) { return new Post(in); }
        @Override
        public Post[] newArray(int size) { return new Post[size]; }
    };
}