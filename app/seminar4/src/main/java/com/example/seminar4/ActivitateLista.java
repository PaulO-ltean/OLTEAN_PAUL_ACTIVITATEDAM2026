package com.example.seminar4;

import android.content.Intent;
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
    //public ArrayAdapter<Operatie> adapter;
    private int  idModificat=0;
    private OperatieAdapter adapter=null;

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
        adapter = new OperatieAdapter(lista, getApplicationContext(), R.layout.custom_layout_lv);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intentModifica= new Intent(getApplicationContext(), OperatieActivity.class);
            intentModifica.putExtra("lista operatii", lista.get(position));
            idModificat=position;
            startActivityForResult(intentModifica,209);
            Operatie opSelectata = lista.get(position);
            Toast.makeText(getApplicationContext(), lista.get(position).toString(), Toast.LENGTH_SHORT).show();
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