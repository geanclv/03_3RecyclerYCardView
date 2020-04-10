package com.example.a03_3recyclerycardview.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.a03_3recyclerycardview.adapter.MyAdapter;
import com.example.a03_3recyclerycardview.model.Pelicula;
import com.example.a03_3recyclerycardview.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Pelicula> lstPelicula;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private int contador = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //4. Inicializando controles básicos
        initControls();
        initLstPelicula();

        //5. Inicializando el layoutManager
        layoutManager = new LinearLayoutManager(this);
//        layoutManager = new GridLayoutManager(this, 2);

        //6. Inicializando el adaptador
        adapter = new MyAdapter(MainActivity.this, lstPelicula, R.layout.recycler_view_item,
                new MyAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(Pelicula pelicula, int posicion) {
                        eliminarPelicula(posicion);
                    }
                });

        //7. Atributos del RecyclerView dependiendo de la funcionalidad
        recyclerView.setHasFixedSize(true); //tamaño no variable
        recyclerView.setItemAnimator(new DefaultItemAnimator()); //animaciones básicas

        //8. Asignando el layoutManager y el adaptador
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    //3. Menu de opciones
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_nombre:
                this.agregarPelicula(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //2. Operaciones básicas
    private void agregarPelicula(int posicion) {
        lstPelicula.add(posicion,
                new Pelicula("Pelicula " + (++contador), R.drawable.spiderman));
        adapter.notifyItemInserted(posicion);
        layoutManager.scrollToPosition(posicion);
    }

    private void eliminarPelicula(int posicion) {
        lstPelicula.remove(posicion);
        adapter.notifyItemRemoved(posicion);
    }

    //1. Inicializaciones básicas
    private void initLstPelicula() {
        lstPelicula = new ArrayList<Pelicula>() {{
            add(new Pelicula("Pokemon", R.drawable.pokemon));
            add(new Pelicula("Casa de papel", R.drawable.casapapel));
            add(new Pelicula("John Wick", R.drawable.jon));
            add(new Pelicula("Spiderman", R.drawable.spiderman));
        }};
    }

    private void initControls() {
        recyclerView = findViewById(R.id.recyclerViewMain);
    }
}
