package groupon.edanpossey.groupon1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class User_activity extends ActionBarActivity {


    Button viewCoupon;
    Button viewOrders;
    Button searchCoupon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_activity);
        viewCoupon = (Button) findViewById(R.id.viewCoupon_bt);
        viewOrders = (Button) findViewById(R.id.viewOrders_bt);
        searchCoupon = (Button) findViewById(R.id.searchCoupon_bt);
    }


    public void viewCoupon(View view) {


        Intent i = new Intent(this,ViewOrders_activity.class);
        startActivity(i);

    }


    public void searchCoupon(View view) {

        // do whatever

    }


    public void viewOrders(View view) {

        //do whatever

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user_activity, menu);
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
