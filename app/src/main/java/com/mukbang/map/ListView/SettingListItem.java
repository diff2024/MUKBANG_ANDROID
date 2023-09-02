package com.mukbang.map.ListView;

public class SettingListItem {
    String channelId;
    String channelName;
    String channelMarkerColor;

    public SettingListItem(String channelId, String channelName, String channelMarkerColor) {
        this.channelId = channelId;
        this.channelName = channelName;
        this.channelMarkerColor = channelMarkerColor;
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

    public void setChannelMarkerColor(String channelMarkerColor){
        this.channelMarkerColor = channelMarkerColor;
    }

    public String getChannelMarkerColor() {
        return channelMarkerColor;
    }

    @Override
    public String toString(){
        return "SettingListItem{"+ "channelId='" + channelId + '\'' + ", channelName='" + channelName + '\'' + ", channelMarkerColor='" + channelMarkerColor + '\'' + '}';
    }
}
