package com.example.seminar4;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ImagesActivity extends AppCompatActivity {
    List<String> linkuriImagini = new ArrayList<>();
    List<Bitmap> imagini = new ArrayList<>();
    List<ImaginiDomeniu> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_images);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        linkuriImagini.add("https://www.excelsior.edu/wp-content/uploads/2023/11/What_is_a_Machine_Operator_Blog.jpg");
        linkuriImagini.add("https://miro.medium.com/v2/resize:fit:1400/1*JB6LDc27AlTiWXK7UjPM3A@2x.jpeg");
        linkuriImagini.add("https://img.magnific.com/free-photo/worker-operating-industrial-machine-metal-workshop_342744-268.jpg?semt=ais_hybrid&w=740&q=80");
        linkuriImagini.add("https://d1ldvf68ux039x.cloudfront.net/thumbs/photos/2508/9236064/1000w_q95.jpg");
        linkuriImagini.add("https://marello.com/wp-content/uploads/2020/04/twitter-17.jpg");

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.myLooper());
        executor.execute(new Runnable() {
            @Override
            public void run() {
                for (String link:linkuriImagini){
                    HttpURLConnection con =null;
                    try{
                        URL url = new URL(link);
                        con = (HttpURLConnection) url.openConnection();
                        InputStream is = con.getInputStream();
                        imagini.add(BitmapFactory.decodeStream(is));
                    } catch (MalformedURLException e) {
                        throw new RuntimeException(e);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

        });
        handler.post(new Runnable() {
            @Override
            public void run() {
                lista = new ArrayList<>();
                lista.add(new ImaginiDomeniu("Smecher masina", imagini.get(0), "https://www.excelsior.edu/wp-content/uploads/2023/11/What_is_a_Machine_Operator_Blog.jpg"));
                lista.add(new ImaginiDomeniu("Ou yeah", imagini.get(1), "https://miro.medium.com/v2/resize:fit:1400/1*JB6LDc27AlTiWXK7UjPM3A@2x.jpeg"));
                lista.add(new ImaginiDomeniu("Oh nou", imagini.get(2), "https://img.magnific.com/free-photo/worker-operating-industrial-machine-metal-workshop_342744-268.jpg?semt=ais_hybrid&w=740&q=80"));
                lista.add(new ImaginiDomeniu("Digi Sport", imagini.get(3), "https://d1ldvf68ux039x.cloudfront.net/thumbs/photos/2508/9236064/1000w_q95.jpg"));
                lista.add(new ImaginiDomeniu("Nush ce aici", imagini.get(4), "https://marello.com/wp-content/uploads/2020/04/twitter-17.jpg"));
            }
        });

        ListView lv = findViewById(R.id.lv_img);
        ImageAdapter adapter = new ImageAdapter(lista ,getApplicationContext(),R.layout.custom_img_layout);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(getApplicationContext(),WebViewActivity.class);
                it.putExtra("link", lista.get(position).getLink());
                startActivity(it);
            }
        });
    }
}