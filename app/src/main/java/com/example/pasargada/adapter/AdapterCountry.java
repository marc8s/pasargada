package com.example.pasargada.adapter;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pasargada.R;
import com.example.pasargada.model.Country;

import org.jetbrains.annotations.NotNull;

import java.util.List;
//A NUMERAÇÃO É PARA AUXILIAR A REPLICAÇÃO DO CODIGO COM A INDICAÇÃO DO
//PASSO A PASSO NA CRIAÇÃO DA CLASSE

//1. public class AdapterCountry extends RecyclerView.Adapter<>
//4. public class AdapterCountry extends RecyclerView.Adapter<AdapterCountry.MyViewHolder>
//5. implement methods
public class AdapterCountry extends RecyclerView.Adapter<AdapterCountry.MyViewHolder> {
    //7.
    List<Country> mCountries;
    Context mContext;
    public AdapterCountry(List<Country> countries, Context context){
        this.mCountries = countries;
        this.mContext = context;
        //int tamanho = getItemCount();
        //Log.i("tamanho lista", "adapter: " +tamanho);
    }


    //6. criar as visualizações, é preciso criar um layout xml para isso
    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        //converter xml em um objeto do tipo view
        View itemList = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_country, parent, false);
        return new MyViewHolder(itemList);
    }
    //8. exibição dos itens
    @Override
    public void onBindViewHolder(@NonNull @NotNull AdapterCountry.MyViewHolder holder, int position) {
        Country country = mCountries.get(position);
        holder.name.setText(country.getName());
    }
    //9.
    @Override
    public int getItemCount() {
        return mCountries.size();
    }

    //2. classe interna para poder exibir cada um dos dados dentro de um elemento de lista
    public class MyViewHolder extends RecyclerView.ViewHolder{
        //dados a serem exibidos na listagem
        TextView name;

        //3. create constructor
        public MyViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            //configura os dados
            name = itemView.findViewById(R.id.textViewAdapterCountry);
        }
    }
}
