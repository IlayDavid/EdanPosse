package groupon.edanpossey.groupon1.Client.PresentationLayer;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import groupon.edanpossey.groupon1.Entities.AccessLevel;
import groupon.edanpossey.groupon1.Entities.CatalogItem;
import groupon.edanpossey.groupon1.Entities.Order;
import groupon.edanpossey.groupon1.R;

public class ViewCatalogItem extends ActionBarActivity {
    TextView catalogNumber, itemName, publishedBy, originalPrice, priceAfterDiscount, type, category, description, expirationDate, averageRating;
    Button editItem, orderItem;
    CatalogItem currentItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_catalog_item);

        currentItem = ObjectsHolder.getCurrentCatalogItem();

        catalogNumber = (TextView) findViewById(R.id.catalogNumberTextView);
        catalogNumber.setText(currentItem.getCatalogNumber() + "");

        itemName = (TextView) findViewById(R.id.itemNameTextView);
        itemName.setText(currentItem.getName() + "");

        publishedBy = (TextView) findViewById(R.id.publishedByTextView);
        publishedBy.setText(currentItem.getPublishedBy().getBusinessName() + "");

        originalPrice = (TextView) findViewById(R.id.originalPriceTextView);
        originalPrice.setText(currentItem.getOriginalPrice() + "");

        priceAfterDiscount = (TextView) findViewById(R.id.priceAfterDiscountTextView);
        priceAfterDiscount.setText(currentItem.getPriceAfterDiscount() + "");

        type = (TextView) findViewById(R.id.typeTextView);
        type.setText(currentItem.getType().toString() + "");

        category = (TextView) findViewById(R.id.categoryTextView);
        category.setText(currentItem.getCategory() + "");

        description = (TextView) findViewById(R.id.descriptionTextView);
        description.setText(currentItem.getDescription() + "");

        expirationDate = (TextView) findViewById(R.id.expirationDateTextView);
        expirationDate.setText(currentItem.getExpirationDate().toString() + "");

        averageRating = (TextView) findViewById(R.id.averageRatingTextView);
        double avgRating = (double)currentItem.getSumOfRatings()/currentItem.getRatings();
        averageRating.setText(avgRating + "");

        editItem = (Button) findViewById(R.id.editItemButton);
        orderItem = (Button) findViewById(R.id.orderButton);
        if(ObjectsHolder.getCurrentUser().getAccessLevel() == AccessLevel.Administrator){
            editItem.setVisibility(View.VISIBLE);
            orderItem.setVisibility(View.GONE);
        }
        else{
            editItem.setVisibility(View.GONE);
            orderItem.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_catalog_item, menu);
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

    public void editItem_ButtonClick(View view){
        Intent i = new Intent(this, EditCatalogItem.class);
        startActivity(i);
    }

    public void order_ButtonClick(View view){
        Order order = new Order();
        order.setCatalogItem(currentItem);
        order.setOrderedBy(ObjectsHolder.getCurrentUser());
        if(ObjectsHolder.getBl().newOrder(order)){
            Toast.makeText(getApplicationContext(), "Order was successful. An E-mail was sent to you with a serial code.", Toast.LENGTH_LONG).show();
        }
    }
}
