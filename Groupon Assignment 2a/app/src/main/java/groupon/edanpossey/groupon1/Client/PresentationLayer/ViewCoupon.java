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
import android.widget.TextView;

import groupon.edanpossey.groupon1.Entities.Coupon;
import groupon.edanpossey.groupon1.R;

public class ViewCoupon extends ActionBarActivity {
    TextView couponCodeText, couponStatusText;
    EditText ratingText;
    Button viewCatalogItemButton, rateCouponButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_coupon);

        Coupon coupon = ObjectsHolder.getCurrentCoupon();

        couponCodeText = (TextView) findViewById(R.id.couponCodeTextView);
        couponCodeText.setText(coupon.getCouponCode() + "");

        couponStatusText = (TextView) findViewById(R.id.couponStatusTextView);
        couponStatusText.setText(coupon.getStatus().toString());

        ratingText = (EditText) findViewById(R.id.ratingTextView);
        viewCatalogItemButton = (Button) findViewById(R.id.viewItemButton);
        rateCouponButton = (Button) findViewById(R.id.rateButton);

        if(coupon.getStatus() == Coupon.CouponStatus.Used){
            rateCouponButton.setVisibility(View.VISIBLE);
            ratingText.setVisibility(View.VISIBLE);
        }
        else{
            rateCouponButton.setVisibility(View.GONE);
            ratingText.setVisibility(View.GONE);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_coupon, menu);
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

    public void rateCoupon_ButtonClick(View view){
        if(TextUtils.isEmpty(ratingText.getText())){
            ratingText.setError("The rating can't be empty.");
        }
        else{
            if((Long.parseLong(ratingText.getText().toString()) < 1) || (Long.parseLong(ratingText.getText().toString()) > 5)){
                ratingText.setError("The rating must be between 1 and 5.");
            }
            else{
                ObjectsHolder.getBl().rateCatalogItem(ObjectsHolder.getCurrentCoupon().getCatalogItem(), Long.parseLong(ratingText.getText().toString()));
            }
        }
    }

    public void viewItem_ButtonClick(View view){
        ObjectsHolder.setCurrentCatalogItem(ObjectsHolder.getCurrentCoupon().getCatalogItem());
        Intent i = new Intent(this, ViewCatalogItem.class);
        startActivity(i);
    }
}
