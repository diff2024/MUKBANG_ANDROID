package com.mukbang.map.DTO;

import android.icu.text.Collator;

import com.google.gson.annotations.SerializedName;

import java.util.Comparator;

public class ChannelData {
    @SerializedName("_channel_id")
    private String channelId;
    @SerializedName("_channel_name")
    private String channelName;
    @SerializedName("_channel_marker_color")
    private String channelMarkerColor;
    @SerializedName("_channel_button_width")
    private String channelButtonWidth;
    @SerializedName("_channel_youtube_id")
    private String channelYoutubeId;
    @SerializedName("_channel_youtube_subscribe_count")
    private String channelYoutubeSubscribeCount;
    @SerializedName("_channel_youtube_video_count")
    private String channelYoutubeVideoCount;

    public String getChannelId() {
        return channelId;
    }
    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }
    public void setChannelName(String channelName){
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

    public String getChannelYoutubeId() {
        return channelYoutubeId;
    }
    public void setChannelYoutubeId(String channelYoutubeId) {
        this.channelYoutubeId = channelYoutubeId;
    }

    public String getChannelYoutubeSubscribeCount() {
        return channelYoutubeSubscribeCount;
    }
    public void setChannelYoutubeSubscribeCount(String channelYoutubeSubscribeCount) {
        this.channelYoutubeSubscribeCount = channelYoutubeSubscribeCount;
    }

    public String getChannelYoutubeVideoCount() {
        return channelYoutubeVideoCount;
    }
    public void setChannelYoutubeVideoCount(String channelYoutubeVideoCount) {
        this.channelYoutubeVideoCount = channelYoutubeVideoCount;
    }
}
