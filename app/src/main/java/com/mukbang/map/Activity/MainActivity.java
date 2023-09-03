package com.mukbang.map.Activity;

import static java.security.AccessController.getContext;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.LocationListener;
import com.mukbang.map.DTO.ChannelData;
import com.mukbang.map.DTO.MarkerData;
import com.mukbang.map.DTO.RestaurantData;
import com.mukbang.map.DTO.UserChannelData;
import com.mukbang.map.DTO.UserRestaurantData;
import com.mukbang.map.R;
import com.mukbang.map.Setting.BackAPI;
import com.mukbang.map.Setting.RetrofitClient;
import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraPosition;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.UiSettings;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.Overlay;
import com.naver.maps.map.util.FusedLocationSource;
import com.naver.maps.map.util.MarkerIcons;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.YouTubePlayerTracker;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.io.FileReader;
import java.io.SyncFailedException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, ActivityCompat.OnRequestPermissionsResultCallback {
    Button listBtn;
    Button naverBtn, kakaoBtn, tBtn;
    ImageButton btn_address_copy;
    //유튜브 API KEY와 동영상 ID 변수 설정
    private YouTubePlayer youTubePlayer;
    private YouTubePlayerView youTubePlayerView;
    private static String API_KEY = "AIzaSyAKBTsyj_OHZHv7SmttOwH6ILxuXXKLG80";
    private String videoId= "";
    SlidingUpPanelLayout _slide_layout;
    TextView _slide_title_name, _slide_address_name;
    ImageView _review_star;
    TextView _review_star_point, _review_star_full_point, _review_visitor, _review_blog, _review_gubun_1, _review_gubun_2;
    public static String WEBVIEW_URL = "";
    private NaverMap mNaverMap;
    public static Double myLatitude;
    public static Double myLongitude;
    private FusedLocationSource locationSource;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 100;
    private static final String[] PERMISSIONS = {
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
    };
    public static List<UserRestaurantData> UserRestaurantList;
    public static List<UserChannelData> UserChannelList;
    public static List<ChannelData> UserSettingChannelList;
    public static List<ChannelData> ChannelList;
    public static List<RestaurantData> RestaurantList;
    Button channelBtn;
    private Marker[] markers;
    private int Marker_Count = 0;
    Retrofit retrofit = RetrofitClient.getInstance();
    BackAPI backAPI = retrofit.create(BackAPI.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        _slide_layout = findViewById(R.id.main_panel);
        _slide_layout.setOverlayed(false);
        _slide_title_name = findViewById(R.id.slide_title_name);
        _slide_address_name = findViewById(R.id.slide_address_name);
        _review_star = findViewById(R.id.review_star);
        _review_star_point = findViewById(R.id.review_star_point);
        _review_star_full_point = findViewById(R.id.review_star_full_point);
        _review_visitor = findViewById(R.id.review_visitor);
        _review_blog = findViewById(R.id.review_blog);
        _review_gubun_1 = findViewById(R.id.review_gubun_1);
        _review_gubun_2 = findViewById(R.id.review_gubun_2);

        youTubePlayerView = findViewById(R.id.youtube_player_view);
        getLifecycle().addObserver(youTubePlayerView);
        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer2) {
                youTubePlayer = youTubePlayer2;
                youTubePlayer.cueVideo(videoId, 0);
            }
        });

        naverBtn = (Button) findViewById(R.id.btn_naver_map);
        kakaoBtn = (Button) findViewById(R.id.btn_kakao_map);
        tBtn = (Button) findViewById(R.id.btn_t_map);
        listBtn = (Button) findViewById(R.id.btn_list);
        btn_address_copy = (ImageButton) findViewById(R.id.btn_address_copy);
        //YouTubePlayerTracker tracker = new YouTubePlayerTracker();

        _slide_layout.addPanelSlideListener(new SlidingUpPanelLayout.PanelSlideListener() {
            @Override
            public void onPanelSlide(View panel, float slideOffset) {

            }

            @Override
            public void onPanelStateChanged(View panel, SlidingUpPanelLayout.PanelState previousState, SlidingUpPanelLayout.PanelState newState) {
                if (newState.name().equalsIgnoreCase("Collapsed")) {
                    // 닫혔을때 처리하는 부분
                    youTubePlayer.pause();
                } else if (newState.name().equalsIgnoreCase("Expanded")) {
                    // 열렸을때 처리하는 부분
                }
            }
        });
        listBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                startActivity(intent);
            }
        });

        Button setting_button = (Button) findViewById(R.id.btn_setting);
        setting_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SettingActivity.class);
                startActivity(intent);
            }
        });

        backAPI.ChannelList("").enqueue(new Callback<List<ChannelData>>() {
            @Override
            public void onResponse(@NonNull Call<List<ChannelData>> call, @NonNull Response<List<ChannelData>> response) {
                if (response.isSuccessful()) {
                    ChannelList = response.body();
                }
            }

            @Override
            public void onFailure(Call<List<ChannelData>> call, Throwable t) {
                t.printStackTrace();
            }
        });

        backAPI.RestaurantList("").enqueue(new Callback<List<RestaurantData>>() {
            @Override
            public void onResponse(@NonNull Call<List<RestaurantData>> call, @NonNull Response<List<RestaurantData>> response) {
                if (response.isSuccessful()) {
                    RestaurantList = response.body();
                }
            }

            @Override
            public void onFailure(Call<List<RestaurantData>> call, Throwable t) {
                t.printStackTrace();
            }
        });

        backAPI.UserSettingChannelList(IntroActivity.USER_ID).enqueue(new Callback<List<ChannelData>>() {
            @Override
            public void onResponse(Call<List<ChannelData>> call, Response<List<ChannelData>> response) {
                if (response.isSuccessful()) {
                    UserSettingChannelList = response.body();
                }
            }

            @Override
            public void onFailure(Call<List<ChannelData>> call, Throwable t) {
                t.printStackTrace();
            }
        });

        FragmentManager fragmentManager = getSupportFragmentManager();
        MapFragment mapFragment = (MapFragment) fragmentManager.findFragmentById(R.id.map);
        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance();
            fragmentManager.beginTransaction().add(R.id.map, mapFragment).commit();
        }

        //getMapAsync 호출해 비동기로 onMapReady 콜백 메서드 호출
        //onMapReady에서 NaverMap 객체를 받음.
        mapFragment.getMapAsync(this);

        //위치를 반환하는 구현체인 FusedLocationSource 생성
        locationSource = new FusedLocationSource(this, LOCATION_PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onStart() {
        super.onStart();
        LinearLayout _LinearLayout = findViewById(R.id.linear_layout);
        _LinearLayout.removeAllViews();

        backAPI.ChannelList("").enqueue(new Callback<List<ChannelData>>() {
            @Override
            public void onResponse(@NonNull Call<List<ChannelData>> call, @NonNull Response<List<ChannelData>> response) {
                if (response.isSuccessful()) {
                    ChannelList = response.body();
                }
            }

            @Override
            public void onFailure(Call<List<ChannelData>> call, Throwable t) {
                t.printStackTrace();
            }
        });

        backAPI.RestaurantList("").enqueue(new Callback<List<RestaurantData>>() {
            @Override
            public void onResponse(@NonNull Call<List<RestaurantData>> call, @NonNull Response<List<RestaurantData>> response) {
                if (response.isSuccessful()) {
                    RestaurantList = response.body();
                }
            }

            @Override
            public void onFailure(Call<List<RestaurantData>> call, Throwable t) {
                t.printStackTrace();
            }
        });

        backAPI.UserChannelList(IntroActivity.USER_ID).enqueue(new Callback<List<UserChannelData>>() {
            @Override
            public void onResponse(@NonNull Call<List<UserChannelData>> call, @NonNull Response<List<UserChannelData>> response) {
                if (response.isSuccessful()) {
                    UserChannelList = response.body();
                    for (int x = 0; x < UserChannelList.size(); x++) {
                        channelBtn = new Button(getApplicationContext());
                        channelBtn.setId((x + 1));
                        channelBtn.setText(UserChannelList.get(x).getUserChannelName());
                        channelBtn.setTag(UserChannelList.get(x).getUserChannelId());
                        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.WRAP_CONTENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT);
                        if (x == 0) {
                            param.leftMargin = 20;
                        }
                        param.rightMargin = 15;
                        channelBtn.setLayoutParams(param);
                        channelBtn.setHeight(40);
                        channelBtn.setPadding(20, 0, 20, 0);
                        channelBtn.setOnClickListener(channelBtnListener);
                        channelBtn.setTextColor(Color.WHITE);
                        channelBtn.setSelected(true);
                        BtnBackgroundColor(channelBtn, UserChannelList.get(x).getUserChannelColor());
                        _LinearLayout.addView(channelBtn);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<UserChannelData>> call, Throwable t) {
                t.printStackTrace();
            }

            private void BtnBackgroundColor(Button channelBtn, String Color) {
                if (Color.equals("FFFFFF")) {
                    channelBtn.setBackground(getResources().getDrawable(R.drawable.btn_ffffff));
                } else if (Color.equals("000000")) {
                    channelBtn.setBackground(getResources().getDrawable(R.drawable.btn_000000));
                } else if (Color.equals("DC143C")) {
                    channelBtn.setBackground(getResources().getDrawable(R.drawable.btn_dc143c));
                } else if (Color.equals("FF3333")) {
                    channelBtn.setBackground(getResources().getDrawable(R.drawable.btn_ff3333));
                } else if (Color.equals("E8472E")) {
                    channelBtn.setBackground(getResources().getDrawable(R.drawable.btn_e8472e));
                } else if (Color.equals("FF8C8C")) {
                    channelBtn.setBackground(getResources().getDrawable(R.drawable.btn_ff8c8c));
                } else if (Color.equals("F77F23")) {
                    channelBtn.setBackground(getResources().getDrawable(R.drawable.btn_f77f23));
                } else if (Color.equals("E38436")) {
                    channelBtn.setBackground(getResources().getDrawable(R.drawable.btn_e38436));
                } else if (Color.equals("F0E090")) {
                    channelBtn.setBackground(getResources().getDrawable(R.drawable.btn_f0e090));
                } else if (Color.equals("98B84D")) {
                    channelBtn.setBackground(getResources().getDrawable(R.drawable.btn_98b84d));
                } else if (Color.equals("A3D17B")) {
                    channelBtn.setBackground(getResources().getDrawable(R.drawable.btn_a3d17b));
                } else if (Color.equals("6ECF69")) {
                    channelBtn.setBackground(getResources().getDrawable(R.drawable.btn_6ecf69));
                } else if (Color.equals("6EC48C")) {
                    channelBtn.setBackground(getResources().getDrawable(R.drawable.btn_6ec48c));
                } else if (Color.equals("5AA392")) {
                    channelBtn.setBackground(getResources().getDrawable(R.drawable.btn_5aa392));
                } else if (Color.equals("4B8BBF")) {
                    channelBtn.setBackground(getResources().getDrawable(R.drawable.btn_4b8bbf));
                } else if (Color.equals("6A88D4")) {
                    channelBtn.setBackground(getResources().getDrawable(R.drawable.btn_6a88d4));
                } else if (Color.equals("736DC9")) {
                    channelBtn.setBackground(getResources().getDrawable(R.drawable.btn_736dc9));
                } else if (Color.equals("864FBD")) {
                    channelBtn.setBackground(getResources().getDrawable(R.drawable.btn_864fbd));
                } else if (Color.equals("D169CA")) {
                    channelBtn.setBackground(getResources().getDrawable(R.drawable.btn_d169ca));
                } else if (Color.equals("CC99FF")) {
                    channelBtn.setBackground(getResources().getDrawable(R.drawable.btn_cc99ff));
                } else {
                    channelBtn.setBackground(getResources().getDrawable(R.drawable.btn_style));
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        backAPI.UserChannelList(IntroActivity.USER_ID).enqueue(new Callback<List<UserChannelData>>() {
            @Override
            public void onResponse(@NonNull Call<List<UserChannelData>> call, @NonNull Response<List<UserChannelData>> response) {
                if (response.isSuccessful()) {
                    UserChannelList = response.body();
                    getMarker(mNaverMap, "", "Y");
                }
            }

            @Override
            public void onFailure(Call<List<UserChannelData>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (_slide_layout.getPanelState() == SlidingUpPanelLayout.PanelState.EXPANDED || _slide_layout.getPanelState() == SlidingUpPanelLayout.PanelState.ANCHORED) {
            _slide_layout.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);
            return;
        }

        super.onBackPressed();
    }

    View.OnClickListener channelBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String channelId = v.getTag().toString();
            v.setSelected(!v.isSelected());
            Button tmpBtn = findViewById(v.getId());
            if (v.isSelected()) {
                tmpBtn.setTextColor(Color.WHITE);
            } else {
                tmpBtn.setTextColor(Color.BLACK);
            }
            getMarker(mNaverMap, Integer.toString(v.getId()), "N");
        }
    };

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        // NaverMap 객체 받아서 NaverMap 객체에 위치 소스 지정
        mNaverMap = naverMap;
        mNaverMap.setLocationSource(locationSource);

        mNaverMap.addOnLocationChangeListener(new NaverMap.OnLocationChangeListener() {
            @Override
            public void onLocationChange(@NonNull Location location) {
                myLatitude = location.getLatitude();
                myLongitude = location.getLongitude();

                if(myLatitude > 0 && myLongitude > 0) {
                    listBtn.setVisibility(View.VISIBLE);
                }
                //System.out.println("경도 : " + Double.toString(myLatitude));
                //System.out.println("위도 : " + Double.toString(myLongitude));
            }
        });

        UiSettings uiSettings = naverMap.getUiSettings();
        uiSettings.setCompassEnabled(false);
        uiSettings.setScaleBarEnabled(false);
        uiSettings.setZoomControlEnabled(false);
        uiSettings.setLocationButtonEnabled(true); // 현재위치

        CameraPosition cameraPosition = new CameraPosition(new LatLng(37.5666102, 126.9783881), 10);
        naverMap.setCameraPosition(cameraPosition);

        //Toast.makeText(getApplicationContext(), "1", Toast.LENGTH_SHORT).show();
        // 권한 확인, 결과는 onRequestPermissionResult 콜백 메서드 호출
        ActivityCompat.requestPermissions(this, PERMISSIONS, LOCATION_PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // request code와 권한 획득 여부 확인
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mNaverMap.setLocationTrackingMode(LocationTrackingMode.Follow);
            }else{
                Toast.makeText(getApplicationContext(), "위치 권한이 필요합니다.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void getMarker(@NonNull NaverMap naverMap, String btnId, String init_YN) {
        String channel_id = "";

        if(init_YN.equals("Y")) {
            if(Marker_Count > 0) {
                for (int x = 0; x < Marker_Count; x++) {
                    markers[x].setMap(null);
                }
            }
            markers = null;
            Marker_Count = 0;
        }

        if(init_YN.equals("N")) {
            for(int x=0; x<UserChannelList.size(); x++) {
                Button tmpBtn =  findViewById((x+1));
                if(tmpBtn.isSelected()){
                    if(channel_id.equals("")){
                        channel_id = UserChannelList.get(x).getUserChannelId();
                    }else{
                        channel_id = channel_id + "," + UserChannelList.get(x).getUserChannelId();
                    }
                }
            }
            if(channel_id.equals("")){
                for(int x=0; x<Marker_Count; x++){
                    markers[x].setMap(null);
                }
            }
        }else if(init_YN.equals("Y")){
            channel_id = "";
            for(int x=0; x<UserChannelList.size(); x++) {
                if(channel_id.equals("")){
                    channel_id = UserChannelList.get(x).getUserChannelId();
                }else{
                    channel_id = channel_id + "," + UserChannelList.get(x).getUserChannelId();
                }
            }
        }

        backAPI.UserRestaurantList(IntroActivity.USER_ID, channel_id).enqueue(new Callback<List<UserRestaurantData>>() {
            @Override
            public void onResponse(@NonNull Call<List<UserRestaurantData>> call, @NonNull Response<List<UserRestaurantData>> response) {
                UserRestaurantList = response.body();
                if (response.isSuccessful()) {
                    List<UserRestaurantData> list = response.body();
                    if(init_YN.equals("Y")){
                        Marker_Count = list.size();
                    }

                    if(markers == null) {
                        markers = new Marker[list.size()];
                    }else{
                        for(int x=0; x<Marker_Count; x++){
                            markers[x].setMap(null);
                        }
                    }

                    int marker_cnt = 0;
                    for (UserRestaurantData userRestaurantData : list) {
                        markers[marker_cnt] = new Marker();
                        markers[marker_cnt].setCaptionText(userRestaurantData.getUserRestaurantName());
                        markers[marker_cnt].setIcon(MarkerIcons.BLACK);
                        markers[marker_cnt].setWidth(35);
                        markers[marker_cnt].setHeight(55);
                        markers[marker_cnt].setIconTintColor(Color.parseColor("#" + userRestaurantData.getUserChannelColor()));
                        markers[marker_cnt].setPosition(new LatLng(Double.parseDouble(userRestaurantData.getUserRestaurantLatitude().toString()), Double.parseDouble(userRestaurantData.getUserRestaurantLongitude().toString())));
                        markers[marker_cnt].setMap(naverMap);
                        markers[marker_cnt].setOnClickListener(new Overlay.OnClickListener() {
                            @Override
                            public boolean onClick(@NonNull Overlay overlay) {
                                // Marker 클릭(권세현)
                                btn_address_copy.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                                        ClipData clipData = ClipData.newPlainText("주소", userRestaurantData.getUserRestaurantAddress());
                                        clipboardManager.setPrimaryClip(clipData);
                                        Toast.makeText(getApplicationContext(), "주소가 복사 되었습니다.", Toast.LENGTH_SHORT).show();
                                    }
                                });

                                naverBtn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        System.out.println("=========["+userRestaurantData.getUserRestaurantName()+"] NaverMapID : " + userRestaurantData.getUserRestaurantNavermapId());
                                        String url = "nmap://place?id="+userRestaurantData.getUserRestaurantNavermapId();
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

                                kakaoBtn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        System.out.println("=========["+userRestaurantData.getUserRestaurantName()+"] KAKAOMapID : " + userRestaurantData.getUserRestaurantKakaomapId());
                                        String url = "kakaomap://place?id="+userRestaurantData.getUserRestaurantKakaomapId();
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

                                tBtn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        System.out.println("=========["+userRestaurantData.getUserRestaurantName()+"] TMapID : " + userRestaurantData.getUserRestaurantTmapId());
                                        String url = "tmap://route?goalx="+userRestaurantData.getUserRestaurantLongitude()+"&goaly="+userRestaurantData.getUserRestaurantLatitude()+"&goalname="+userRestaurantData.getUserRestaurantName();
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

                                videoId = userRestaurantData.getUserRestaurantYoutubeId();
                                youTubePlayer.cueVideo(videoId, 0);

                                _slide_layout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
                                _slide_title_name.setText(userRestaurantData.getUserRestaurantName());
                                _slide_address_name.setText(userRestaurantData.getUserRestaurantAddress());

                                if(!(userRestaurantData.getUserRestaurantReviewStarPoint()).equals("") || !(userRestaurantData.getUserRestaurantReviewVisitorCount()).equals("") || !(userRestaurantData.getUserRestaurantReviewBlogCount()).equals("")) {
                                    if ((userRestaurantData.getUserRestaurantReviewStarPoint()).equals("")) {
                                        _review_star.setVisibility(View.GONE);
                                        _review_star_point.setVisibility(View.GONE);
                                        _review_star_full_point.setVisibility(View.GONE);
                                    } else {
                                        _review_star.setVisibility(View.VISIBLE);
                                        _review_star_point.setVisibility(View.VISIBLE);
                                        _review_star_full_point.setVisibility(View.VISIBLE);

                                        _review_star_point.setText(userRestaurantData.getUserRestaurantReviewStarPoint());
                                    }

                                    if ((userRestaurantData.getUserRestaurantReviewVisitorCount()).equals("")) {
                                        _review_visitor.setVisibility(View.GONE);
                                    } else {
                                        if (!(userRestaurantData.getUserRestaurantReviewStarPoint()).equals("")) {
                                            _review_gubun_1.setVisibility(View.VISIBLE);
                                        } else {
                                            _review_gubun_1.setVisibility(View.GONE);
                                        }
                                        _review_visitor.setVisibility(View.VISIBLE);
                                        _review_visitor.setText("방문자리뷰 " + userRestaurantData.getUserRestaurantReviewVisitorCount());
                                        _review_visitor.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                WEBVIEW_URL = "https://m.place.naver.com/restaurant/" + userRestaurantData.getUserRestaurantNavermapId() + "/review/visitor?type=photoView";
                                                //Intent webViewIntent = new Intent(getApplicationContext(), webViewActivity.class);
                                                //startActivity(webViewIntent);
                                                Intent intentUrl = new Intent(Intent.ACTION_VIEW, Uri.parse(WEBVIEW_URL));
                                                startActivity(intentUrl);
                                            }
                                        });
                                    }

                                    if ((userRestaurantData.getUserRestaurantReviewBlogCount()).equals("")) {
                                        _review_blog.setVisibility(View.GONE);
                                    } else {
                                        if (!(userRestaurantData.getUserRestaurantReviewBlogCount()).equals("")) {
                                            _review_gubun_2.setVisibility(View.VISIBLE);
                                        } else {
                                            _review_gubun_2.setVisibility(View.GONE);
                                        }
                                        _review_blog.setVisibility(View.VISIBLE);
                                        _review_blog.setText("블로그리뷰 " + userRestaurantData.getUserRestaurantReviewBlogCount());
                                        _review_blog.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                WEBVIEW_URL = "https://m.place.naver.com/restaurant/" + userRestaurantData.getUserRestaurantNavermapId() + "/review/ugc?type=photoView";
                                                //Intent webViewIntent = new Intent(getApplicationContext(), webViewActivity.class);
                                                //startActivity(webViewIntent);
                                                Intent intentUrl = new Intent(Intent.ACTION_VIEW, Uri.parse(WEBVIEW_URL));
                                                startActivity(intentUrl);
                                            }
                                        });
                                    }
                                }else{
                                    _review_star.setVisibility(View.GONE);
                                    _review_star_point.setVisibility(View.GONE);
                                    _review_star_full_point.setVisibility(View.GONE);
                                    _review_visitor.setVisibility(View.GONE);
                                    _review_gubun_1.setVisibility(View.GONE);
                                    _review_blog.setVisibility(View.GONE);
                                    _review_gubun_2.setVisibility(View.GONE);
                                }

                                return false;
                            }
                        });
                        marker_cnt++;
                    }
                }
            }

            @Override
            public void onFailure(Call<List<UserRestaurantData>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}