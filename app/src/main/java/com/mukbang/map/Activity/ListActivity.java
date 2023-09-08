package com.mukbang.map.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.GradientDrawable;
import android.icu.text.Collator;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
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

        List<UserRestaurantData> UserRestaurantList = MainActivity.UserRestaurantList;
        ArrayList<HashMap<String, Object>> DistanceList = new ArrayList<HashMap<String, Object>>();
        for(int x=0; x<UserRestaurantList.size(); x++) {
            String Distance = DistanceByDegreeAndroid(Double.parseDouble(UserRestaurantList.get(x).getUserRestaurantLatitude()), Double.parseDouble(UserRestaurantList.get(x).getUserRestaurantLongitude()));            System.out.println("> 음식점직선거리 : " + Distance);

            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ChannelId", UserRestaurantList.get(x).getUserChannelId());
            map.put("ChannelName", UserRestaurantList.get(x).getUserChannelName());
            map.put("RestaurantId", UserRestaurantList.get(x).getUserRestaurantId());
            map.put("RestaurantName", UserRestaurantList.get(x).getUserRestaurantName());
            map.put("RestaurantAddress", UserRestaurantList.get(x).getUserRestaurantAddress());
            map.put("RestaurantDistance", Distance);
            map.put("RestaurantNavermapId", UserRestaurantList.get(x).getUserRestaurantNavermapId());
            map.put("RestaurantKakaomapId", UserRestaurantList.get(x).getUserRestaurantKakaomapId());
            map.put("RestaurantLatitude", UserRestaurantList.get(x).getUserRestaurantLatitude());
            map.put("RestaurantLongitude", UserRestaurantList.get(x).getUserRestaurantLongitude());
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
            adapter_listview.addItem(new ListListItem((String) DistanceList.get(x).get("ChannelId"), (String) DistanceList.get(x).get("ChannelName"), (String) DistanceList.get(x).get("RestaurantId")
                    , (String) DistanceList.get(x).get("RestaurantName"), (String) DistanceList.get(x).get("RestaurantDistance") + "km", (String) DistanceList.get(x).get("RestaurantAddress")
                    , (String) DistanceList.get(x).get("RestaurantNavermapId"), (String) DistanceList.get(x).get("RestaurantKakaomapId"), (String) DistanceList.get(x).get("RestaurantLatitude"), (String) DistanceList.get(x).get("RestaurantLongitude")));
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

            final Context context = parent.getContext();
            if (convertView == null) {
                //LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                //convertView = inflater.inflate(R.layout.activity_list_listview, parent, false);
                //[황영철] 이렇게 해야 처음 로드되지마자 버튼 클릭이 된다.
                convertView = listListItemView;
            }

            ListListItem item = items.get(position);
            listListItemView.setChannelId(item.getChannelId());
            listListItemView.setChannelName(item.getChannelName());
            listListItemView.setRestaurantId(item.getRestaurantId());
            listListItemView.setRestaurantName(item.getRestaurantName());
            listListItemView.setRestaurantDistance(item.getRestaurantDistance());
            listListItemView.setRestaurantAddress(item.getRestaurantAddress());

            Button naverBtn = (Button) convertView.findViewById(R.id.btn_naver_map);
            Button kakaoBtn = (Button) convertView.findViewById(R.id.btn_kakao_map);
            Button tBtn = (Button) convertView.findViewById(R.id.btn_t_map);
            naverBtn.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url = "nmap://place?id="+items.get(position).getRestaurantNavermapId();
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);

                    List<ResolveInfo> list = getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
                    if(list == null || list.isEmpty()) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.nhn.android.nmap")));
                    }else{
                        startActivity(intent);
                    }
                }
            });
            kakaoBtn.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url = "kakaomap://place?id="+items.get(position).getRestaurantKakaomapId();
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);

                    List<ResolveInfo> list = getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
                    if(list == null || list.isEmpty()) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=net.daum.android.map")));
                    }else{
                        startActivity(intent);
                    }
                }
            });
            tBtn.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String url = "tmap://route?goalx="+items.get(position).getRestaurantLongitude()+"&goaly="+items.get(position).getRestaurantLatitude()+"&goalname="+items.get(position).getRestaurantName();
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);

                    List<ResolveInfo> list = getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
                    if(list == null || list.isEmpty()) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.skt.tmap.ku")));
                    }else{
                        startActivity(intent);
                    }
                }
            });

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
