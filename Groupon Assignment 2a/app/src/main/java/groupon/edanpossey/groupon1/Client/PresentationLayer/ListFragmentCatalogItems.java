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
public class ListFragmentCatalogItems extends Fragment {

    private ArrayList<String> nameList;


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_catalog_items_fragment, container, false);

        ArrayList<CatalogItem> catalogItemsList = (ArrayList<CatalogItem>) ObjectsHolder.getCurrentCatalogItemsList();
        String[] names = {};
        nameList = new ArrayList<String>(Arrays.asList(names));

        nameList.add("Ori Jacobovitz");
        nameList.add("coupon1");
        nameList.add("harta");
        nameList.add("couponzibbi");
        nameList.add("more harta");
        nameList.add("coupon3");
        nameList.add("hartasgd");
        nameList.add("couponzfsfsaibbi");
        nameList.add("Ori Jacobovfaasfasitz");
        nameList.add("coupofafaan1");
        nameList.add("harffeaffsta");
        nameList.add("couponzfasffasibbi");
        nameList.add("Ori Jacobovfsagaegeitz");
        nameList.add("coupofsasasn1");
        nameList.add("hartgagawefgsa");
        nameList.add("couponzibbaafasdfasi");


        /*
        //Collection<String> memoryNames = (Collection<String>) sharedPref.getAll().values();
        while (!memoryNames.isEmpty()) {

            try {
                String s = (String) (memoryNames.iterator().next());
                memoryNames.remove(s);
                nameList.add(s);
                Log.i(TAG, s + " achived");
            } catch (ClassCastException e) {
                Log.i(TAG, "Cast Exception Error: " + e.getMessage());
            }
        }


        Collections.sort(nameList);

         */

        final ListAdapter catalogItemsAdapter = new CustomAdapterCatalogItems(view.getContext(), catalogItemsList);
        ListView catalogItemsListView = (ListView) view.findViewById(R.id.catalogItemsListView);
        catalogItemsListView.setAdapter(catalogItemsAdapter);
        catalogItemsListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ObjectsHolder.setCurrentCatalogItem((CatalogItem) parent.getItemAtPosition(position));
                        Intent i = new Intent(view.getContext(), ViewCatalogItem.class);
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
