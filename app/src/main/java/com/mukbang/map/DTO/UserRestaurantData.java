package com.mukbang.map.DTO;

import com.google.gson.annotations.SerializedName;

public class UserRestaurantData {
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
    @SerializedName("_user_restaurant_id")
    private String userRestaurantId;
    @SerializedName("_user_restaurant_name")
    private String userRestaurantName;
    @SerializedName("_user_restaurant_address")
    private String userRestaurantAddress;
    @SerializedName("_user_restaurant_latitude")
    private String userRestaurantLatitude;
    @SerializedName("_user_restaurant_longitude")
    private String userRestaurantLongitude;
    @SerializedName("_user_restaurant_youtube_id")
    private String userRestaurantYoutubeId;
    @SerializedName("_user_restaurant_navermap_id")
    private String userRestaurantNavermapId;
    @SerializedName("_user_restaurant_kakaomap_id")
    private String userRestaurantKakaomapId;
    @SerializedName("_user_restaurant_tmap_id")
    private String userRestaurantTmapId;
    @SerializedName("_user_restaurant_review_star_point")
    private String userRestaurantReviewStarPoint;
    @SerializedName("_user_restaurant_review_visitor_count")
    private String userRestaurantReviewVisitorCount;
    @SerializedName("_user_restaurant_review_blog_count")
    private String userRestaurantReviewBlogCount;

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

    public String getUserRestaurantId() {
        return userRestaurantId;
    }
    public void setUserRestaurantId(String userRestaurantId) { this.userRestaurantId = userRestaurantId; }

    public String getUserRestaurantName() {
        return userRestaurantName;
    }
    public void setUserRestaurantName(String userRestaurantName) { this.userRestaurantName = userRestaurantName; }

    public String getUserRestaurantAddress() {
        return userRestaurantAddress;
    }
    public void setUserRestaurantAddress(String userRestaurantAddress) { this.userRestaurantAddress = userRestaurantAddress; }

    public String getUserRestaurantLatitude() {
        return userRestaurantLatitude;
    }
    public void setUserRestaurantLatitude(String userRestaurantLatitude) { this.userRestaurantLatitude = userRestaurantLatitude; }

    public String getUserRestaurantLongitude() {
        return userRestaurantLongitude;
    }
    public void setUserRestaurantLongitude(String userRestaurantLongitude) { this.userRestaurantLongitude = userRestaurantLongitude; }

    public String getUserRestaurantYoutubeId() {
        return userRestaurantYoutubeId;
    }
    public void setUserRestaurantYoutubeId(String userRestaurantYoutubeId) { this.userRestaurantYoutubeId = userRestaurantYoutubeId; }

    public String getUserRestaurantNavermapId() {
        return userRestaurantNavermapId;
    }
    public void setUserRestaurantNavermapId(String userRestaurantNavermapId) { this.userRestaurantNavermapId = userRestaurantNavermapId; }

    public String getUserRestaurantKakaomapId() {
        return userRestaurantKakaomapId;
    }
    public void setUserRestaurantKakaomapId(String userRestaurantKakaomapId) { this.userRestaurantKakaomapId = userRestaurantKakaomapId; }

    public String getUserRestaurantTmapId() {
        return userRestaurantTmapId;
    }
    public void setUserRestaurantTmapId(String userRestaurantTmapId) { this.userRestaurantTmapId = userRestaurantTmapId; }

    public String getUserRestaurantReviewStarPoint() {
        return userRestaurantReviewStarPoint;
    }
    public void setUserRestaurantReviewStarPoint(String userRestaurantReviewStarPoint) { this.userRestaurantReviewStarPoint = userRestaurantReviewStarPoint; }

    public String getUserRestaurantReviewVisitorCount() {
        return userRestaurantReviewVisitorCount;
    }
    public void setUserRestaurantReviewVisitorCount(String userRestaurantReviewVisitorCount) { this.userRestaurantReviewVisitorCount = userRestaurantReviewVisitorCount; }

    public String getUserRestaurantReviewBlogCount() {
        return userRestaurantReviewBlogCount;
    }
    public void setUserRestaurantReviewBlogCount(String userRestaurantReviewBlogCount) { this.userRestaurantReviewBlogCount = userRestaurantReviewBlogCount; }
}
