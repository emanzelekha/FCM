package com.example.lap_shop.fcm.Fcm;


import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LAP_SHOP on 30/01/2017.
 */

public class FCMTokenRefreshListenerService extends FirebaseInstanceIdService {
    @Override
    public void onTokenRefresh() {
        String token = FirebaseInstanceId.getInstance().getToken();
        System.out.println(token+"efgyef");
        SendTokenToServer(token);
    }
    private void SendTokenToServer(final String token) {
        String Url = "http://emantest.16mb.com/addnewtoken.php";
        StringRequest requestout=new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.equals("1")){

                    Log.e("Registration Service", "Response : Send Token Success");

                } else {

                    Log.e("Registration Service", "Response : Send Token Failed");


                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.e("Registration Service", "Error :Send Token Failed");

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("token", token);
                return params;
            }
        };
        MySingleton.getInstance(this).addToRequestQueue(requestout);
    }

}
