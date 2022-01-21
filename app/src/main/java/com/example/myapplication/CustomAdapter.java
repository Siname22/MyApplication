package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapter  extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    Context ctx;
    ArrayList<itm_pkm> itm_pkm;

    public CustomAdapter(Context ctx, ArrayList<itm_pkm> tareas) {
        this.ctx = ctx;
        this.itm_pkm = tareas;
    }
    //Se llama cada vex que un item nuevo de la lista se va a ver, y se va a reciclar uno anteiro que ya
//no se va a ver
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Obtenemos el layout que hemos diseñado como tipo "view"
        View view = LayoutInflater.from(ctx).inflate(R.layout.activity_itm_pkm, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }
    // Este metodo se llama para poder finalmente mostrar los datos que mantiene el holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TextView textView = holder.itemView.findViewById(R.id.namePokemon);
        ImageView imageView =holder.itemView.findViewById(R.id.imagePokemon);
        textView.setText(itm_pkm.get(position).getNombre());
        Picasso.get().load(Pokedex.urls.get(position)).into(imageView);


    }

    @Override
    public int getItemCount() {
        return itm_pkm.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
        }
    }
}