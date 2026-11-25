package com.example.seminar9;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // https://dataservice.accuweather.com/locations/v1/cities/search?q=<string>' \
        //  --header 'Authorization: <api-key>
//        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder()
//                .url("https://dataservice.accuweather.com/locations/v1/cities/search")
//                .get()
//                .addHeader("Authorization", "<api-key>")
//                .build();
//        Response response = client.newCall(request).execute();

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Button btn_Deschidere = findViewById(R.id.btn_deschidere);
        btn_Deschidere.setOnClickListener(v -> {
            Intent it = new Intent(MainActivity.this, AccesActivity.class);
            startActivityForResult(it ,1 );
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if( resultCode==1 && data!=null)
        {

        }
    }
}