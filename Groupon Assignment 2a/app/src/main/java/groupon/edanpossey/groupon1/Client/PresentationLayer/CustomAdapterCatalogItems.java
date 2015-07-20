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

import groupon.edanpossey.groupon1.Entities.CatalogItem;
import groupon.edanpossey.groupon1.Entities.Coupon;
import groupon.edanpossey.groupon1.Entities.Order;
import groupon.edanpossey.groupon1.R;

class CustomAdapterCatalogItems extends ArrayAdapter<CatalogItem> {

    CustomAdapterCatalogItems(Context context, ArrayList<CatalogItem> catalogItems) {
        super(context, R.layout.custom_row_view_catalog_item, catalogItems);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater costumeInflater = LayoutInflater.from(getContext());
        View customView = costumeInflater.inflate(R.layout.custom_row_view_catalog_item, parent, false);

        CatalogItem catalogItem = getItem(position);
        TextView catalogItemText = (TextView) customView.findViewById(R.id.catalogItemNameTextView);
        TextView statusText = (TextView) customView.findViewById(R.id.orderStatusTextView);
        ImageView statusImage = (ImageView) customView.findViewById(R.id.statusImage);

        catalogItemText.setText(catalogItem.getName() + "");
        statusText.setText(catalogItem.getStatus().toString());

        int color;
        if(catalogItem.getStatus() == CatalogItem.CatalogItemStatus.PendingApproval)
            color = Color.GRAY;
        else if(catalogItem.getStatus() == CatalogItem.CatalogItemStatus.Approved)
            color = Color.GREEN;
        else
            color = Color.BLACK;
        statusImage.setBackgroundColor(color);
        return customView;
    }
}
