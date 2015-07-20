package groupon.edanpossey.groupon1.Client.PresentationLayer;

import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import groupon.edanpossey.groupon1.R;

public class Business_activity extends ActionBarActivity {

    EditText categoriesText, radiusText, cityText;
    Button viewOrders, searchCatalog, searchBusinesses, addCatalogItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_activity);
        viewOrders = (Button) findViewById(R.id.viewOrders_bt);
        searchCatalog = (Button) findViewById(R.id.searchCatalogButton);
        searchBusinesses = (Button) findViewById(R.id.searchBusinessesButton);
        categoriesText = (EditText) findViewById(R.id.categoriesTextView);
        radiusText = (EditText) findViewById(R.id.radiusTextView);
        cityText = (EditText) findViewById(R.id.cityTextView);
        addCatalogItem = (Button) findViewById(R.id.addCatalogItemButton);
    }



    public void searchCatalog_ButtonClick(View view) {
        String categories = categoriesText.getText().toString();
        String city = cityText.getText().toString();
        int radius;
        boolean everythingOk = true;
        if(TextUtils.isEmpty(radiusText.getText()))
            radius = -1;
        else{
            try{
                radius = Integer.parseInt(radiusText.getText().toString());
                if(radius < 0)
                    radiusText.setError("Positive numbers only");
            }catch (Exception e){
                radiusText.setError("Positive numbers only");
                everythingOk = false;
                radius = 0;
            }
        }
        if(everythingOk){
            Location location = ObjectsHolder.getLocationManager().getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

            ObjectsHolder.getBl().getCatalogItemsByPreferences(categories, city, radius, location.getLongitude(), location.getLatitude());
            Intent i = new Intent(this,ViewCatalogItems_activity.class);
            startActivity(i);
        }
    }
    public void searchBusinesses_ButtonClick(View view){
        String categories = categoriesText.getText().toString();
        String city = cityText.getText().toString();
        int radius;
        boolean everythingOk = true;
        if(TextUtils.isEmpty(radiusText.getText()))
            radius = -1;
        else{
            try{
                radius = Integer.parseInt(radiusText.getText().toString());
                if(radius < 0)
                    radiusText.setError("Positive numbers only");
            }catch (Exception e){
                radiusText.setError("Positive numbers only");
                everythingOk = false;
                radius = 0;
            }
        }
        if(everythingOk){
            Location location = ObjectsHolder.getLocationManager().getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            Toast.makeText(getApplicationContext(), location.getLongitude() + ", " + location.getLatitude(), Toast.LENGTH_LONG).show();
            ObjectsHolder.getBl().getBusinessesByPreference(categories, city, radius, location.getLongitude(), location.getLatitude());
            Intent i = new Intent(this,ViewBusinesses_activity.class);
            startActivity(i);
        }

    }
    public void addCatalogItem_ButtonClick(View view){
        Intent i = new Intent(this, AddCatalogItem.class);
        startActivity(i);
    }
    public void viewOrders(View view) {
        ObjectsHolder.setCurrentOrdersList(ObjectsHolder.getCurrentUser().getBusiness().getOrders());

        Intent i = new Intent(this,ViewOrders_activity.class);
        startActivity(i);
    }
    public void viewOwnCatalogItems_ButtonClick(View view){
        ObjectsHolder.setCurrentCatalogItemsList(ObjectsHolder.getCurrentUser().getBusiness().getCatalogItems());

        Intent i = new Intent(this, ViewCatalogItems_activity.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_business_, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
