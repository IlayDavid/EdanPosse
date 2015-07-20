package groupon.edanpossey.groupon1.Client.PresentationLayer;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

import groupon.edanpossey.groupon1.Entities.CatalogItem;
import groupon.edanpossey.groupon1.Entities.Coupon;
import groupon.edanpossey.groupon1.Entities.Order;
import groupon.edanpossey.groupon1.R;

/**
 * Created by IlayDavid on 17/05/2015.
 */
public class ListFragmentOrders extends Fragment {

    private ArrayList<String> nameList;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_orders_fragment, container, false);

        final ArrayList<Order> ordersList = (ArrayList<Order>) ObjectsHolder.getCurrentOrdersList();
        ArrayList<Order> testList = new ArrayList<Order>();
        Coupon coup = new Coupon();
        coup.setStatus(Coupon.CouponStatus.NotUsed);
        Order order =  new Order();
        order.setOrderCode(1);
        order.setCoupon(coup);
        order.setOrderStatus(Order.OrderStatus.Complete);
        testList.add(order);

        coup = new Coupon();
        coup.setStatus(Coupon.CouponStatus.Used);
        order = new Order();
        order.setOrderCode(2);
        order.setCoupon(coup);
        order.setOrderStatus(Order.OrderStatus.Complete);
        testList.add(order);

        coup = new Coupon();
        coup.setStatus(Coupon.CouponStatus.Expired);
        order = new Order();
        order.setOrderCode(3);
        order.setCoupon(coup);
        order.setOrderStatus(Order.OrderStatus.Complete);
        testList.add(order);

        final ListAdapter ordersAdapter = new CustomAdapterOrders(view.getContext(), ordersList);
        ListView ordersListView = (ListView) view.findViewById(R.id.ordersListView);
        ordersListView.setAdapter(ordersAdapter);
        ordersListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Coupon coupon = ((Order)parent.getItemAtPosition(position)).getCoupon();
                        ObjectsHolder.setCurrentOrder((Order) parent.getItemAtPosition(position));
                        ObjectsHolder.setCurrentCoupon(coupon);
                        Intent i = new Intent(view.getContext(), ViewCoupon.class);
                        startActivity(i);
                    }
                });

        return view;
    }


    @Override
    public void onStop() {
        super.onStop();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}
