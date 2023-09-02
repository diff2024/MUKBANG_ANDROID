package com.mukbang.map.ListView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;

import com.mukbang.map.R;

public class SettingListItemView extends LinearLayout {
    TextView textView_channelId, textView_channelName, textView_channelMarkerColor;

    public SettingListItemView(Context context){
        super(context);
        init(context);
    }

    public SettingListItemView(Context context, @Nullable AttributeSet attrs){
        super(context, attrs);
    }

    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.activity_setting_listview, this, true);

        textView_channelId = findViewById(R.id.textView_channelId);
        textView_channelName = findViewById(R.id.textView_channelName);
        textView_channelMarkerColor = findViewById(R.id.textView_channelMarkerColor);
    }

    public void setChannelId(String channelId){
        textView_channelId.setText(channelId);
    }
    public void setChannelName(String channelName){
        textView_channelName.setText(channelName);
    }
    public void setChannelMarkerColor(String channelMarkerColor){
        textView_channelMarkerColor.setText(channelMarkerColor);
    }
}
