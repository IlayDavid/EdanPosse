package groupon.edanpossey.groupon1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import groupon.edanpossey.groupon1.Entities.AccessLevel;
import groupon.edanpossey.groupon1.Entities.User;


public class Signup_activity extends ActionBarActivity {


    EditText userText;
    EditText passText;
    EditText phoneText;
    EditText mailText;
    Button signupBtn;
    ObjectsHolder objectsHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_activity);

        userText = (EditText) findViewById(R.id.nameText);
        passText = (EditText) findViewById(R.id.passText);
        phoneText = (EditText) findViewById(R.id.phoneText);
        mailText = (EditText) findViewById(R.id.mailText);
        signupBtn = (Button) findViewById(R.id.Send_bt);

        objectsHolder = ObjectsHolder.getInstance(getApplicationContext());

    }


    public void Signup(View view) {
        String username = userText.getText().toString();
        String password = passText.getText().toString();
        String email = mailText.getText().toString();
        String phone = phoneText.getText().toString();
        AccessLevel accessLevel = AccessLevel.User;

        boolean missingParameters = (username.equals("") || username == null);
        missingParameters = missingParameters || (password.equals("") || password == null);
        missingParameters = missingParameters || (email.equals("") || email == null);
        missingParameters = missingParameters || (phone.equals("") || phone == null);

        if(missingParameters){
            Toast.makeText(getApplicationContext(), "Missing parameters.", Toast.LENGTH_LONG).show();
        }
        else{
            User user = new User(username, password, email, phone, accessLevel, null);
            if(objectsHolder.getBl().newUser(user)){
                objectsHolder.setCurrentUser(user);
                Intent i = new Intent(this,User_activity.class);
                startActivity(i);
                finish();
            }
            else{
                Toast.makeText(getApplicationContext(), "Unavailable username", Toast.LENGTH_LONG).show();
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_signup_activity, menu);
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
