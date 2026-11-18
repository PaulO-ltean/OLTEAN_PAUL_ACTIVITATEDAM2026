package com.example.seminar4;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class OperatieActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operatie);

        EditText etOperator = findViewById(R.id.et_nume_operator);
        RadioGroup rgTip = findViewById(R.id.rg_tip);
        RadioButton rbIngineresc = findViewById(R.id.rb_ingineresc);
        RadioButton rbDigital = findViewById(R.id.rb_digital);
        RatingBar ratingDurata = findViewById(R.id.rating_durata);
        Switch swCompletata = findViewById(R.id.switch_completata);
        Spinner spinnerOperatie = findViewById(R.id.spinner_operatie);
        Button btnSalvare = findViewById(R.id.btn_salvare);
        CheckBox cbConfirmare = findViewById(R.id.cb_confirmare);


        String[] optiuniOperatii = {"Mentenanță", "Asamblare", "Reparație", "Testare"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, optiuniOperatii);
        spinnerOperatie.setAdapter(adapter);


        Intent it = getIntent();
        if(it.hasExtra("operatie")) {
            Operatie operatieDeModificat = it.getParcelableExtra("operatie");

            if (operatieDeModificat != null) {
                etOperator.setText(operatieDeModificat.getnOperator());

                ratingDurata.setRating(operatieDeModificat.getDurataOre());
                swCompletata.setChecked(operatieDeModificat.getCompletata());

                if ("Ingineresc".equals(operatieDeModificat.getTip())) {
                    rbIngineresc.setChecked(true);
                } else if ("Digital".equals(operatieDeModificat.getTip())) {
                    rbDigital.setChecked(true);
                }


                for (int i = 0; i < optiuniOperatii.length; i++) {
                    if (optiuniOperatii[i].equals(operatieDeModificat.getnOpeartie())) {
                        spinnerOperatie.setSelection(i);
                        break;
                    }
                }


                btnSalvare.setText("SALVEAZA MODIFICARILE");
            }
        }


        btnSalvare.setOnClickListener(v -> {
            if (!cbConfirmare.isChecked()) {
                Toast.makeText(this, "Bifeaza confirmarea datelor (CheckBox)!", Toast.LENGTH_SHORT).show();
                return;
            }

            String nOperator = etOperator.getText().toString();
            String nOpeartie = spinnerOperatie.getSelectedItem().toString();
            Float durataOre = ratingDurata.getRating();
            Boolean completata = swCompletata.isChecked();

            String tip = "Necunoscut";
            int checkedId = rgTip.getCheckedRadioButtonId();
            if (checkedId != -1) {
                RadioButton rb = findViewById(checkedId);
                tip = rb.getText().toString();
            }

            Operatie op = new Operatie(tip, nOpeartie, nOperator, durataOre, completata);

            Intent intent = new Intent();
            intent.putExtra("operatie_rezultat", op);
            setResult(RESULT_OK, intent);
            finish();
        });
    }
}