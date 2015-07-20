package groupon.edanpossey.groupon1.Client.PresentationLayer;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import groupon.edanpossey.groupon1.Entities.AccessLevel;
import groupon.edanpossey.groupon1.Entities.Business;
import groupon.edanpossey.groupon1.R;

public class ViewBusiness extends ActionBarActivity {
    TextView userNameText, businessNameText, addressText, cityText, descriptionText;
    Button editBusinessButton, deleteBusinessButton;

    Business currentBusiness;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_business);

        this.currentBusiness = ObjectsHolder.getCurrentBusiness();

        this.userNameText = (TextView) findViewById(R.id.userNameTextView);
        this.userNameText.setText(currentBusiness.getOwner().getId());

        this.businessNameText = (TextView) findViewById(R.id.businessNameTextView);
        this.businessNameText.setText(currentBusiness.getBusinessName());

        this.addressText = (TextView) findViewById(R.id.addressTextView);
        this.addressText.setText(currentBusiness.getAddress());

        this.cityText = (TextView) findViewById(R.id.cityTextView);
        this.cityText.setText(currentBusiness.getCity());

        this.descriptionText = (TextView) findViewById(R.id.descriptionTextView);
        this.descriptionText.setText(currentBusiness.getDescription());


        this.editBusinessButton = (Button) findViewById(R.id.editBusinessButton);
        if(!currentBusiness.getOwner().equals(ObjectsHolder.getCurrentUser())){
            this.editBusinessButton.setVisibility(View.GONE);
        }
        this.deleteBusinessButton = (Button) findViewById(R.id.deleteBusinessButton);
        if(!(ObjectsHolder.getCurrentUser().getAccessLevel() == AccessLevel.Administrator)){
            this.deleteBusinessButton.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_business, menu);
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

    public void deleteBusiness_ButtonClick(View view){
        if(ObjectsHolder.getBl().deleteBusiness(currentBusiness)){
            Toast.makeText(getApplicationContext(), "Business deleted successfully.", Toast.LENGTH_LONG).show();
            finish();
        }
        else{
            Toast.makeText(getApplicationContext(), "Business couldn't be deleted successfully.", Toast.LENGTH_LONG).show();
        }
    }

    public void editBusiness_ButtonClick(View view){
        Intent i = new Intent(this, EditBusiness.class);
        startActivity(i);
    }
}
