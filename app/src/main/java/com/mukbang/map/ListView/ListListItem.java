package com.mukbang.map.ListView;

public class ListListItem {
    String channelId;
    String channelName;
    String restaurantId;
    String restaurantName;
    String restaurantDistance;
    String restaurantAddress;

    public ListListItem(String channelId, String channelName, String restaurantId, String restaurantName, String restaurantDistance, String restaurantAddress) {
        this.channelId = channelId;
        this.channelName = channelName;
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.restaurantDistance = restaurantDistance;
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

    public void setRestaurantDistance(String restaurantDistance){
        this.restaurantDistance = restaurantDistance;
    }

    public String getRestaurantDistance() {
        return restaurantDistance;
    }

    public void setRestaurantAddress(String restaurantAddress){
        this.restaurantAddress = restaurantAddress;
    }

    public String getRestaurantAddress() {
        return restaurantAddress;
    }

    @Override
    public String toString(){
        return "ListItem{"+ "channelId='" + channelId + '\'' + ", channelName='" + channelName + '\'' + ", restaurantId='" + restaurantId + '\''  + ", restaurantName='" + restaurantName + '\'' + ", restaurantDistance='" + restaurantDistance + '\'' + ", restaurantAddress='" + restaurantAddress + '\'' + '}';
    }
}
