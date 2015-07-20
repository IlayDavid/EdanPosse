package groupon.edanpossey.groupon1.Client.PresentationLayer;

/**
 * Created by IlayDavid on 17/05/2015.
 */

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import groupon.edanpossey.groupon1.Entities.Business;
import groupon.edanpossey.groupon1.Entities.CatalogItem;
import groupon.edanpossey.groupon1.R;

class CustomAdapterBusinesses extends ArrayAdapter<Business> {

    CustomAdapterBusinesses(Context context, ArrayList<Business> businesses) {
        super(context, R.layout.custom_row_view_business, businesses);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater costumeInflater = LayoutInflater.from(getContext());
        View customView = costumeInflater.inflate(R.layout.custom_row_view_business, parent, false);

        Business business = getItem(position);
        TextView businessText = (TextView) customView.findViewById(R.id.businessNameTextView);
        businessText.setText(business.getBusinessName());

        return customView;
    }
}
