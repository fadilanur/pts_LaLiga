package com.example.laliga;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.laliga.Class.RealmHelper;
import com.example.laliga.Model.DataModel;
import com.squareup.picasso.Picasso;

import java.security.AlgorithmParameterGenerator;

import io.realm.Realm;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    private Bundle bundle;
    private TextView tvName;
    private TextView tvDeskripsi;
    private ImageView imageView;
    private Button btnSimpan;

    private String image;
    private String nama;
    private String deskripsi;

    private Realm realm;
    private RealmHelper realmHelper;
    private DataModel dataModel;
    private AlgorithmParameterGenerator Realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imageView = findViewById(R.id.imageDetail);
        tvName = findViewById(R.id.tvNamaDetail);
        tvDeskripsi = findViewById(R.id.tvDeskripsiDetail);
        btnSimpan = findViewById(R.id.btnButtonSimpan);

        bundle = getIntent().getExtras();
        if (bundle != null){
            image = bundle.getString("image");
            nama = bundle.getString("nama");
            deskripsi = bundle.getString("deskripsi");
        }

        tvName.setText(nama);
        tvDeskripsi.setText(deskripsi);
        Picasso.get()
                .load(image)
                .into(imageView);

    }

    @Override
    public void onClick(View v) {
        if (v.equals(btnSimpan)){
            Toast.makeText(getApplicationContext(), "Berhasil disimpan!", Toast.LENGTH_SHORT).show();

            bundle = getIntent().getExtras();
            if (bundle != null){
                image = bundle.getString("image");
                nama = bundle.getString("nama");
                deskripsi = bundle.getString("deskripsi");
            }

            dataModel = new DataModel();
            dataModel.setImage(image);
            dataModel.setNama(nama);
            dataModel.setDeskripsi(deskripsi);

            realmHelper = new RealmHelper(realm);
            realmHelper.Save(dataModel);
        }
    }
}
