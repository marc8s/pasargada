package com.example.pasargada.model;

import com.example.pasargada.config.ConfigFirebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

public class Country {
    private String name, id;

    public Country() {

    }

    public void save(){
        DatabaseReference databaseReference = ConfigFirebase.getFirebaseDatabase();
        databaseReference.child("countries")
                .child(this.id)
                .setValue(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //usar exclude para não salvar no firebasedatabase
    @Exclude
    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }
}
