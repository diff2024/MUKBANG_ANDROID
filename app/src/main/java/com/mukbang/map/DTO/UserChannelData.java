package com.mukbang.map.DTO;

import com.google.gson.annotations.SerializedName;

public class UserChannelData {
    @SerializedName("_user_id")
    private String userId;
    @SerializedName("_user_android_id")
    private String userAndroidId;
    @SerializedName("_user_channel_id")
    private String userChannelId;
    @SerializedName("_user_channel_name")
    private String userChannelName;
    @SerializedName("_user_channel_color")
    private String userChannelColor;

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserAndroidId() {
        return userAndroidId;
    }
    public void setUserAndroidId(String userAndroidId){
        this.userAndroidId = userAndroidId;
    }

    public String getUserChannelId() {
        return userChannelId;
    }
    public void setUserChannelId(String userChannelId) { this.userChannelId = userChannelId; }

    public String getUserChannelName() {
        return userChannelName;
    }
    public void setUserChannelName(String userChannelName) { this.userChannelName = userChannelName; }

    public String getUserChannelColor() {
        return userChannelColor;
    }
    public void setUserChannelColor(String userChannelColor) { this.userChannelColor = userChannelColor; }
}
