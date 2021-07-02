package com.example.pasargada.activity;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.pasargada.R;
import com.example.pasargada.adapter.AdapterCountry;
import com.example.pasargada.config.ConfigFirebase;
import com.example.pasargada.helper.Base64Custom;
import com.example.pasargada.model.Country;
import com.example.pasargada.model.Document;
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
    private Document mDocument;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //remove botões back e next dos slides
        setButtonBackVisible(false);
        setButtonNextVisible(false);
        //addDocumentFirebase();
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

    public void addDocumentFirebase(){
        mDocument = new Document();
        mDocument.setName("Número de Utente");
        String country = "Portugal";
        String idDocument = Base64Custom.encodeBase64(country);
        mDocument.setId(idDocument);
        mDocument.setDescription("teste");
        mDocument.save();
    }

    public void btOpenHome(View view){
        startActivity(new Intent(this, HomeActivity.class));
    }

}