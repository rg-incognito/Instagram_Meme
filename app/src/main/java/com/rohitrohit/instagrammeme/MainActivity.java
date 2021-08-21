package com.rohitrohit.instagrammeme;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    Button next_btn;
    String url = "https://meme-api.herokuapp.com/gimme";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView =findViewById(R.id.memes_image);
        next_btn =findViewById(R.id.next_meme_btn);
        getMeme();
        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMeme();

            }
        });

    }

    private void getMeme() {



        RequestQueue queue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        String imageUrl = response.getString("url");
                        Glide.with(MainActivity.this).load(imageUrl).into(imageView);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }, error -> {
            Toast.makeText(MainActivity.this, " Error Is Going On", Toast.LENGTH_SHORT).show();



        });
        queue.add(jsonObjectRequest);




    }
}