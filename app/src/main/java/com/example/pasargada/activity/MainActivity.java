package com.example.pasargada.activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.pasargada.R;
import com.example.pasargada.adapter.AdapterCountry;
import com.example.pasargada.model.Country;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends IntroActivity {
    //recupera a instancia e aponta para o nó raiz do firebase
    private DatabaseReference mReferenceDatabase = FirebaseDatabase.getInstance().getReference();
    //listagem de paises
    private RecyclerView mRecyclerView;
    private AdapterCountry mAdapterCountry;
    private List<Country> mCountries = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //DESCOMENTAR PARA ADICIONAR PAIS NO FIREBASE
        //addCountryFirebase();
        //fillRecyclerView();
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
        DatabaseReference countrys = mReferenceDatabase.child("countrys");
        Country country = new Country();
        country.setName("Portugal");
        countrys.push().setValue(country);
    }

    public void fillRecyclerView(){
        mRecyclerView = findViewById(R.id.recyclerViewCountries);
        //configurar adapter para receber e formatar os dados para o recyclerview
        mAdapterCountry = new AdapterCountry(mCountries, this);

        //configurar recycler
        //instancia o gerenciador do layout
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
        //recycler tem tamanho fixo
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapterCountry);

    }
}