package com.mukbang.map.Activity;

import android.graphics.drawable.GradientDrawable;
import android.icu.text.Collator;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mukbang.map.DTO.ChannelData;
import com.mukbang.map.DTO.UserRestaurantData;
import com.mukbang.map.ListView.ListListItem;
import com.mukbang.map.ListView.ListListItemView;
import com.mukbang.map.ListView.SettingListItem;
import com.mukbang.map.ListView.SettingListItemView;
import com.mukbang.map.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        overridePendingTransition(R.anim.vertical_enter, R.anim.none);

        ListView listView = findViewById(R.id.list_listview_list);
        ListActivity.ListListItemView_Adapter adapter_listview = new ListActivity.ListListItemView_Adapter();

        System.out.println("> 내 위치(위도) : " + Double.toString(MainActivity.myLatitude));
        System.out.println("> 내 위치(경도) : " + Double.toString(MainActivity.myLongitude));
        List<UserRestaurantData> UserRestaurantList = MainActivity.UserRestaurantList;
        ArrayList<HashMap<String, Object>> DistanceList = new ArrayList<HashMap<String, Object>>();
        for(int x=0; x<UserRestaurantList.size(); x++) {
            System.out.println("============================================================================");
            System.out.println("> 채널이름 : " + UserRestaurantList.get(x).getUserChannelName());
            System.out.println("> 음식점ID : " + UserRestaurantList.get(x).getUserRestaurantId());
            System.out.println("> 음식점이름 : " + UserRestaurantList.get(x).getUserRestaurantName());
            System.out.println("> 음식점주소 : " + UserRestaurantList.get(x).getUserRestaurantAddress());
            System.out.println("> 음식점위도 : " + UserRestaurantList.get(x).getUserRestaurantLatitude());
            System.out.println("> 음식점경도 : " + UserRestaurantList.get(x).getUserRestaurantLongitude());
            String Distance = DistanceByDegreeAndroid(Double.parseDouble(UserRestaurantList.get(x).getUserRestaurantLatitude()), Double.parseDouble(UserRestaurantList.get(x).getUserRestaurantLongitude()));
            System.out.println("> 음식점직선거리 : " + Distance);

            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ChannelId", UserRestaurantList.get(x).getUserChannelId());
            map.put("ChannelName", UserRestaurantList.get(x).getUserChannelName());
            map.put("RestaurantId", UserRestaurantList.get(x).getUserRestaurantId());
            map.put("RestaurantName", UserRestaurantList.get(x).getUserRestaurantName());
            map.put("RestaurantAddress", UserRestaurantList.get(x).getUserRestaurantAddress());
            map.put("RestaurantDistance", Distance);
            DistanceList.add(map);
        }

        Collections.sort(DistanceList, new Comparator<HashMap<String, Object>>() {
            @Override
            public int compare(HashMap<String, Object> o1, HashMap<String, Object> o2) {
                Integer d1 = Integer.parseInt(String.valueOf(Math.round(Double.parseDouble((String) o1.get("RestaurantDistance"))*100)).replace(".0",""));
                Integer d2 = Integer.parseInt(String.valueOf(Math.round(Double.parseDouble((String) o2.get("RestaurantDistance"))*100)).replace(".0",""));
                return d1.compareTo(d2);
            }
        });

        for(int x=0; x<DistanceList.size(); x++){
            System.out.println("DISTANCE============================================================================DISTANCE");
            System.out.println("["+DistanceList.get(x).get("RestaurantDistance")+"] 음식점이름 : " + DistanceList.get(x).get("RestaurantName"));
            adapter_listview.addItem(new ListListItem(UserRestaurantList.get(x).getUserChannelId(), UserRestaurantList.get(x).getUserChannelName(), UserRestaurantList.get(x).getUserRestaurantId(), UserRestaurantList.get(x).getUserRestaurantName(), UserRestaurantList.get(x).getUserRestaurantAddress()));
        }
        listView.setAdapter(adapter_listview);
        /*
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

        }
        */
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

    class ListListItemView_Adapter extends BaseAdapter {
        ArrayList<ListListItem> items = new ArrayList<ListListItem>();

        @Override
        public int getCount(){
            return items.size();
        }

        public void clear(){
            items.clear();
        }

        public void removeItem(ListListItem item){
            items.remove(item);
        }
        public void addItem(ListListItem item){
            items.add(item);
        }

        @Override
        public Object getItem(int position){
            return items.get(position);
        }

        @Override
        public long getItemId(int position){
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            ListListItemView listListItemView = null;

            if(convertView == null){
                listListItemView = new ListListItemView(getApplicationContext());
            }else{
                listListItemView = (ListListItemView) convertView;
            }
            ListListItem item = items.get(position);
            listListItemView.setChannelId(item.getChannelId());
            listListItemView.setChannelName(item.getChannelName());
            listListItemView.setRestaurantId(item.getRestaurantId());
            listListItemView.setRestaurantName(item.getRestaurantName());
            listListItemView.setRestaurantAddress(item.getRestaurantAddress());

            return listListItemView;
        }
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
