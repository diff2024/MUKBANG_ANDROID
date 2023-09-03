package com.mukbang.map.ListView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.mukbang.map.R;

public class ListListItemView extends LinearLayout {
    TextView textView_channelId, textView_channelName, textView_restaurantId, textView_restaurantName, textView_restaurantAddress;

    public ListListItemView(Context context){
        super(context);
        init(context);
    }

    public ListListItemView(Context context, @Nullable AttributeSet attrs){
        super(context, attrs);
    }

    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.activity_list_listview, this, true);

        textView_channelId = findViewById(R.id.textView_channelId);
        textView_channelName = findViewById(R.id.textView_channelName);
        textView_restaurantId = findViewById(R.id.textView_restaurantId);
        textView_restaurantName = findViewById(R.id.textView_restaurantName);
        textView_restaurantAddress = findViewById(R.id.textView_restaurantAddress);
    }

    public void setChannelId(String channelId){
        textView_channelId.setText(channelId);
    }
    public void setChannelName(String channelName){
        textView_channelName.setText(channelName);
    }
    public void setRestaurantId(String restaurantId){ textView_restaurantId.setText(restaurantId ); }
    public void setRestaurantName(String restaurantName){ textView_restaurantName.setText(restaurantName ); }
    public void setRestaurantAddress(String restaurantAddress){ textView_restaurantAddress.setText(restaurantAddress ); }
}
