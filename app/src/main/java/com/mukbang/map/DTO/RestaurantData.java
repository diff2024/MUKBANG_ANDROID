package com.mukbang.map.DTO;

import com.google.gson.annotations.SerializedName;

public class RestaurantData {
    @SerializedName("_channel_id")
    private String channelId;
    @SerializedName("_restaurant_id")
    private String restaurantId;
    @SerializedName("_restaurant_name")
    private String restaurantName;
    @SerializedName("_restaurant_address")
    private String restaurantAddress;
    @SerializedName("_restaurant_latitude")
    private String restaurantLatitude;
    @SerializedName("_restaurant_longitude")
    private String restaurantLongitude;
    @SerializedName("_restaurant_youtube_id")
    private String restaurantYoutubeId;
    @SerializedName("_restaurant_navermap_id")
    private String restaurantNavermapId;
    @SerializedName("_restaurant_kakaomap_id")
    private String restaurantKakaomapId;
    @SerializedName("_restaurant_tmap_id")
    private String restaurantTmapId;

    @SerializedName("_restaurant_review_star_point")
    private String restaurantReviewStarPoint;
    @SerializedName("_restaurant_review_visitor_count")
    private String restaurantReviewVisitorCount;
    @SerializedName("_restaurant_review_blog_count")
    private String restaurantReviewBlogCount;

    public String getChannelId() {
        return channelId;
    }
    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }
    public String getRestaurantId() {
        return restaurantId;
    }
    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }
    public String getRestaurantLatitude() {
        return restaurantLatitude;
    }
    public void setRestaurantLatitude(String restaurantLatitude) {
        this.restaurantLatitude = restaurantLatitude;
    }
    public String getRestaurantLongitude() {
        return restaurantLongitude;
    }
    public void setRestaurantLongitude(String restaurantLongitude) {
        this.restaurantLongitude = restaurantLongitude;
    }
    public String getRestaurantName() {
        return restaurantName;
    }
    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }
    public String getRestaurantAddress() {
        return restaurantAddress;
    }
    public void setRestaurantAddress(String restaurantAddress) {
        this.restaurantAddress = restaurantAddress;
    }

    public String getRestaurantYoutubeId() {
        return restaurantYoutubeId;
    }
    public void setRestaurantYoutubeId(String restaurantYoutubeId) {
        this.restaurantYoutubeId = restaurantYoutubeId;
    }
    public String getRestaurantNavermapId() {
        return restaurantNavermapId;
    }
    public void setRestaurantNavermapId(String restaurantNavermapId) {
        this.restaurantNavermapId = restaurantNavermapId;
    }
    public String getRestaurantKakaomapId() {
        return restaurantKakaomapId;
    }
    public void setRestaurantKakaomapId(String restaurantKakaomapId) {
        this.restaurantKakaomapId = restaurantKakaomapId;
    }
    public String getRestaurantTmapId() {
        return restaurantTmapId;
    }
    public void setRestaurantTmapId(String restaurantTmapId) {
        this.restaurantTmapId = restaurantTmapId;
    }
    public String getRestaurantReviewStarPoint() {
        return restaurantReviewStarPoint;
    }
    public void setRestaurantReviewStarPoint(String restaurantReviewStarPoint) {
        this.restaurantReviewStarPoint = restaurantReviewStarPoint;
    }
    public String getRestaurantReviewVisitorCount() {
        return restaurantReviewVisitorCount;
    }
    public void setRestaurantReviewVisitorCount(String restaurantReviewVisitorCount) {
        this.restaurantReviewVisitorCount = restaurantReviewVisitorCount;
    }
    public String getRestaurantReviewBlogCount() {
        return restaurantReviewBlogCount;
    }
    public void setRestaurantReviewBlogCount(String restaurantReviewBlogCount) {
        this.restaurantReviewBlogCount = restaurantReviewBlogCount;
    }
}
