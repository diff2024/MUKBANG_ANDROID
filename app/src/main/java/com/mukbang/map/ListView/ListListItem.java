package com.mukbang.map.ListView;

public class ListListItem {
    String channelId;
    String channelName;
    String restaurantId;
    String restaurantName;
    String restaurantDistance;
    String restaurantAddress;
    String restaurantNavermapId;
    String restaurantKakaomapId;
    String restaurantLatitude;
    String restaurantLongitude;

    public ListListItem(String channelId, String channelName, String restaurantId, String restaurantName, String restaurantDistance, String restaurantAddress, String restaurantNavermapId, String restaurantKakaomapId, String restaurantLatitude, String restaurantLongitude) {
        this.channelId = channelId;
        this.channelName = channelName;
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.restaurantDistance = restaurantDistance;
        this.restaurantAddress = restaurantAddress;
        this.restaurantNavermapId = restaurantNavermapId;
        this.restaurantKakaomapId = restaurantKakaomapId;
        this.restaurantLatitude = restaurantLatitude;
        this.restaurantLongitude = restaurantLongitude;
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

    public void setRestaurantNavermapId(String restaurantNavermapId){
        this.restaurantNavermapId = restaurantNavermapId;
    }

    public String getRestaurantNavermapId() {
        return restaurantNavermapId;
    }

    public void setRestaurantKakaomapId(String restaurantKakaomapId){
        this.restaurantKakaomapId = restaurantKakaomapId;
    }

    public String getRestaurantKakaomapId() {
        return restaurantKakaomapId;
    }

    public void setRestaurantLatitude(String restaurantLatitude){
        this.restaurantLatitude = restaurantLatitude;
    }

    public String getRestaurantLatitude() {
        return restaurantLatitude;
    }

    public void setRestaurantLongitude(String restaurantLongitude){
        this.restaurantLongitude = restaurantLongitude;
    }

    public String getRestaurantLongitude() {
        return restaurantLongitude;
    }

    @Override
    public String toString(){
        return "ListItem{"+ "channelId='" + channelId + '\'' + ", channelName='" + channelName + '\'' + ", restaurantId='" + restaurantId + '\''  + ", restaurantName='" + restaurantName + '\'' + ", restaurantDistance='" + restaurantDistance + '\'' + ", restaurantAddress='" + restaurantAddress + '\'' + '}';
    }
}
