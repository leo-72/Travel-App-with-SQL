package com.android.travelapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class Receipt extends AppCompatActivity {
    ImageView imgTour;
    TextView nameTour, totalPeople, priceTour, totalPrice, name, email, phone;
    Button btnConfirm;
    AlertDialog dialog;
    SharedPreferences preferences;

    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PHONE = "phone";

    private static final String KEY_IMG_TOUR = "img_tour";
    private static final String KEY_TOTAL_PRICE = "total_price";
    private static final String KEY_NAME_TOUR = "name_tour";
    private static final String KEY_COUNT_ITEMS = "count_items";
    private static final String KEY_PRICE_TOUR = "price_tour";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);

        imgTour = findViewById(R.id.img_tour);
        nameTour = findViewById(R.id.name_tour);
        totalPeople = findViewById(R.id.total_people);
        priceTour = findViewById(R.id.price_tour);
        totalPrice = findViewById(R.id.total_price);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        btnConfirm = findViewById(R.id.btn_confirm);

        preferences = getSharedPreferences("userInfo", 0);

        String nameView = preferences.getString(KEY_NAME, null);
        String emailView = preferences.getString(KEY_EMAIL, null);
        String phoneView = preferences.getString(KEY_PHONE, null);

        String imgTourView = preferences.getString(KEY_IMG_TOUR, null);
        String nameTourView = preferences.getString(KEY_NAME_TOUR, null);
        String totalItemsView = preferences.getString(KEY_COUNT_ITEMS, null);
        String priceView = preferences.getString(KEY_PRICE_TOUR, null);
        String totalPriceView = preferences.getString(KEY_TOTAL_PRICE, null);

        if (nameView != null || emailView != null || phoneView != null || nameTourView != null || totalItemsView != null || priceView != null || totalPriceView != null || imgTourView != null){
            Glide.with(this).asBitmap().load(imgTourView).into(imgTour);
            name.setText(nameView);
            email.setText(emailView);
            phone.setText(phoneView);
            nameTour.setText(nameTourView);
            priceTour.setText("Rp"+priceView);
            totalPeople.setText(totalItemsView + " Orang");
            totalPrice.setText("Rp"+totalPriceView);
        }

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new AlertDialog.Builder(Receipt.this)
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setTitle("Message")
                        .setMessage("\nYakin ingin memesan tempat Wisata ini?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                                Toast.makeText(Receipt.this, "Berhasil dipesan", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(Receipt.this, Dashboard.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                                Toast.makeText(Receipt.this, "Gagal dipesan", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(Receipt.this, Dashboard.class);
                                startActivity(intent);
                            }
                        })
                        .show();
            }
        });
    }
}