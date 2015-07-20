package groupon.edanpossey.groupon1.Client.PresentationLayer;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.TextUtils;
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

public class EditCatalogItem extends ActionBarActivity {

    EditText catalogItemNameText, categoryText, descriptionText, expirationDateText, originalPriceText, priceAfterDiscountText, linkText;
    Button approveItem;
    CatalogItem currentItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_catalog_item);

        Intent intent = getIntent();
        currentItem = ObjectsHolder.getCurrentCatalogItem();

        catalogItemNameText = (EditText) findViewById(R.id.catalogItemNameTextView);
        catalogItemNameText.setText(currentItem.getName());

        categoryText = (EditText) findViewById(R.id.categoryTextView);
        categoryText.setText(currentItem.getCategory());

        descriptionText = (EditText) findViewById(R.id.descriptionTextView);
        descriptionText.setText(currentItem.getDescription());

        expirationDateText = (EditText) findViewById(R.id.expirationDateTextView);
        expirationDateText.setText(currentItem.getExpirationDate().toString());

        originalPriceText = (EditText) findViewById(R.id.originalPriceTextView);
        originalPriceText.setText(currentItem.getOriginalPrice() + "");

        priceAfterDiscountText = (EditText) findViewById(R.id.priceAfterDiscountTextView);
        priceAfterDiscountText.setText(currentItem.getPriceAfterDiscount() + "");

        linkText = (EditText) findViewById(R.id.linkTextView);
        approveItem = (Button) findViewById(R.id.approveItemButton);
        if(currentItem.getStatus() == CatalogItem.CatalogItemStatus.Approved){
            approveItem.setVisibility(View.GONE);
        }

        if(ObjectsHolder.getCurrentUser().getBusiness() == null){
            linkText.setVisibility(View.VISIBLE);
            linkText.setText(currentItem.getPublishedBy().getBusinessName());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_catalog_item, menu);
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

    public void editItem_ButtonClick(View view) {
        if(!missingParameters()){
            String name = catalogItemNameText.getText().toString();
            String category = categoryText.getText().toString();
            String description = descriptionText.getText().toString();

            CatalogItem.CatalogItemType type = CatalogItem.CatalogItemType.NotSocial;
            Business publishedBy = ObjectsHolder.getCurrentUser().getBusiness();
            if(publishedBy == null){
                type = CatalogItem.CatalogItemType.Social;
                String linkString = linkText.getText().toString();
                publishedBy = new Business(null, linkString, null, null, null);
            }

            Date expirationDate = Date.valueOf(expirationDateText.getText().toString());

            double originalPrice = Double.parseDouble(originalPriceText.getText().toString());
            double priceAfterDiscount = Double.parseDouble(priceAfterDiscountText.getText().toString());

            CatalogItem catalogItem = new CatalogItem(publishedBy, name, category, description,
                    CatalogItem.CatalogItemStatus.PendingApproval, 0, 0, originalPrice, priceAfterDiscount, expirationDate, type);
            if(ObjectsHolder.getBl().updateCatalogItem(currentItem, catalogItem)){
                Toast.makeText(getApplicationContext(), "Item edited successfully!", Toast.LENGTH_LONG).show();
            }
            else{
                Toast.makeText(getApplicationContext(), "Failed to edit item...", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void deleteItem_ButtonClick(View view){
        if(ObjectsHolder.getBl().deleteCatalogItem(currentItem)){
            Toast.makeText(getApplicationContext(), "Item deleted successfully!", Toast.LENGTH_LONG).show();
            finish();
        }
        else{
            Toast.makeText(getApplicationContext(), "Item couldn't be deleted successfully!", Toast.LENGTH_LONG).show();
        }
    }

    public void approveItem_ButtonClick(View view){
        if(ObjectsHolder.getBl().approvePendingCatalogItem(currentItem)){
            Toast.makeText(getApplicationContext(), "Item approved.", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(getApplicationContext(), "Item couldn't be approved.", Toast.LENGTH_LONG).show();
        }
    }

    private boolean missingParameters() {
        boolean missing = false;

        if(TextUtils.isEmpty(catalogItemNameText.getText())){
            missing = true;
            catalogItemNameText.setError("Item name can't be empty.");
        }
        if(TextUtils.isEmpty(originalPriceText.getText())){
            missing = true;
            originalPriceText.setError("Original price can't be empty.");
        }
        if(TextUtils.isEmpty(priceAfterDiscountText.getText())){
            missing = true;
            priceAfterDiscountText.setError("Price after discount can't be empty.");
        }
        if(TextUtils.isEmpty(categoryText.getText())){
            missing = true;
            categoryText.setError("Category can't be empty.");
        }
        if(TextUtils.isEmpty(descriptionText.getText())){
            missing = true;
            descriptionText.setError("Description can't be empty.");
        }
        if(TextUtils.isEmpty(expirationDateText.getText())){
            missing = true;
            expirationDateText.setError("Expiration date can't be empty.");
        }

        if(ObjectsHolder.getCurrentUser().getBusiness() == null){
            if(TextUtils.isEmpty(linkText.getText())){
                missing = true;
                linkText.setError("Link can't be empty.");
            }
        }
        return missing;
    }
}
