package com.mukbang.map.ListView;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.mukbang.map.R;

import java.util.List;

public class ListListItemView extends LinearLayout {
    TextView textView_channelId, textView_channelName, textView_restaurantId, textView_restaurantName, textView_restaurantDistance, textView_restaurantAddress;

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
        textView_restaurantDistance = findViewById(R.id.textView_restaurantDistance);
        textView_restaurantAddress = findViewById(R.id.textView_restaurantAddress);
    }

    public void setChannelId(String channelId){
        textView_channelId.setText(channelId);
    }
    public void setChannelName(String channelName){
        textView_channelName.setText(channelName);
    }
    public void setRestaurantId(String restaurantId){ textView_restaurantId.setText(restaurantId); }
    public void setRestaurantName(String restaurantName){ textView_restaurantName.setText(restaurantName); }
    public void setRestaurantDistance(String restaurantDistance){ textView_restaurantDistance.setText(restaurantDistance); }
    public void setRestaurantAddress(String restaurantAddress){ textView_restaurantAddress.setText(restaurantAddress); }
}
