package com.example.pasargada.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pasargada.R;
import com.example.pasargada.model.Document;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AdapterDocument extends RecyclerView.Adapter<AdapterDocument.MyViewHolder> {

    List<Document> mDocuments;
    Context mContext;
    public AdapterDocument(List<Document> documents, Context context){
        this.mDocuments = documents;
        this.mContext = context;
    }

    @NonNull
    @NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemList = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_document, parent, false);
        return new AdapterDocument.MyViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AdapterDocument.MyViewHolder holder, int position) {
        Document document = mDocuments.get(position);
        holder.name.setText(document.getName());
        holder.description.setText(document.getDescription());
    }

    @Override
    public int getItemCount() {
        return mDocuments.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name, description;

        public MyViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textViewName);
            description = itemView.findViewById(R.id.textViewDescription);
        }
    }

}
