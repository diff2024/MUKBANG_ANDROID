package com.mukbang.map.DTO;

import com.google.gson.annotations.SerializedName;

public class MarkerData {
    @SerializedName("_channel_id")
    private String channelId;
    @SerializedName("_channel_name")
    private String channelName;
    @SerializedName("_channel_marker_color")
    private String channelMarkerColor;
    @SerializedName("_channel_button_width")
    private String channelButtonWidth;
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

    public String getChannelId() {
        return channelId;
    }
    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }
    public String getChannelName() {
        return channelName;
    }
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelMarkerColor() {
        return channelMarkerColor;
    }
    public void setChannelMarkerColor(String channelMarkerColor) {
        this.channelMarkerColor = channelMarkerColor;
    }

    public String getChannelButtonWidth() {
        return channelButtonWidth;
    }
    public void setChannelButtonWidth(String channelButtonWidth) {
        this.channelButtonWidth = channelButtonWidth;
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
}
