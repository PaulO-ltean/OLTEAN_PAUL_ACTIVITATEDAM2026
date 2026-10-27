package com.example.seminar4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvRezultat;

    private final ActivityResultLauncher<Intent> launcherActivitati = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    // Extragerea obiectului transmis prin Bundle/Intent
                    Operatie opReturnata = (Operatie) result.getData().getSerializableExtra("operatie_rezultat");

                    if (opReturnata != null) {
                        tvRezultat.setText(opReturnata.toString());
                        Toast.makeText(this, "Obiect creat de: " + opReturnata.getnOperator(), Toast.LENGTH_LONG).show();
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvRezultat = findViewById(R.id.tv_rezultat);
        Button btnDeschideFormular = findViewById(R.id.btn_deschide_formular);

        btnDeschideFormular.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, OperatieActivity.class);
            launcherActivitati.launch(intent);
        });
    }
}