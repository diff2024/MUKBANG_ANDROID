package com.mukbang.map.ListView;

public class ListListItem {
    String channelId;
    String channelName;
    String restaurantId;
    String restaurantName;
    String restaurantAddress;

    public ListListItem(String channelId, String channelName, String restaurantId, String restaurantName, String restaurantAddress) {
        this.channelId = channelId;
        this.channelName = channelName;
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.restaurantAddress = restaurantAddress;
    }

    public void setChannelId(String channelId){
        this.channelId = channelId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelName(String channelName){
        this.channelName = channelName;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setRestaurantId(String restaurantId){
        this.restaurantId = restaurantId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantName(String restaurantName){
        this.restaurantName = restaurantName;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantAddress(String restaurantAddress){
        this.restaurantAddress = restaurantAddress;
    }

    public String getRestaurantAddress() {
        return restaurantAddress;
    }

    @Override
    public String toString(){
        return "SettingListItem{"+ "channelId='" + channelId + '\'' + ", channelName='" + channelName + '\'' + ", restaurantId='" + restaurantId + '\'' + ", restaurantName='" + restaurantName + '\'' + ", restaurantAddress='" + restaurantAddress + '\'' + '}';
    }
}
