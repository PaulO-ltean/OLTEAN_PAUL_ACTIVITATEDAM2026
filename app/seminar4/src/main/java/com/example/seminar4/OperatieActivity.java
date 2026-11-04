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
        Intent it=getIntent();
        if(it.hasExtra("lista operatii")){
            Operatie operatieDeModificat=it.getParcelableExtra("operatie");
            EditText etOperator = findViewById(R.id.et_nume_operator);
            RadioGroup rgTip = findViewById(R.id.rg_tip);
            RatingBar ratingDurata = findViewById(R.id.rating_durata);
            Switch swCompletata = findViewById(R.id.switch_completata);
            Spinner spinnerOperatie = findViewById(R.id.spinner_operatie);

            etOperator.setText(operatieDeModificat.getnOperator());
            spinnerOperatie.setSelection(operatieDeModificat.getTip());
        }
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


        btnSalvare.setOnClickListener(v -> {
            if (!cbConfirmare.isChecked()) {
                Toast.makeText(this, "Bifați confirmarea datelor (CheckBox)!", Toast.LENGTH_SHORT).show();
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