package groupon.edanpossey.groupon1.Client.PresentationLayer;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Date;

import groupon.edanpossey.groupon1.Entities.Business;
import groupon.edanpossey.groupon1.Entities.CatalogItem;
import groupon.edanpossey.groupon1.R;


public class AddCatalogItem extends ActionBarActivity {
    EditText catalogItemNameText, categoryText, descriptionText, expirationDateText, originalPriceText, priceAfterDiscountText, linkText;
    Button addItemButton;
    ObjectsHolder objectsHolder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_catalog_item);

        catalogItemNameText = (EditText) findViewById(R.id.catalogItemNameTextView);
        categoryText = (EditText) findViewById(R.id.categoryTextView);
        descriptionText = (EditText) findViewById(R.id.descriptionTextView);
        expirationDateText = (EditText) findViewById(R.id.expirationDateTextView);
        originalPriceText = (EditText) findViewById(R.id.originalPriceTextView);
        priceAfterDiscountText = (EditText) findViewById(R.id.priceAfterDiscountTextView);
        linkText = (EditText) findViewById(R.id.linkTextView);
        addItemButton = (Button) findViewById(R.id.addItemButton);

        objectsHolder = ObjectsHolder.getInstance(getApplicationContext());

        if(objectsHolder.getCurrentUser().getBusiness() == null){
            linkText.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_catalog_item, menu);
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

    public void addItem_ButtonClick(View view) {
        boolean missingParameters;

        String expirationDateString = expirationDateText.getText().toString();
        missingParameters = (expirationDateText.equals("") || expirationDateString == null);

        String originalPriceString = originalPriceText.getText().toString();
        missingParameters = missingParameters || (originalPriceString.equals("") || originalPriceString == null);

        String priceAfterDiscountString = priceAfterDiscountText.getText().toString();
        missingParameters = missingParameters || (priceAfterDiscountString.equals("") || priceAfterDiscountString == null);

        String nameString = catalogItemNameText.getText().toString();
        missingParameters = missingParameters || (nameString.equals("") || nameString == null);

        String categoryString = categoryText.getText().toString();
        missingParameters = missingParameters || (categoryString.equals("") || categoryString == null);

        String descriptionString = descriptionText.getText().toString();
        missingParameters = missingParameters || (descriptionString.equals("") || descriptionString == null);

        CatalogItem.CatalogItemType type = CatalogItem.CatalogItemType.NotSocial;
        Business publishedBy = objectsHolder.getCurrentUser().getBusiness();
        if(publishedBy == null){
            type = CatalogItem.CatalogItemType.Social;
            String linkString = linkText.getText().toString();
            missingParameters = missingParameters || (linkString.equals("") || linkString == null);
            if(!missingParameters)
                publishedBy = new Business(null, linkString, null, null, null);
        }

        if(missingParameters) {
            Toast.makeText(getApplicationContext(), "Missing parameters.", Toast.LENGTH_LONG).show();
        }
        else{
            Date expirationDate = Date.valueOf(expirationDateString);

            double originalPrice = Double.parseDouble(originalPriceString);
            double priceAfterDiscount = Double.parseDouble(priceAfterDiscountString);

            String name = nameString;
            String category = categoryString;
            String description = descriptionString;

            CatalogItem catalogItem = new CatalogItem(publishedBy, name, category, description,
                    CatalogItem.CatalogItemStatus.PendingApproval, 0, 0, originalPrice, priceAfterDiscount, expirationDate, type);
            if(objectsHolder.getBl().newCatalogItem(catalogItem)){
                Toast.makeText(getApplicationContext(), "Item added successfully!", Toast.LENGTH_LONG).show();
                clearFields();
            }
            else{
                Toast.makeText(getApplicationContext(), "Failed to add item...", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void clearFields() {
        catalogItemNameText.setText("");
        categoryText.setText("");
        descriptionText.setText("");
        expirationDateText.setText("");
        originalPriceText.setText("");
        priceAfterDiscountText.setText("");
    }
}
