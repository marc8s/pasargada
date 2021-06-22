package com.example.pasargada.config;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ConfigFirebase {

    private static DatabaseReference mReferenceFirebase;
    //RETORNA UMA INSTANCIA DO FIREBASEDATABASE
    public static DatabaseReference getFirebaseDatabase(){
        if(mReferenceFirebase == null){
            mReferenceFirebase = FirebaseDatabase.getInstance().getReference();
        }
        return mReferenceFirebase;
    }
}
