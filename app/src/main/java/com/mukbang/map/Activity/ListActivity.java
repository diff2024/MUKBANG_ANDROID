package com.mukbang.map.Activity;

import android.location.Location;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.mukbang.map.DTO.ChannelData;
import com.mukbang.map.DTO.UserRestaurantData;
import com.mukbang.map.ListView.SettingListItem;
import com.mukbang.map.R;

import java.util.List;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        overridePendingTransition(R.anim.vertical_enter, R.anim.none);

        System.out.println("> 내 위치(위도) : " + Double.toString(MainActivity.myLatitude));
        System.out.println("> 내 위치(경도) : " + Double.toString(MainActivity.myLongitude));
        List<UserRestaurantData> UserRestaurantList = MainActivity.UserRestaurantList;
        for(int x=0; x<UserRestaurantList.size(); x++) {
            System.out.println("============================================================================");
            System.out.println("> 채널ID : " + UserRestaurantList.get(x).getUserChannelId());
            System.out.println("> 채널이름 : " + UserRestaurantList.get(x).getUserChannelName());
            System.out.println("> 음식점ID : " + UserRestaurantList.get(x).getUserRestaurantId());
            System.out.println("> 음식점이름 : " + UserRestaurantList.get(x).getUserRestaurantName());
            System.out.println("> 음식점주소 : " + UserRestaurantList.get(x).getUserRestaurantAddress());
            System.out.println("> 음식점위도 : " + UserRestaurantList.get(x).getUserRestaurantLatitude());
            System.out.println("> 음식점경도 : " + UserRestaurantList.get(x).getUserRestaurantLongitude());
            String Distance = DistanceByDegreeAndroid(Double.parseDouble(UserRestaurantList.get(x).getUserRestaurantLatitude()), Double.parseDouble(UserRestaurantList.get(x).getUserRestaurantLongitude()));
            System.out.println("> 음식점직선거리 : " + Distance);
        }
    }

    public String DistanceByDegreeAndroid(double end_latitude, double end_longitude){
        Location startPos = new Location("PointA");
        Location endPos = new Location("PointB");

        startPos.setLatitude(MainActivity.myLatitude);
        startPos.setLongitude(MainActivity.myLongitude);
        endPos.setLatitude(end_latitude);
        endPos.setLongitude(end_longitude);

        double dob_distance = startPos.distanceTo(endPos)/1000;
        String distance = Double.toString(Math.round(dob_distance * 10) / 10.0);
        distance = distance.replace(".0", "");
        return distance;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.none, R.anim.vertical_exit);
    }
}
