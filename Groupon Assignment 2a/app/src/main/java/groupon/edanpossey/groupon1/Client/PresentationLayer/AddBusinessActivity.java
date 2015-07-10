package groupon.edanpossey.groupon1.Client.PresentationLayer;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import groupon.edanpossey.groupon1.Entities.AccessLevel;
import groupon.edanpossey.groupon1.Entities.Business;
import groupon.edanpossey.groupon1.Entities.User;
import groupon.edanpossey.groupon1.R;


public class AddBusinessActivity extends ActionBarActivity {
    EditText userNameText, passwordText, phoneText, emailText;
    EditText businessNameText, addressText, cityText, descriptionText;
    Button addBusinessButton;

    ObjectsHolder objectsHolder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_business);

        this.userNameText = (EditText) findViewById(R.id.userNameTextView);
        this.passwordText = (EditText) findViewById(R.id.passwordTextView);
        this.phoneText = (EditText) findViewById(R.id.phoneTextView);
        this.emailText = (EditText) findViewById(R.id.emailTextView);

        this.businessNameText = (EditText) findViewById(R.id.businessNameTextView);
        this.addressText = (EditText) findViewById(R.id.addressTextView);
        this.cityText = (EditText) findViewById(R.id.cityTextView);
        this.descriptionText = (EditText) findViewById(R.id.descriptionTextView);

        this.addBusinessButton = (Button) findViewById(R.id.addBusinessButton);

        objectsHolder = ObjectsHolder.getInstance(getApplicationContext());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_business, menu);
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

    public void addBusiness_ButtonClick(View view){
        String username = userNameText.getText().toString();
        String password = passwordText.getText().toString();
        String email = emailText.getText().toString();
        String phone = phoneText.getText().toString();
        AccessLevel accessLevel = AccessLevel.Business;

        boolean missingParameters = (username.equals("") || username == null);
        missingParameters = missingParameters || (password.equals("") || password == null);
        missingParameters = missingParameters || (email.equals("") || email == null);
        missingParameters = missingParameters || (phone.equals("") || phone == null);

        if(missingParameters){
            Toast.makeText(getApplicationContext(), "Missing needed parameters to create user.", Toast.LENGTH_LONG).show();
        }
        else{
            String businessName = businessNameText.getText().toString();
            String address = addressText.getText().toString();
            String city = cityText.getText().toString();
            String description = descriptionText.getText().toString();

            missingParameters = (businessName.equals("") || businessName == null);
            missingParameters = missingParameters || (address.equals("") || address == null);
            missingParameters = missingParameters || (city.equals("") || city == null);
            missingParameters = missingParameters || (description.equals("") || description == null);

            if(missingParameters){
                Toast.makeText(getApplicationContext(), "Missing needed parameters to create business.", Toast.LENGTH_LONG).show();
            }
            else{
                User user = new User(username, password, email, phone, accessLevel, null);
                if(this.objectsHolder.getBl().newUser(user)){
                    Business business = new Business(user, businessName, address, city, description);
                    if(this.objectsHolder.getBl().newBusiness(business)){

                    }
                    else{
                        objectsHolder.getBl().deleteUser(user);
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Couldn't create user.", Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}
