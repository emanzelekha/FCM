package com.example.lap_shop.fcm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.lap_shop.fcm.Fcm.FCMTokenRefreshListenerService;
import com.example.lap_shop.fcm.Fcm.MySingleton;
import com.example.lap_shop.fcm.View.ResizableImageView;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    Button send;
    EditText message;
   ResizableImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(this, FCMTokenRefreshListenerService.class));
        send = (Button) findViewById(R.id.send);
        message = (EditText) findViewById(R.id.message);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendMessage(message.getText() + "");
            }
        });
        imageView = (ResizableImageView) findViewById(R.id.image);
        Picasso.with(this).load("http://77.93.198.186//u//2016//05//03//1462284245-9.jpg")
                .fit().centerInside()
                .placeholder(R.drawable.placeholder_loading)
                .into(imageView);

    }


    private void SendMessage(final String message) {
        String Url = "http://emantest.16mb.com/FCM.php";
        StringRequest requestout = new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("Registration Service", "Error :Send Token Failed");

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("message", message);
                return params;
            }
        };
        MySingleton.getInstance(this).addToRequestQueue(requestout);
    }

}
