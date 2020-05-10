package com.example.mobilephoneshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {

    String[] addresses = {"Type user email"};
    String subject = "Order from Mobile Shop";
    String emailText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        setTitle("Your Order");

        Intent getOrderIntent = getIntent();
        String username = getOrderIntent.getStringExtra("userName");
        String goodsName = getOrderIntent.getStringExtra("goodsName");
        int quantity = getOrderIntent.getIntExtra("quantity", 0);
        double price = getOrderIntent.getDoubleExtra("price", 0);
        double orderPrice = getOrderIntent.getDoubleExtra("orderPrice", 0);

        emailText = "Customer Name  " + username +
                "\n" + "Goods Name  " + goodsName +
                "\n" + "Goods Quantity  " + quantity +
                "\n" + "Goods Price  " + price +
                "\n" + "Order Price  " + orderPrice;

        TextView orderTextView = findViewById(R.id.userNameTextView);
        orderTextView.setText(emailText);

    }

    public void submitOrder(View view) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, emailText);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

}
