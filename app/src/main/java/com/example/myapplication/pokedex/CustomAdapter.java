package com.example.myapplication.pokedex;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class CustomAdapter
        extends RecyclerView.Adapter<CustomAdapter.ViewHolder>
         implements  View.OnClickListener{
    Context ctx;
    ArrayList<Itm_pkm> itm_pkm;
    ArrayList<Itm_pkm> itm_pkmOriginal;

    //Creo la variable de view para seleecionar el item
    private View.OnClickListener listener;

    public CustomAdapter(ArrayList<Itm_pkm> pokemos,Context ctx ) {
        this.ctx = ctx;
        this.itm_pkm = pokemos;
        itm_pkmOriginal = new ArrayList<>();
        itm_pkmOriginal.addAll(itm_pkm);
    }
    //Se llama cada vex que un item nuevo de la lista se va a ver,
    // y se va a reciclar uno anterior que ya no se va a ver
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Obtenemos el layout que hemos diseñado como tipo "view"
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_itm_pkm, null);

        view.setOnClickListener(this);

        ViewHolder holder = new ViewHolder(view);
        return holder;
    }
    // Este metodo se llama para poder finalmente mostrar los datos que mantiene el holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String name = itm_pkm.get(position).getNombre();
        holder.name.setText(name);
        //new DownloadImageTask(holder.image).execute(itm_pkm.get(position).getImgPokemon());
        Picasso.get().load(itm_pkm.get(position).getImgPokemon()).into(holder.image);

    }

    public void filtrado(String txtBuscar){
        int longitud = txtBuscar.length();
        if (longitud == 0){
            itm_pkm.clear();
            itm_pkm.addAll(itm_pkmOriginal);
        }else{
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                List<Itm_pkm> collecion = itm_pkm.stream().filter(i -> i.getNombre().toLowerCase().
                        contains(txtBuscar.toLowerCase())).collect(Collectors.toList());
                itm_pkm.clear();
                itm_pkm.addAll(collecion);
            } else {
                for (Itm_pkm pokemon: itm_pkmOriginal){
                    if (pokemon.getNombre().toLowerCase().contains(txtBuscar.toLowerCase())){
                        itm_pkm.add(pokemon);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return itm_pkm.size();
    }

    public void  setOnClicklListener(View.OnClickListener listener){
        this.listener = listener;
    }


    @Override
    public void onClick(View view) {
        if (listener != null){
            listener.onClick(view);
        }
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