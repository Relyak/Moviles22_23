package com.example.agendaentregar;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MiViewHolder> {
    private ArrayList<Persona> personas;
    private RecyclerViewClickListener listener;
    public recyclerAdapter(ArrayList<Persona>personas, RecyclerViewClickListener listener){
        this.listener=listener;
        this.personas=personas;
    }
    public class MiViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView tv1;
        private TextView tv2;
        public MiViewHolder(final View view){
            super(view);
            tv1=view.findViewById((R.id.tvnombre));
            tv2=view.findViewById(R.id.tvtelefono);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view,getAdapterPosition());
        }
    }

    @NonNull
    @Override
    //inflamos la vista y retornamos los datos
    public recyclerAdapter.MiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_agenda,parent,false);

        return new MiViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerAdapter.MiViewHolder holder, int position) {

        holder.tv1.setText(personas.get(position).getNombre());
        holder.tv2.setText(personas.get(position).getTel());



        /*String name= personas.get(position).getNombre();
        String tel= personas.get(position).getTel();
        holder.tv1.setText(name);
        holder.tv2.setText(tel);*/
    }


    @Override
    public int getItemCount() {
        return personas.size();
    }
    public interface RecyclerViewClickListener{
        void onClick(View v,int position);
    }
}
