package com.example.pasargada;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;

public class MainActivity extends IntroActivity {
    //recupera a instancia e aponta para o nó raiz do firebase
    private DatabaseReference mReferenceDatabase = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //DESCOMENTAR PARA ADICIONAR PAIS NO FIREBASE
        //addCountryFirebase();
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
}