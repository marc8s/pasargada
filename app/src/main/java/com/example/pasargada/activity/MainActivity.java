package com.example.pasargada.activity;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.pasargada.R;
import com.example.pasargada.adapter.AdapterCountry;
import com.example.pasargada.config.ConfigFirebase;
import com.example.pasargada.helper.Base64Custom;
import com.example.pasargada.model.Country;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends IntroActivity {

    private Country mCountry;

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
        //remove botões back e next dos slides
        setButtonBackVisible(false);
        setButtonNextVisible(false);
        //slides apresentados ao abrir a app
        addSlide(new FragmentSlide.Builder()
                .background(R.color.background_sliders)
                .fragment(R.layout.intro_1)
                .build()
        );
        addSlide(new FragmentSlide.Builder()
                .background(R.color.background_sliders)
                .fragment(R.layout.intro_2)
                .build()
        );
        addSlide(new FragmentSlide.Builder()
                .background(R.color.background_sliders)
                .fragment(R.layout.intro_3)
                .build()
        );
    }

    //Chamar esse método quando quiser adicionar um pais no firebase
    //Se parecer tudo correto e não estiver funcionando o realtimedatabase experimentar Build>Clean Projet e depois Build>Rebuild Projet
    public void addCountryFirebase(){
        mCountry = new Country();
        mCountry.setName("Inglaterra");
        String idCountry = Base64Custom.encodeBase64(mCountry.getName());
        mCountry.setId(idCountry);
        mCountry.save();
    }

   public void fillRecyclerView(){
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
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });
     }


    @Override
    protected void onStart(){
        super.onStart();
        //addCountryFirebase();
        //fillRecyclerView();
        loadCountries();

    }
}