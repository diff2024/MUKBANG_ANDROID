package com.mukbang.map.Activity;

import static androidx.core.content.ContentProviderCompat.requireContext;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.icu.text.Collator;
import android.icu.text.StringPrepParseException;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import com.mukbang.map.DTO.ChannelData;
import com.mukbang.map.ListView.SettingListItem;
import com.mukbang.map.ListView.SettingListItemView;
import com.mukbang.map.R;
import com.mukbang.map.Setting.BackAPI;
import com.mukbang.map.Setting.RetrofitClient;

import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import okhttp3.ResponseBody;
import petrov.kristiyan.colorpicker.ColorPicker;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SettingActivity extends AppCompatActivity {
    Resources res;
    private ListView list;
    String[] orderItems = {"가나다 순", "구독자 순"};

    Retrofit retrofit = RetrofitClient.getInstance();
    BackAPI backAPI = retrofit.create(BackAPI.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        overridePendingTransition(R.anim.horizon_enter, R.anim.none);

        final GradientDrawable drawable = (GradientDrawable) ContextCompat.getDrawable(this, R.drawable.setting_btn);

        ListView listView = findViewById(R.id.setting_listview_list);
        SettingListItemView_Adapter adapter_listview = new SettingListItemView_Adapter();

        List<ChannelData> UserSettingChannelList = MainActivity.UserSettingChannelList;
        Collections.sort(UserSettingChannelList, sortByName);

        for(int x=0; x<UserSettingChannelList.size(); x++) {
            adapter_listview.addItem(new SettingListItem(UserSettingChannelList.get(x).getChannelId(), UserSettingChannelList.get(x).getChannelName(), UserSettingChannelList.get(x).getChannelMarkerColor()));
        }
        listView.setAdapter(adapter_listview);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                View btn_channelColor = findViewById(R.id.setting_listview_list);
                final SettingListItem item = (SettingListItem) adapter_listview.getItem(i);
                String item_ChannelId = item.getChannelId();
                String item_ChannelName = item.getChannelName();
                String item_ChannelMarkerColor = item.getChannelMarkerColor();

                final ColorPicker colorPicker = new ColorPicker(SettingActivity.this);
                ArrayList<String> colors = new ArrayList<>();

                res = getResources();
                String[] array_color = res.getStringArray(R.array.array_color);
                for(int x=0; x<array_color.length; x++){
                    colors.add(array_color[x]);
                }

                colorPicker
                .setDefaultColorButton(Color.parseColor("#"+item_ChannelMarkerColor))
                .setColors(colors)
                .setColumns(5)
                .setTitle("마커 색상 선택")
                .setRoundColorButton(true)
                .disableDefaultButtons(true)
                .setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
                    @Override
                    public void onChooseColor(int position, int color) {
                    }

                    @Override
                    public void onCancel() {
                    }
                })
                .addListenerButton("미 사용", new ColorPicker.OnButtonListener() {
                    @Override
                    public void onClick(View v, int position, int color) {
                        drawable.setColor(Color.parseColor("#FFFFFF"));
                        (view.findViewById(R.id.btn_channelColor)).setBackground(getResources().getDrawable(R.drawable.setting_btn));

                        backAPI.ChannelDelete(IntroActivity.USER_ID, item_ChannelId).enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                if (response.isSuccessful()) {
                                    ResponseBody body = response.body();
                                    if (body != null) {
                                        backAPI.UserSettingChannelList(IntroActivity.USER_ID).enqueue(new Callback<List<ChannelData>>() {

                                            @Override
                                            public void onResponse(Call<List<ChannelData>> call, Response<List<ChannelData>> response) {
                                                if (response.isSuccessful()) {
                                                    MainActivity.UserSettingChannelList = response.body();
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<List<ChannelData>> call, Throwable t) {
                                                t.printStackTrace();
                                            }
                                        });
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {

                            }
                        });

                        colorPicker.dismissDialog();
                    }
                })
                .addListenerButton("선택", new ColorPicker.OnButtonListener() {
                    @Override
                    public void onClick(View v, int position, int color) {
                        drawable.setColor(color);
                        (view.findViewById(R.id.btn_channelColor)).setBackground(getResources().getDrawable(R.drawable.setting_btn));

                        String _android_id = IntroActivity.ANDROID_ID;
                        colorPicker.dismissDialog();

                        if((String.format("#%06X", (0xFFFFFF & color))).replace("#","").equals("FFFFFF")){
                            backAPI.ChannelDelete(IntroActivity.USER_ID, item_ChannelId).enqueue(new Callback<ResponseBody>() {
                                @Override
                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                    if (response.isSuccessful()) {
                                        ResponseBody body = response.body();
                                        if (body != null) {
                                            backAPI.UserSettingChannelList(IntroActivity.USER_ID).enqueue(new Callback<List<ChannelData>>() {

                                                @Override
                                                public void onResponse(Call<List<ChannelData>> call, Response<List<ChannelData>> response) {
                                                    if (response.isSuccessful()) {
                                                        MainActivity.UserSettingChannelList = response.body();
                                                    }
                                                }

                                                @Override
                                                public void onFailure(Call<List<ChannelData>> call, Throwable t) {
                                                    t.printStackTrace();
                                                }
                                            });
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<ResponseBody> call, Throwable t) {

                                }
                            });
                        }else {
                            backAPI.ChannelColorUpdate(IntroActivity.USER_ID, item_ChannelId, (String.format("#%06X", (0xFFFFFF & color))).replace("#", "")).enqueue(new Callback<ResponseBody>() {
                                @Override
                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                    if (response.isSuccessful()) {
                                        ResponseBody body = response.body();
                                        if (body != null) {
                                            backAPI.UserSettingChannelList(IntroActivity.USER_ID).enqueue(new Callback<List<ChannelData>>() {

                                                @Override
                                                public void onResponse(Call<List<ChannelData>> call, Response<List<ChannelData>> response) {
                                                    if (response.isSuccessful()) {
                                                        MainActivity.UserSettingChannelList = response.body();
                                                    }
                                                }

                                                @Override
                                                public void onFailure(Call<List<ChannelData>> call, Throwable t) {
                                                    t.printStackTrace();
                                                }
                                            });
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<ResponseBody> call, Throwable t) {

                                }
                            });
                        }

                        ((SettingListItem) adapter_listview.getItem(i)).setChannelMarkerColor(Integer.toString(color));
                    }
                })
                .addListenerButton("닫기", new ColorPicker.OnButtonListener() {
                    @Override
                    public void onClick(View v, int position, int color) {
                        colorPicker.dismissDialog();
                    }
                }).show();
            }
        });

        Spinner spinner = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter_spinner = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, orderItems
        );
        adapter_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter_spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                adapter_listview.clear();

                if(position == 0){
                    Collections.sort(UserSettingChannelList, sortByName);
                }else if(position == 1){
                    Collections.sort(UserSettingChannelList, sortBySubscribe);
                }

                for(int x=0; x<UserSettingChannelList.size(); x++) {
                    adapter_listview.addItem(new SettingListItem(UserSettingChannelList.get(x).getChannelId(), UserSettingChannelList.get(x).getChannelName(), UserSettingChannelList.get(x).getChannelMarkerColor()));
                }
                adapter_listview.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private final static Comparator<ChannelData> sortByName = new Comparator<ChannelData>() {
        @Override
        public int compare(ChannelData object1, ChannelData object2) {
            return Collator.getInstance().compare(object1.getChannelName(), object2.getChannelName());
        }
    };

    private final static Comparator<ChannelData> sortBySubscribe = new Comparator<ChannelData>() {
        @Override
        public int compare(ChannelData object1, ChannelData object2) {
            return Integer.compare(Integer.parseInt(object2.getChannelYoutubeSubscribeCount()), Integer.parseInt(object1.getChannelYoutubeSubscribeCount()));
        }
    };

    class SettingListItemView_Adapter extends BaseAdapter {
        ArrayList<SettingListItem> items = new ArrayList<SettingListItem>();

        @Override
        public int getCount(){
            return items.size();
        }

        public void clear(){
            items.clear();
        }

        public void removeItem(SettingListItem item){
            items.remove(item);
        }
        public void addItem(SettingListItem item){
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
            SettingListItemView settingListItemView = null;

            if(convertView == null){
                settingListItemView = new SettingListItemView(getApplicationContext());
            }else{
                settingListItemView = (SettingListItemView) convertView;
            }
            SettingListItem item = items.get(position);
            settingListItemView.setChannelId(item.getChannelId());
            settingListItemView.setChannelName(item.getChannelName());

            TextView tv = (TextView)settingListItemView.findViewById(R.id.btn_channelColor);
            GradientDrawable bgShape = (GradientDrawable) tv.getBackground();
            if((item.getChannelMarkerColor()).equals("FFFFFF")){
                bgShape.setColor(getResources().getColor(R.color.FFFFFFFF));
            }else if((item.getChannelMarkerColor()).equals("000000")){
                bgShape.setColor(getResources().getColor(R.color.FF000000));
            }else if((item.getChannelMarkerColor()).equals("DC143C")){
                bgShape.setColor(getResources().getColor(R.color.FFDC143C));
            }else if((item.getChannelMarkerColor()).equals("FF3333")){
                bgShape.setColor(getResources().getColor(R.color.FFFF3333));
            }else if((item.getChannelMarkerColor()).equals("E8472E")){
                bgShape.setColor(getResources().getColor(R.color.FFE8472E));
            }else if((item.getChannelMarkerColor()).equals("FF8C8C")){
                bgShape.setColor(getResources().getColor(R.color.FFFF8C8C));
            }else if((item.getChannelMarkerColor()).equals("F77F23")){
                bgShape.setColor(getResources().getColor(R.color.FFF77F23));
            }else if((item.getChannelMarkerColor()).equals("E38436")){
                bgShape.setColor(getResources().getColor(R.color.FFE38436));
            }else if((item.getChannelMarkerColor()).equals("F0E090")){
                bgShape.setColor(getResources().getColor(R.color.FFF0E090));
            }else if((item.getChannelMarkerColor()).equals("98B84D")){
                bgShape.setColor(getResources().getColor(R.color.FF98B84D));
            }else if((item.getChannelMarkerColor()).equals("A3D17B")){
                bgShape.setColor(getResources().getColor(R.color.FFA3D17B));
            }else if((item.getChannelMarkerColor()).equals("6ECF69")){
                bgShape.setColor(getResources().getColor(R.color.FF6ECF69));
            }else if((item.getChannelMarkerColor()).equals("6EC48C")){
                bgShape.setColor(getResources().getColor(R.color.FF6EC48C));
            }else if((item.getChannelMarkerColor()).equals("5AA392")){
                bgShape.setColor(getResources().getColor(R.color.FF5AA392));
            }else if((item.getChannelMarkerColor()).equals("4B8BBF")){
                bgShape.setColor(getResources().getColor(R.color.FF4B8BBF));
            }else if((item.getChannelMarkerColor()).equals("6A88D4")){
                bgShape.setColor(getResources().getColor(R.color.FF6A88D4));
            }else if((item.getChannelMarkerColor()).equals("736DC9")){
                bgShape.setColor(getResources().getColor(R.color.FF736DC9));
            }else if((item.getChannelMarkerColor()).equals("864FBD")){
                bgShape.setColor(getResources().getColor(R.color.FF864FBD));
            }else if((item.getChannelMarkerColor()).equals("D169CA")){
                bgShape.setColor(getResources().getColor(R.color.FFD169CA));
            }else if((item.getChannelMarkerColor()).equals("CC99FF")){
                bgShape.setColor(getResources().getColor(R.color.FFCC99FF));
            }else{
                bgShape.setColor(getResources().getColor(R.color.FFFFFFFF));
            }
            return settingListItemView;
        }
    }
    @Override
    public void onStart() {
        backAPI.UserSettingChannelList(IntroActivity.USER_ID).enqueue(new Callback<List<ChannelData>>() {

            @Override
            public void onResponse(Call<List<ChannelData>> call, Response<List<ChannelData>> response) {
                if (response.isSuccessful()) {
                    MainActivity.UserSettingChannelList = response.body();
                }
            }

            @Override
            public void onFailure(Call<List<ChannelData>> call, Throwable t) {
                t.printStackTrace();
            }
        });
        super.onStart();
    }
    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.none, R.anim.horizon_exit);
    }
}
