package groupon.edanpossey.groupon1.Client.PresentationLayer;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
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

public class EditBusiness extends ActionBarActivity {
    EditText userNameText, passwordText, phoneText, emailText;
    EditText businessNameText, addressText, cityText, descriptionText;
    Button editBusinessButton, deleteBusinessButton;

    Business currentBusiness;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_business);

        this.currentBusiness = ObjectsHolder.getCurrentBusiness();

        this.userNameText = (EditText) findViewById(R.id.userNameTextView);
        this.userNameText.setText(currentBusiness.getOwner().getId());

        this.passwordText = (EditText) findViewById(R.id.passwordTextView);
        this.passwordText.setText(currentBusiness.getOwner().getPassword());

        this.phoneText = (EditText) findViewById(R.id.phoneTextView);
        this.phoneText.setText(currentBusiness.getOwner().getPhoneNumber());

        this.emailText = (EditText) findViewById(R.id.emailTextView);
        this.emailText.setText(currentBusiness.getOwner().getEmail());

        //------------------------

        this.businessNameText = (EditText) findViewById(R.id.businessNameTextView);
        this.businessNameText.setText(currentBusiness.getBusinessName());

        this.addressText = (EditText) findViewById(R.id.addressTextView);
        this.addressText.setText(currentBusiness.getAddress());

        this.cityText = (EditText) findViewById(R.id.cityTextView);
        this.cityText.setText(currentBusiness.getCity());

        this.descriptionText = (EditText) findViewById(R.id.descriptionTextView);
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
        getMenuInflater().inflate(R.menu.menu_edit_business, menu);
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

    public void editBusiness_ButtonClick(View view){
        if(!missingParameters()){
            String username = userNameText.getText().toString();
            String password = passwordText.getText().toString();
            String email = emailText.getText().toString();
            String phone = phoneText.getText().toString();
            AccessLevel accessLevel = AccessLevel.Business;

            String businessName = businessNameText.getText().toString();
            String address = addressText.getText().toString();
            String city = cityText.getText().toString();
            String description = descriptionText.getText().toString();

            User userNew = new User(username, password, email, phone, accessLevel, null);
            User userOld = currentBusiness.getOwner();
            if(ObjectsHolder.getBl().updateUser(userOld, userNew)){
                Business businessNew = new Business(userNew, businessName, address, city, description);
                if(ObjectsHolder.getBl().updateBusiness(currentBusiness, businessNew)){
                    Toast.makeText(getApplicationContext(), "Business edited successfully.", Toast.LENGTH_LONG).show();
                }
                else{
                    ObjectsHolder.getBl().updateUser(userNew, userOld);
                    Toast.makeText(getApplicationContext(), "Couldn't edit business.", Toast.LENGTH_LONG).show();
                }
            }
            else{
                Toast.makeText(getApplicationContext(), "Couldn't edit user.", Toast.LENGTH_LONG).show();
            }

        }
    }



    private boolean missingParameters(){
        boolean missing = false;

        if(TextUtils.isEmpty(userNameText.getText())){
            missing = true;
            userNameText.setError("Username can't be empty.");
        }
        if(TextUtils.isEmpty(passwordText.getText())){
            missing = true;
            passwordText.setError("Password can't be empty.");
        }
        if(TextUtils.isEmpty(emailText.getText())){
            missing = true;
            emailText.setError("E-mail can't be empty.");
        }
        if(TextUtils.isEmpty(phoneText.getText())){
            missing = true;
            phoneText.setError("Phone number can't be empty.");
        }
        //---------------------------------
        if(TextUtils.isEmpty(businessNameText.getText())){
            missing = true;
            businessNameText.setError("Business name can't be empty.");
        }
        if(TextUtils.isEmpty(addressText.getText())){
            missing = true;
            addressText.setError("Address can't be empty.");
        }
        if(TextUtils.isEmpty(cityText.getText())){
            missing = true;
            cityText.setError("City can't be empty.");
        }
        if(TextUtils.isEmpty(descriptionText.getText())){
            missing = true;
            descriptionText.setError("Description can't be empty.");
        }

        return missing;
    }
}
