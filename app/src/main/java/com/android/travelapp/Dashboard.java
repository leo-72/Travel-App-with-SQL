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
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {
    TextView txtNama, txtEmail;
    Button checkTickets;

    AlertDialog alertDialog;
    MenuInflater inflater;

    private ArrayList<String> al_img_tour = new ArrayList<>();
    private ArrayList<String> al_name_tour = new ArrayList<>();
    private ArrayList<String> al_desc_tour = new ArrayList<>();
    private ArrayList<Integer> al_price_tour = new ArrayList<>();
    private ArrayList<String> al_location = new ArrayList<>();

    SharedPreferences preferences;

    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_TOTAL_PRICE = "total_price";
    private static final String KEY_NAME_TOUR = "name_tour";
    private static final String KEY_COUNT_ITEMS = "count_items";

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        txtNama = findViewById(R.id.tv_fullname);
        txtEmail = findViewById(R.id.tv_email);
        checkTickets = findViewById(R.id.check_ticket);

        checkTickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameView = preferences.getString(KEY_NAME, null);
                String emailView = preferences.getString(KEY_EMAIL, null);
                String phoneView = preferences.getString(KEY_PHONE, null);

                String nameTourView = preferences.getString(KEY_NAME_TOUR, null);
                String totalItemsView = preferences.getString(KEY_COUNT_ITEMS, null);
                String totalPriceView = preferences.getString(KEY_TOTAL_PRICE, null);

                if (nameView == "" || emailView == "" || phoneView == "" || nameTourView == "" || totalItemsView == "" || totalPriceView == "" ||
                        nameView == null || emailView == null || phoneView == null || nameTourView == null || totalItemsView == null || totalPriceView == null) {
                    AlertDialog dialog = new AlertDialog.Builder(Dashboard.this)
                            .setTitle("Check Tickets")
                            .setMessage("\nData is Empty")
                            .setIcon(android.R.drawable.ic_dialog_info)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent intent = new Intent(Dashboard.this, Dashboard.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }).show();
                }else if (nameView == nameView || emailView == emailView || phoneView == phoneView || nameTourView == nameTourView || totalItemsView == totalItemsView || totalPriceView == totalPriceView){
                    Intent intent = new Intent(Dashboard.this, Tickets.class);
                    startActivity(intent);
                }
            }
        });

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
        al_location.add("Pantai Pandawa, Bali");

        //Pantai Kuta
        al_img_tour.add("https://www.rentalmobilbali.net/wp-content/uploads/2016/05/Objek-Wisata-Kuta-Bali.jpg");
        al_name_tour.add("Pantai Kuta");
        al_desc_tour.add("Pantai Kuta terletak di kelurahan Kuta, Kabupaten Badung, menjadi objek wisata alam pantai yang paling menarik dan indah di pulau Dewata Bali, tempat rekreasi alam pesisir ini melengkapi destinasi tour populer di kawasan pariwisata Bali Selatan yang memang populer dengan wilayah pesisir yang memiliki pasir putih.");
        al_price_tour.add(5000);
        al_location.add("Pantau Kuta, Bali");

        //Tanah Lot
        al_img_tour.add("https://www.rentalmobilbali.net/wp-content/uploads/2016/05/Tanah-Lot-Tabanan-Bali.jpg");
        al_name_tour.add("Tanah Lot");
        al_desc_tour.add("Tanah Lot berada di Desa Beraban, Kecamatan Kediri, Kabupaten Tabanan. Jaraknya sekitar 13 km ke arah barat kota Tabanan. Dari bandara udara Ngurah Rai, menuju lokasi pura dapat di tempuh dalam waktu kurang lebih 1 jam 20 menit.");
        al_price_tour.add(20000);
        al_location.add("Tanah Lot, Bali");


        //Pura Uluwatu
        al_img_tour.add("https://www.rentalmobilbali.net/wp-content/uploads/2016/03/Sunset-Pura-Uluwatu.jpg");
        al_name_tour.add("Pura Uluwatu");
        al_desc_tour.add("Lokasi pura Luhur Uluwatu tepatnya berada di desa Pecatu, Kecamatan Kuta Selatan, Kabupaten Badung, Bali. Jarak yang di tempuh dari Bandara Ngurah Rai menuju lokasi pura Luhur Uluwatu Bali, sekitar 1 jam ke arah selatan dengan kendaraan pribadi.");
        al_price_tour.add(30000);
        al_location.add("Pura Uluwatu, Bali");

        //Tanjung Benoa
        al_img_tour.add("https://www.rentalmobilbali.net/wp-content/uploads/2013/11/tanjung-benoa-watersport.jpg");
        al_name_tour.add("Tanjung Benoa");
        al_desc_tour.add("Lokasi pantai Tanjung Benoa ada di kawasan Bali selatan, berdekatan dengan kawasan wisata Nusa Dua Bali. Alamatnya adalah Kelurahan Tanjung Benoa, Kecamatan Kuta Selatan, Kabupaten Badung.");
        al_price_tour.add(5000);
        al_location.add("Tanjung Benoa, Bali");

        //Pura Ulun Danu
        al_img_tour.add("https://www.rentalmobilbali.net/wp-content/uploads/2016/05/Objek-Wisata-Pura-Ulun-Danu-Bedugul.jpg");
        al_name_tour.add("Pura Ulun Danu");
        al_desc_tour.add("Lokasi pura Ulun Danu Beratan berada di sisi barat Danau Bedugul. Alamatnya berada di jalan Raya Bedugul, Candi Kuning, Kecamatan Baturiti Kabupaten Tabanan. Apabila anda memilih menginap di salah satu hotel yang berada di tepi pantai Kuta, maka akan memerlukan waktu tempuh sekitar 2 jam perjalanan untuk sampai di kawasan tempat wisata Bedugul Tabanan.");
        al_price_tour.add(30000);
        al_location.add("Pura Ulun Danu, Bali");

        //Pantai Parai Tenggiri
        al_img_tour.add("https://ecs7.tokopedia.net/blog-tokopedia-com/uploads/2020/06/Pantai-Parai-Tenggiri.jpg");
        al_name_tour.add("Pantai Parai Tenggiri");
        al_desc_tour.add("Pantai Parai Tenggiri terletak tepat di sebelah Pantai Matras yaitu berlokasi Sungailiat, Bangka, Kepulauan Bangka Belitung. Sama seperti Pantai Matras yang terkenal dengan keindahannya, Pantai Parai Tenggiri juga menawarkan keelokan tersendiri.");
        al_price_tour.add(30000);
        al_location.add("Pantai Parai Tenggiri, Bangka Belitung");

        //Nusa Dua
        al_img_tour.add("https://ecs7.tokopedia.net/blog-tokopedia-com/uploads/2020/06/Nusa-Dua-Bali.jpg");
        al_name_tour.add("Nusa Dua (Bali)");
        al_desc_tour.add("Lokasi Nusa Dua tempat wisata Bali, terletak di paling ujung bagian tenggara pulau Bali. Nusa Dua berjarak sekitar 40 kilometer dari kota Denpasar. Jika dari dari Bandara Internasional Ngurah Rai, jaraknya kurang lebih 8 kilometer, atau sekitar tiga puluh menit perjalanan dengan menggunakan mobil.");
        al_price_tour.add(5000);
        al_location.add("Nusa Dua, Bali");

        //Gunung Rinjani
        al_img_tour.add("https://ecs7.tokopedia.net/blog-tokopedia-com/uploads/2020/06/gunung-rinjani.jpg");
        al_name_tour.add("Gunung Rinjani");
        al_desc_tour.add("Gunung Rinjani adalah gunung yang berlokasi di Pulau Lombok, Nusa Tenggara Barat. Gunung yang merupakan gunung berapi kedua tertinggi di Indonesia dengan ketinggian 3.726 mdpl serta terletak pada lintang 8º25' LS dan 116º28' BT ini merupakan gunung favorit bagi pendaki Indonesia karena keindahan pemandangannya.");
        al_price_tour.add(7500);
        al_location.add("Gunung Rinjani, Lombok, NTB");

        //Danau Toba
        al_img_tour.add("https://ecs7.tokopedia.net/blog-tokopedia-com/uploads/2018/11/danau-toba.jpg");
        al_name_tour.add("Danau Toba");
        al_desc_tour.add("Alamat lokasi Danau Toba berada di Sumatera Utara, dan dikelilingi oleh 7 kabupaten (Simalungun, Toba Samosir, Tapanuli Utara, Humbang Hasundutan, Dairi, Karo, dan Samosir). Untuk mencapai kawasan ini sobat native harus masuk ke pintu masuknya terlebih dahulu yang berada di Medan. Menuju ke Kota Medan bisa menggunakan pesawat atau dengan menggunakan bus.");
        al_price_tour.add(5000);
        al_location.add("Danau Toba, Sumatra Utara");

        //Nusa Penida
        al_img_tour.add("https://ecs7.tokopedia.net/blog-tokopedia-com/uploads/2018/11/nusa-penida.jpg");
        al_name_tour.add("Nusa Penida");
        al_desc_tour.add("Nusa Penida adalah sebuah pulau kecil yang berada sebelah tenggara pulau Bali, dan terpisah oleh selat Badung. Di dekat pulau ini, terdapat 2 pulau kecil lain, yaitu pulau Nusa Lembongan dan pulau Nusa Ceningan. Ketiga pulau kecil yang ada di bagian tenggara pulau Bali, memiliki keunikan dan daya tarik tersendiri dan ada perbedaan satu sama lain. Ketiga pulau masuk dalam wilayah pemerintahan kabupaten Klungkung, provinsi Bali.");
        al_price_tour.add(10000);
        al_location.add("Nusa Penida, Bali");

        //Taman Laut Bunaken
        al_img_tour.add("https://www.itrip.id/wp-content/uploads/2020/10/Taman-Nasional-Bunaken-Surga-Bawah-Laut.jpg");
        al_name_tour.add("Taman Laut Bunaken");
        al_desc_tour.add("Taman Nasional Bunaken adalah taman laut yang terletak di Sulawesi Utara, Indonesia. Taman ini terletak di Segitiga Terumbu Karang yang menjadi habitat bagi 390 spesies terumbu karang[2] dan juga berbagai spesies ikan, moluska, reptil, dan mamalia laut. Taman Nasional Bunaken merupakan perwakilan ekosistem laut Indonesia, meliputi padang rumput laut, terumbu karang, dan ekosistem pantai.");
        al_price_tour.add(25000);
        al_location.add("Taman Laut Bunaken, Sulawesi Utara");

        //Wakatobi
        al_img_tour.add("https://ecs7.tokopedia.net/blog-tokopedia-com/uploads/2018/11/wakatobi.jpg");
        al_name_tour.add("Wakatobi");
        al_desc_tour.add("Taman Nasional Wakatobi adalah salah satu taman nasional di Indonesia. Letaknya di Kabupaten Wakatobi, Sulawesi Tenggara.");
        al_price_tour.add(160000);
        al_location.add("Wakatobi, Sulawesi Tenggara");

        //Kepulauan Raja Ampat (Papua Barat)
        al_img_tour.add("https://ecs7.tokopedia.net/blog-tokopedia-com/uploads/2018/11/raja-ampat.jpg");
        al_name_tour.add("Kepulauan Raja Ampat (Papua Barat)");
        al_desc_tour.add("Kabupaten Raja Ampat adalah salah satu kabupaten di provinsi Papua Barat, Indonesia. Ibukota kabupaten ini terletak di Waisai. Kabupaten ini memiliki 610 pulau, termasuk kepulauan Raja Ampat. Empat di antaranya, yakni Pulau Misool, Salawati, Batanta dan Waigeo, merupakan pulau-pulau besar. Dari seluruh pulau hanya 35 pulau yang berpenghuni sedangkan pulau lainnya tidak berpenghuni dan sebagian besar belum memiliki nama. Kabupaten ini memiliki total luas 67.379,60 km² dengan rincian luas daratan 7.559,60 km² dan luas lautan 59.820,00 km².");
        al_price_tour.add(500000);
        al_location.add("Kepulauan Raja Ampat, Papua Barat");

        //Gunung Bromo (Jawa Timur)
        al_img_tour.add("https://ecs7.tokopedia.net/blog-tokopedia-com/uploads/2018/11/gunung-bromo.jpg");
        al_name_tour.add("Gunung Bromo (Jawa Timur)");
        al_desc_tour.add("Gunung Bromo berlokasi dan terletak di empat kabupaten pemerintahan Provinsi Jawa Timur. Yaitu di antara Kaputen Malang , Kabupaten Pasuruan, Kabupaten Probolinggo dan Kapupaten Lumajang.");
        al_price_tour.add(34000);
        al_location.add("Gunung Bromo,Jawa Timur");

        //Pulau Komodo (Nusa Tenggara Timur)
        al_img_tour.add("https://ecs7.tokopedia.net/blog-tokopedia-com/uploads/2018/11/pulau-komodo.jpg");
        al_name_tour.add("Pulau Komodo (NTT)");
        al_desc_tour.add("Pulau Komodo adalah sebuah pulau yang terletak di Kepulauan Nusa Tenggara, berada di sebelah timur Pulau Sumbawa, yang dipisahkan oleh Selat Sape. Pulau Komodo dikenal sebagai habitat asli hewan komodo.");
        al_price_tour.add(5000);
        al_location.add("Pulau Komodo, Nusa Tenggara Timur");

        //Candi Borobudur (Jawa Tengah)
        al_img_tour.add("https://ecs7.tokopedia.net/blog-tokopedia-com/uploads/2018/11/candi-borobudur.jpg");
        al_name_tour.add("Candi Borobudur (Jawa Tengah)");
        al_desc_tour.add("Lokasi Borobudur berada di Magelang, Provinsi Jawa Tengah. Objek wisata ini berada sekitar 100 km dari Semarang, 86 km dari Surakarta, dan 40 km dari DI Yogyakarta. Dan karena lebih dekat dari Yogyakarta dan lebih sering pula dijadikan agenda obyek wisata dari kegiatan promosi pariwisata dari Yogyakarta yang lebih intensif, sebagian orang Indonesia mengira Candi Borobudur ini berada di Daerah Istimewa Yogyakarta.");
        al_price_tour.add(50000);
        al_location.add("Candi Borobudur, Jawa Tengah");

        //Tana Toraja (Sulawesi Selatan)
        al_img_tour.add("https://blog.reservasi.com/wp-content/uploads/2016/06/tempat-wisata-di-tana-toraja-1.jpg");
        al_name_tour.add("Tana Toraja (Sulawesi Selatan)");
        al_desc_tour.add("Kabupaten Tana Toraja adalah salah satu kabupaten yang berada di provinsi Sulawesi Selatan, Indonesia. Ibu kota dari kabupaten ini ada di kecamatan Makale. Tana Toraja memiliki luas wilayah 2.054,30 km² dan pada tahun 2021 memiliki penduduk sebanyak 270.489 jiwa dengan kepadatan 132 jiwa/km².");
        al_price_tour.add(10000);
        al_location.add("Tana Toraja, Sulawesi Selatan");

        //Danau Kelimutu (NTT)
        al_img_tour.add("https://ecs7.tokopedia.net/blog-tokopedia-com/uploads/2018/11/danau-kelimutu.jpg");
        al_name_tour.add("Danau Kelimutu (NTT)");
        al_desc_tour.add("Tiwu atau Danau Kelimutu di bagi atas tiga bagian yang sesuai dengan warna – warna air danau yang terdapat didalamnya. Danau Kelimutu terdapat di provinsi? Tepatnya Pulau Flores Provinsi Nusa Tenggara Timur. Gunung berapi kelimutu dengan ketinggian 5.377 kaki tinggi menjulang berdiri di Desa Pemo Kabupaten Ende mempunyai kisah mistery yang akan dibahas dalam artikel ini.");
        al_price_tour.add(5000);
        al_location.add("Danau Kelimutu, NTT");

        //Kawah Ijen (Jawa Timur)
        al_img_tour.add("https://royaltour.web.id/wp-content/uploads/2017/10/paket-wisata-kawah-ijen-2-hari-1-malam.jpg");
        al_name_tour.add("Kawah Ijen (Jawa Timur)");
        al_desc_tour.add("Mengenai letak dan lokasi Kawah Ijen, ada yang menyebutnya Kawah Ijen Banyuwangi, ada pula yang menyebutnya Kawah Ijen Bondowoso, wajar saja, karna wisata gunung Ijen merupakan bagian dari Taman Nasional Baluran yang terletak diperbatasan 2 daerah, yaitu wilayah Kecamatan Licin, Kabupaten Banyuwangi dan Kecamatan Klobang, Kabupaten Bondowoso, jadi identik dengan wisata Banyuwangi dan Bondowoso.");
        al_price_tour.add(7500);
        al_location.add("Kawah Ijen, Jawa Timur");

        RecycleViewAdapterProcess();

    }

    private void RecycleViewAdapterProcess() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecycleViewAdapter adapter = new RecycleViewAdapter(al_img_tour, al_name_tour, al_desc_tour, al_price_tour, al_location, this);

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
                        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"fihdanps@gmail.com"});
                        intent.putExtra(Intent.EXTRA_SUBJECT , "TES DULS YE BANG");
                        intent.putExtra(Intent.EXTRA_TEXT , "Travel App");
                        intent.setType("message/rfc822");
                        startActivity(Intent.createChooser(intent , "Choose Your Apps : "));
                    }
                })
                .show();

    }
    private void getLoc(){
        alertDialog = new AlertDialog.Builder(Dashboard.this)
                .setIcon(android.R.drawable.ic_dialog_info)
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
        Intent intent = new Intent(Dashboard.this, LoginPage.class);
        startActivity(intent);
        finish();
    }
}