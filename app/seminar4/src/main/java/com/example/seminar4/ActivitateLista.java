package com.example.seminar4;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ActivitateLista extends AppCompatActivity {

    public ArrayList<Operatie> lista;
    public ArrayAdapter<Operatie> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_activitate_lista);
        ListView listView=findViewById(R.id.listView_afisare);
        lista = (ArrayList<Operatie>) getIntent().getSerializableExtra("lista_operatii");
        if (lista == null) {
            lista = new ArrayList<>();
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lista);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Operatie opSelectata = lista.get(position);
            Toast.makeText(ActivitateLista.this, "Ai selectat:\n" + opSelectata.toString(), Toast.LENGTH_LONG).show();
        });
        listView.setOnItemLongClickListener((parent, view, position, id) -> {

            lista.remove(position);
            adapter.notifyDataSetChanged();
            Toast.makeText(ActivitateLista.this, "Obiect șters", Toast.LENGTH_SHORT).show();
            return true;
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}