package com.example.pasargada.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.pasargada.R;
import com.example.pasargada.adapter.AdapterCountry;
import com.example.pasargada.config.ConfigFirebase;
import com.example.pasargada.model.Country;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {


    //RECYCLERVIEW E ADAPTER
    private RecyclerView mRecyclerViewCountries;
    private AdapterCountry mAdapterCountry;
    private List<Country> mCountries = new ArrayList<>();
    //PREENCHER LISTA
    private DatabaseReference mReferenceFirebase = ConfigFirebase.getFirebaseDatabase();
    private DatabaseReference mCountryRef;
    private ValueEventListener mValueEventListenerCountries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        /*int tamanho = mCountries.size();
        Log.i("tamanho lista", "on main");
        logLista();*/
        mRecyclerViewCountries = findViewById(R.id.recyclerViewCountries);
        //CONFIGURANDO O ADAPTER
        //configurar adapter para receber e formatar os dados para o recyclerview
        mAdapterCountry = new AdapterCountry(mCountries, this);
        //CONFIGURANDO O RECYCLER
        //instancia o gerenciador do layout
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerViewCountries.setLayoutManager(layoutManager);
        mRecyclerViewCountries.setHasFixedSize(true);
        mRecyclerViewCountries.setAdapter(mAdapterCountry);
    }

    public void loadCountries(){
        mCountryRef = mReferenceFirebase.child("countries");
        mValueEventListenerCountries = mCountryRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                mCountries.clear();
                for (DataSnapshot dados: snapshot.getChildren()){
                    Log.i("dados", "retorno: " + dados.toString());
                    Country country = dados.getValue(Country.class);
                    Log.i("dados", "nomes: " + country.getName());
                    mCountries.add(country);
                }
                mAdapterCountry.notifyDataSetChanged();
                /*Log.i("tamanho lista", "on load");
                logLista();*/
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });

    }
    public void logLista(){
        //String nome = mCountries.get(1).getName();
        //Log.i("lista", "nome: " +nome);
        int tamanho = mCountries.size();

        Log.i("tamanho lista", " " +tamanho);
    }

    @Override
    protected void onStart(){
        super.onStart();
        loadCountries();
    }

    @Override
    protected void onStop(){
        super.onStop();
        mCountryRef.removeEventListener(mValueEventListenerCountries);
    }
}