package groupon.edanpossey.groupon1.Client.PresentationLayer;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import groupon.edanpossey.groupon1.Entities.User;
import groupon.edanpossey.groupon1.Entities.AccessLevel;
import groupon.edanpossey.groupon1.R;


public class LogIn_Activity extends ActionBarActivity {

    EditText userText;
    EditText passText;
    Button loginBtn;
    Button signupBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_);
        userText = (EditText) findViewById(R.id.NameText);
        passText = (EditText) findViewById(R.id.passText);
        loginBtn = (Button) findViewById(R.id.LogIn_bt);
        signupBtn = (Button) findViewById(R.id.SignUp_bt);
        this.getApplication().getApplicationContext();
        ObjectsHolder.initialize(this.getApplication().getApplicationContext());
    }


    public void Login(View view) {
        String username = userText.getText().toString();
        String password = passText.getText().toString();

        User currentUser = ObjectsHolder.getBl().login(username, password);
        if(currentUser != null){
            ObjectsHolder.setCurrentUser(currentUser);
            Intent i;
            if(ObjectsHolder.getCurrentUser().getAccessLevel() == AccessLevel.Administrator)
                i = new Intent(this, Admin_activity.class);
            else if(ObjectsHolder.getCurrentUser().getAccessLevel() == AccessLevel.Business)
                i = new Intent(this, Business_activity.class);
            else
                i = new Intent(this,User_activity.class);
            startActivity(i);
        }
        else{
            Toast.makeText(getApplicationContext(), "Wrong username or password.", Toast.LENGTH_LONG).show();
        }

    }


    public void Signup(View view) {
        Intent i = new Intent(this,Signup_activity.class);
        startActivity(i);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_log_in_, menu);
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
