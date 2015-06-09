package groupon.edanpossey.groupon1;

/**
 * Created by IlayDavid on 17/05/2015.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

class CustomAdapter extends ArrayAdapter<String> {

    CustomAdapter(Context context, ArrayList<String> names) {
        super(context, R.layout.custom_row ,names);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater costumeInflater = LayoutInflater.from(getContext());
        View customView = costumeInflater.inflate(R.layout.custom_row, parent, false);

        String singleNameItem = getItem(position);
        TextView couponText = (TextView) customView.findViewById(R.id.couponText);
        ImageView couponImage = (ImageView) customView.findViewById(R.id.couponImage);

        couponText.setText(singleNameItem);
        //couponImage.setImageResource(R.drawable.dsa);
        return customView;
    }
}
