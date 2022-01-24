package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter  extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    Context ctx;
    ArrayList<Itm_pkm> itm_pkm;

    public CustomAdapter(ArrayList<Itm_pkm> pokemos,Context ctx ) {
        this.ctx = ctx;
        this.itm_pkm = pokemos;
    }
    //Se llama cada vex que un item nuevo de la lista se va a ver,
    // y se va a reciclar uno anterior que ya no se va a ver
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Obtenemos el layout que hemos diseñado como tipo "view"
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_itm_pkm, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }
    // Este metodo se llama para poder finalmente mostrar los datos que mantiene el holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String name = itm_pkm.get(position).getNombre();
        holder.name.setText(name);
        new DownloadImageTask(holder.image).execute(itm_pkm.get(position).getImgPokemon());
    }

    @Override
    public int getItemCount() {
        return itm_pkm.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private ImageView image;
        public ViewHolder(View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.namePokemon);
            image = (ImageView) v.findViewById(R.id.imagePokemon);
        }
    }
}