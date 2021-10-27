package com.android.travelapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {
    TextView txtNama, txtEmail;

    AlertDialog alertDialog;
    MenuInflater inflater;

    private ArrayList<String> al_img_tour = new ArrayList<>();
    private ArrayList<String> al_name_tour = new ArrayList<>();
    private ArrayList<String> al_desc_tour = new ArrayList<>();
    private ArrayList<Integer> al_price_tour = new ArrayList<>();

    SharedPreferences preferences;

    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_USER = "user";
    private static final String KEY_PASS = "pass";
    private static final String KEY_REPASS = "repass";

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        txtNama = findViewById(R.id.tv_fullname);
        txtEmail = findViewById(R.id.tv_email);

        preferences = getSharedPreferences("userInfo", 0);

        String namaView = preferences.getString(KEY_NAME, null);
        String emailView = preferences.getString(KEY_EMAIL, null);

        if (namaView != null || emailView != null){
            txtNama.setText(namaView);
            txtEmail.setText(emailView);
        }

        getData();

        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
    }

    private void getData() {
        //Pantai Pandawa
        al_img_tour.add("https://www.rentalmobilbali.net/wp-content/uploads/2016/05/Pantai-Pandawa-Kutuh.jpg");
        al_name_tour.add("Pantai Pandawa");
        al_desc_tour.add("Pantai Pandawa berlokasi di Bali Selatan, tepatnya di Desa Kutuh, Kecamatan Kuta Selatan, Kabupaten Badung, Bali. Dahulu pantai ini dikenal sebagai Secret Beach karena lokasinya yang berada di belakang tebing-tebing tinggi ditumbuhi oleh semak belukar.");
        al_price_tour.add(8000);

        //Pantai Kuta
        al_img_tour.add("https://www.rentalmobilbali.net/wp-content/uploads/2016/05/Objek-Wisata-Kuta-Bali.jpg");
        al_name_tour.add("Pantai Kuta");
        al_desc_tour.add("Pantai Kuta terletak di kelurahan Kuta, Kabupaten Badung, menjadi objek wisata alam pantai yang paling menarik dan indah di pulau Dewata Bali, tempat rekreasi alam pesisir ini melengkapi destinasi tour populer di kawasan pariwisata Bali Selatan yang memang populer dengan wilayah pesisir yang memiliki pasir putih.");
        al_price_tour.add(5000);

        //Tanah Lot
        al_img_tour.add("https://www.rentalmobilbali.net/wp-content/uploads/2016/05/Tanah-Lot-Tabanan-Bali.jpg");
        al_name_tour.add("Tanah Lot");
        al_desc_tour.add("Tanah Lot berada di Desa Beraban, Kecamatan Kediri, Kabupaten Tabanan. Jaraknya sekitar 13 km ke arah barat kota Tabanan. Dari bandara udara Ngurah Rai, menuju lokasi pura dapat di tempuh dalam waktu kurang lebih 1 jam 20 menit.");
        al_price_tour.add(20000);

        //Pura Uluwatu
        al_img_tour.add("https://www.rentalmobilbali.net/wp-content/uploads/2016/03/Sunset-Pura-Uluwatu.jpg");
        al_name_tour.add("Pura Uluwatu");
        al_desc_tour.add("Lokasi pura Luhur Uluwatu tepatnya berada di desa Pecatu, Kecamatan Kuta Selatan, Kabupaten Badung, Bali. Jarak yang di tempuh dari Bandara Ngurah Rai menuju lokasi pura Luhur Uluwatu Bali, sekitar 1 jam ke arah selatan dengan kendaraan pribadi.");
        al_price_tour.add(30000);

        //Tanjung Benoa
        al_img_tour.add("https://www.rentalmobilbali.net/wp-content/uploads/2013/11/tanjung-benoa-watersport.jpg");
        al_name_tour.add("Tanjung Benoa");
        al_desc_tour.add("Lokasi pantai Tanjung Benoa ada di kawasan Bali selatan, berdekatan dengan kawasan wisata Nusa Dua Bali. Alamatnya adalah Kelurahan Tanjung Benoa, Kecamatan Kuta Selatan, Kabupaten Badung.");
        al_price_tour.add(5000);

        //Pura Ulun Danu
        al_img_tour.add("https://www.rentalmobilbali.net/wp-content/uploads/2016/05/Objek-Wisata-Pura-Ulun-Danu-Bedugul.jpg");
        al_name_tour.add("Pura Ulun Danu");
        al_desc_tour.add("Lokasi pura Ulun Danu Beratan berada di sisi barat Danau Bedugul. Alamatnya berada di jalan Raya Bedugul, Candi Kuning, Kecamatan Baturiti Kabupaten Tabanan. Apabila anda memilih menginap di salah satu hotel yang berada di tepi pantai Kuta, maka akan memerlukan waktu tempuh sekitar 2 jam perjalanan untuk sampai di kawasan tempat wisata Bedugul Tabanan.");
        al_price_tour.add(30000);

        RecycleViewAdapterProcess();
    }

    private void RecycleViewAdapterProcess() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecycleViewAdapter adapter = new RecycleViewAdapter(al_img_tour, al_name_tour, al_desc_tour, al_price_tour, this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.bar_call_center:
                callCenter();
                return true;
            case R.id.bar_email:
                emailCenter();
                return true;
            case R.id.bar_loc:
                getLoc();
                return true;
            case R.id.bar_edit_user:
                editUser();
                return true;
            case R.id.bar_logout:
                getLogout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void callCenter() {
        alertDialog = new AlertDialog.Builder(Dashboard.this)
                .setIcon(android.R.drawable.ic_dialog_dialer)
                .setTitle("Call Center")
                .setMessage("\n082139860827")
                .setNeutralButton("Call", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Uri uri = Uri.parse("082139860827");
                        Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                        intent.setData(Uri.fromParts("tel", String.valueOf(uri), null));

                        if (intent.resolveActivity(getPackageManager()) != null){
                            startActivity(intent);
                        }
                    }
                })
                .show();

    }
    private void emailCenter(){
        alertDialog = new AlertDialog.Builder(Dashboard.this)
                .setIcon(android.R.drawable.ic_dialog_email)
                .setTitle("Email")
                .setMessage("\nfihdanps@gmail.com")
                .setNeutralButton("Go to Email", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Intent.ACTION_SEND );
                        Uri uri = Uri.parse("fihdanps@gmail.com");
                        intent.putExtra(Intent.EXTRA_EMAIL, uri);
                        intent.putExtra(Intent.EXTRA_SUBJECT , "");
                        intent.putExtra(Intent.EXTRA_TEXT , "");
                        intent.setType("message/rfc822");
                        startActivity(Intent.createChooser(intent , "Choose Your Apps : "));
                    }
                })
                .show();

    }
    private void getLoc(){
        alertDialog = new AlertDialog.Builder(Dashboard.this)
                .setIcon(android.R.drawable.ic_dialog_email)
                .setTitle("Location")
                .setMessage("\nKota Madiun, Jawa Timur")
                .setNeutralButton("Go to Location", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Uri uri2 = Uri.parse("geo:0,0?q="+"Kota Madiun, Jawa Timur");
                        Intent mapIntent = new Intent(Intent.ACTION_VIEW, uri2);
                        mapIntent.setPackage("com.google.android.apps.maps");

                        if(mapIntent.resolveActivity(getPackageManager()) != null){
                            startActivity(mapIntent);
                        }
                    }
                })
                .show();

    }
    private void editUser(){
        Intent intent = new Intent(Dashboard.this, EditUser.class);
        startActivity(intent);

    }
    private void getLogout(){
        finish();
    }
}