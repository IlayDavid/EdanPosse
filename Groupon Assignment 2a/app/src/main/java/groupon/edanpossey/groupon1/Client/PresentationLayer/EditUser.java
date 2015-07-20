package groupon.edanpossey.groupon1.Client.PresentationLayer;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import groupon.edanpossey.groupon1.Entities.User;
import groupon.edanpossey.groupon1.R;

public class EditUser extends ActionBarActivity {

    EditText userText;
    EditText passText;
    EditText phoneText;
    EditText mailText;
    Button editButton, deleteButton;
    User currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_activity);
        currentUser = ObjectsHolder.getCurrentUser();

        userText = (EditText) findViewById(R.id.nameText);
        userText.setText(currentUser.getId());

        passText = (EditText) findViewById(R.id.passText);
        passText.setText(currentUser.getPassword());

        phoneText = (EditText) findViewById(R.id.phoneText);
        phoneText.setText(currentUser.getPhoneNumber());

        mailText = (EditText) findViewById(R.id.mailText);
        mailText.setText(currentUser.getEmail());

        editButton = (Button) findViewById(R.id.editButton);
        deleteButton = (Button) findViewById(R.id.deleteButton);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_user, menu);
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
