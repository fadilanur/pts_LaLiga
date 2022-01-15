package com.example.laliga;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.laliga.Adapter.FavoritAdapter;
import com.example.laliga.Class.RealmHelper;
import com.example.laliga.Model.DataModel;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class FavoritActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FavoritAdapter favoriteAdapter;
    private List<DataModel> dataModelList;

    Realm realm;
    RealmHelper realmHelper;
    private FavoritAdapter favoritAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorit);

        recyclerView = findViewById(R.id.recyclerviewFavorite);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);


        realmHelper = new RealmHelper(realm);
        dataModelList = new ArrayList<>();

        dataModelList= realmHelper.getAllMahasiswa();

        show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        favoriteAdapter.notifyDataSetChanged();
        show();
    }

    public void show(){
        favoritAdapter = new FavoritAdapter(dataModelList, new FavoritAdapter.Callback() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(getApplicationContext(), DetailFavoritActivity.class);
                intent.putExtra("id", dataModelList.get(position).getId());
                intent.putExtra("image", dataModelList.get(position).getImage());
                intent.putExtra("nama", dataModelList.get(position).getNama());
                intent.putExtra("deskripsi", dataModelList.get(position).getDeskripsi());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(favoriteAdapter);
    }
}

