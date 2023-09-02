package com.mukbang.map.Setting;

import com.mukbang.map.DTO.*;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface BackAPI {
    @GET("/Read/MarkerList")
    Call<List<MarkerData>> MarkerList(@Query("channel_id") String channel_id);
    @GET("/Read/ChannelList")
    Call<List<ChannelData>> ChannelList(@Query("channel_id") String channel_id);
    @GET("/Read/RestaurantList")
    Call<List<RestaurantData>> RestaurantList(@Query("restaurant_id") String restaurant_id);
    @POST("/Read/UserChannelList")
    Call<List<UserChannelData>> UserChannelList(@Query("user_id") String user_id);
    @POST("/Read/UserRestaurantList")
    Call<List<UserRestaurantData>> UserRestaurantList(@Query("user_id") String user_id, @Query("channel_id") String channel_id);
    @POST("/Read/UserSettingChannelList")
    Call<List<ChannelData>> UserSettingChannelList(@Query("user_id") String user_id);
    @POST("/Read/UserID")
    Call<String> UserID(@Query("android_id") String android_id);
    @POST("/Update/ChannelColorUpdate")
    Call<ResponseBody> ChannelColorUpdate(@Query("user_id") String user_id, @Query("channel_id") String channel_id, @Query("color") String color);
    @POST("/Delete/ChannelDelete")
    Call<ResponseBody> ChannelDelete(@Query("user_id") String user_id, @Query("channel_id") String channel_id);
}
