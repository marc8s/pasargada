package com.example.pasargada.model;

import com.example.pasargada.config.ConfigFirebase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

public class Document {
    private String name, id, description;

    public Document() {
    }

    public void save(){
        DatabaseReference databaseReference = ConfigFirebase.getFirebaseDatabase();
        databaseReference.child("documents")
                .child(this.id)
                .push()
                .setValue(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Exclude
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
