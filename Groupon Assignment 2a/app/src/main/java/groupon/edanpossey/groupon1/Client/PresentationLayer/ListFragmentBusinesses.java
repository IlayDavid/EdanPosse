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

import groupon.edanpossey.groupon1.Entities.Business;
import groupon.edanpossey.groupon1.Entities.CatalogItem;
import groupon.edanpossey.groupon1.R;

/**
 * Created by IlayDavid on 17/05/2015.
 */
public class ListFragmentBusinesses extends Fragment {

    private ArrayList<String> nameList;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_businesses_fragment, container, false);

        ArrayList<Business> businessesList = (ArrayList<Business>) ObjectsHolder.getCurrentBusinessesList();
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

        final ListAdapter businessesAdapter = new CustomAdapterBusinesses(view.getContext(), businessesList);
        ListView businessesListView = (ListView) view.findViewById(R.id.businessesListView);
        businessesListView.setAdapter(businessesAdapter);
        businessesListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        /*Log.i("Harta", "helo");
                        System.out.println("hohohoho");
                        Logger l = Logger.getLogger("harta");
                        l.info("hello");
                        Toast.makeText(getActivity().getApplicationContext(), "Ori is meffagger. " + position, Toast.LENGTH_LONG).show();*/
                        ObjectsHolder.setCurrentBusiness((Business) parent.getItemAtPosition(position));
                        Intent i = new Intent(view.getContext(), ViewBusiness.class);
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
