package com.example.a03_3recyclerycardview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> lstNombre;

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
        initLstNombre();

        //5. Inicializando el layoutManager
        layoutManager = new GridLayoutManager(this, 2);

        //6. Inicializando el adaptador
        adapter = new MyAdapter(lstNombre, R.layout.recycler_view_item,
                new MyAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(String nombre, int posicion) {
                        eliminarNombre(posicion);
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
                this.agregarNombre(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //2. Operaciones básicas
    private void agregarNombre(int posicion) {
        lstNombre.add(posicion, "Nombre " + (++contador));
        adapter.notifyItemInserted(posicion);
        layoutManager.scrollToPosition(posicion);
    }

    private void eliminarNombre(int posicion) {
        lstNombre.remove(posicion);
        adapter.notifyItemRemoved(posicion);
    }

    //1. Inicializaciones básicas
    private void initLstNombre() {
        lstNombre = new ArrayList<String>() {{
            add("Gean");
            add("Carlo");
            add("Pepe");
            add("Jose");
            add("Luis");
        }};
    }

    private void initControls() {
        recyclerView = findViewById(R.id.recyclerViewMain);
    }
}
