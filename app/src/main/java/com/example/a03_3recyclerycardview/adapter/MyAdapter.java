package com.example.a03_3recyclerycardview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a03_3recyclerycardview.model.Pelicula;
import com.example.a03_3recyclerycardview.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//3. Extender la clase Adapter de RecyclerView
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    //4. Crear los atributos
    private List<Pelicula> lstPelicula;
    private int layout;
    private OnItemClickListener itemClickListener;
    private Context context;

    //5. Crear el constructor
    public MyAdapter (Context context, List<Pelicula> lstPelicula, int layout,
                      OnItemClickListener itemClickListener) {
        this.context = context;
        this.lstPelicula = lstPelicula;
        this.layout = layout;
        this.itemClickListener = itemClickListener;
    }

    //6. Completar los métodos
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Inflando la vista con el contexto del padre y el layout
        View v = LayoutInflater.from(context).inflate(this.layout, parent, false);
        //Inicializando el ViewHolder, pasándole la vista inflada
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Consumimos nuestro método bind del ViewHolder
        holder.bind(lstPelicula.get(position), itemClickListener);
    }

    @Override
    public int getItemCount() {
        return lstPelicula.size();
    }

    //1. Crear el ViewHolder y sus métodos
    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewNombre;
        public ImageView imageViewPoster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textViewNombre = itemView.findViewById(R.id.textViewPelicula);
            this.imageViewPoster = itemView.findViewById(R.id.imageViewPoster);
        }

        //Método para llenar los datos a nuestra vista. Parámetros:
        //1. El objeto Modelo
        //2. El listerner creado acá mismo: OnItemClickListener
        public void bind(final Pelicula pelicula, final OnItemClickListener listener) {
            //Llenado de la vista
            this.textViewNombre.setText(pelicula.getNombre());
            //Trabajando con Picasso
            Picasso.with(context).load(pelicula.getPoster()).fit().into(imageViewPoster);
//            this.imageViewPoster.setImageResource(pelicula.getPoster());

            //Implementando el click
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Llamamos al método definido en nuestra interface. Parámetros definidos:
                    //1. El objeto Modelo / o uno de sus atributos
                    //2. La posición
                    listener.onItemClick(pelicula, getAdapterPosition());
                }
            });
        }
    }

    //2. Crear la interface OnItemClickListener
    public interface OnItemClickListener {
        void onItemClick (Pelicula pelicula, int posicion);
    }
}
