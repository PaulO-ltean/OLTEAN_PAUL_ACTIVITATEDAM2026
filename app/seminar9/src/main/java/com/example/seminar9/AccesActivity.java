package com.example.seminar9;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Executor;

public class AccesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_acces);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button btn_acces = findViewById(R.id.btn_send);
        EditText et = findViewById(R.id.et_oras);

        btn_acces.setOnClickListener(v->{
            String oras = et.getText().toString().trim();
            Executor executor = new Executor() {
                @Override
                public void execute(Runnable command) {

                    String link = "https://dataservice.accuweather.com/locations/v1/cities/search?q=" + oras + "&apikey=";
                    try{
                        URL url = new URL(link);
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        StringBuilder builder = new StringBuilder("");
                        while(reader.readLine()!=null)
                        {   String linie = reader.readLine();

                            builder.append(linie);

                        }
                        reader.close();
                        conn.disconnect();
                        String rezultat = builder.toString();
                        JSONArray vector = new JSONArray(builder.toString());
                        JSONObject obiect = vector.getJSONObject(0);
                        String cheieOras = obiect.getString("key");
                        Toast.makeText(AccesActivity.this, cheieOras, Toast.LENGTH_SHORT).show();


                        String linkDay = "https://dataservice.accuweather.com/forecasts/v1/daily/1day/:" + cheieOras + "/?apikey=";
                        URL urlD = new URL(linkDay);
                        HttpURLConnection connD = (HttpURLConnection) urlD.openConnection();
                        BufferedReader readerD = new BufferedReader(new InputStreamReader(connD.getInputStream()));
                        StringBuilder builderD = new StringBuilder("");
                        while(readerD.readLine()!=null)
                        {   String linieD = reader.readLine();

                            builderD.append(linieD);

                        }
                        readerD.close();
                        connD.disconnect();

                        JSONArray zile = new JSONObject(builderD.toString()).getJSONArray("DailyForecast");
                        for(int i = 0; i<zile.length();i++)
                        {

                        }
                        //3579

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                }
            };

        });
    }
}