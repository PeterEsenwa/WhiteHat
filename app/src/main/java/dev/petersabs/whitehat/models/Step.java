package dev.petersabs.whitehat.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Step implements Serializable, Parcelable {

    public static final Creator<Step> CREATOR = new Creator<Step>() {
        @Override
        public Step createFromParcel(Parcel in) {
            return new Step(in);
        }

        @Override
        public Step[] newArray(int size) {
            return new Step[size];
        }
    };
    private final static long serialVersionUID = -8413313508538224168L;
    private int id = -1;
    private String shortDescription;
    private String description;
    private String videoURL;
    private String thumbnailURL;

    /**
     * No args constructor for use in serialization
     */
    public Step() {
    }

    /**
     * A single step in a Recipe
     *
     * @param videoURL         URL to explanation video for this step
     * @param description      full description of this step
     * @param id               step ID
     * @param shortDescription short description of this step
     * @param thumbnailURL     URL to thumbnail for this step
     */
    public Step(int id, String shortDescription, String description, String videoURL, String thumbnailURL) {
        this.id = id;
        this.shortDescription = shortDescription;
        this.description = description;
        this.videoURL = videoURL;
        this.thumbnailURL = thumbnailURL;
    }

    private Step(Parcel parcel) {
        this.id = parcel.readInt();
        this.shortDescription = parcel.readString();
        this.description = parcel.readString();
        this.videoURL = parcel.readString();
        this.thumbnailURL = parcel.readString();

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(description);
        dest.writeString(shortDescription);
        dest.writeString(videoURL);
        dest.writeString(thumbnailURL);
        dest.writeInt(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }

    public boolean isValid() {
        return id != -1 && description != null && !description.isEmpty() && shortDescription != null && !shortDescription.isEmpty();
    }

}
