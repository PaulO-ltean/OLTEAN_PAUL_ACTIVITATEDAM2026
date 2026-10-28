package com.example.seminar4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView tvRezultat;

    private ArrayList<Operatie> listaOperatii = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvRezultat = findViewById(R.id.tv_rezultat);
        Button btnDeschideFormular = findViewById(R.id.btn_deschide_formular);

        btnDeschideFormular.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, OperatieActivity.class);
            startActivityForResult(intent, 1);
        });

        Button btnDeschideListView = findViewById(R.id.btn_deschidere_listView);

        btnDeschideListView.setOnClickListener(v->{
            Intent intent = new Intent(MainActivity.this, ActivitateLista.class);
            intent.putExtra("lista_operatii", listaOperatii);
            startActivity(intent);
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {
            Operatie opReturnata = (Operatie) data.getSerializableExtra("operatie_rezultat");

            if (opReturnata != null) {
                listaOperatii.add(opReturnata);
                tvRezultat.setText(opReturnata.toString());
                Toast.makeText(this, "Obiect creat de: " + opReturnata.getnOperator(), Toast.LENGTH_LONG).show();

            }
        }
    }
}