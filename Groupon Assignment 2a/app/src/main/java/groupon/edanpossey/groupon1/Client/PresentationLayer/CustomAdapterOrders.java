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

import groupon.edanpossey.groupon1.Entities.Coupon;
import groupon.edanpossey.groupon1.Entities.Order;
import groupon.edanpossey.groupon1.R;

class CustomAdapterOrders extends ArrayAdapter<Order> {

    CustomAdapterOrders(Context context, ArrayList<Order> orderCodes) {
        super(context, R.layout.custom_row_view_order, orderCodes);
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater costumeInflater = LayoutInflater.from(getContext());
        View customView = costumeInflater.inflate(R.layout.custom_row_view_order, parent, false);

        Order order = getItem(position);
        TextView orderText = (TextView) customView.findViewById(R.id.orderNumberTextView);
        TextView statusText = (TextView) customView.findViewById(R.id.orderStatusTextView);
        ImageView statusImage = (ImageView) customView.findViewById(R.id.statusImage);

        orderText.setText(order.getOrderCode() + "");
        statusText.setText(order.getOrderStatus().toString());

        int color = Color.BLACK;
        if(order.getOrderStatus() == Order.OrderStatus.Pending)
            color = Color.GRAY;
        else if(order.getOrderStatus() == Order.OrderStatus.Rejected)
            color = Color.RED;
        else{
            statusText.setText(order.getCoupon().getStatus().toString());
            if(order.getCoupon().getStatus() == Coupon.CouponStatus.Expired)
                color = Color.RED;
            else if(order.getCoupon().getStatus() == Coupon.CouponStatus.NotUsed)
                color = Color.GREEN;
            else if(order.getCoupon().getStatus() == Coupon.CouponStatus.Used)
                color = Color.CYAN;
        }
        statusImage.setBackgroundColor(color);
        return customView;
    }
}
