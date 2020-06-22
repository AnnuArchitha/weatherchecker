package com.example.weatherapp;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class Mysingleton {
    private static Mysingleton minstance;
    private RequestQueue requestQueue;
    public static Context context;

    public Mysingleton(Context context) {
        this.context=  context;
        requestQueue=getRequestQueue();

        }

    public RequestQueue getRequestQueue() {
       if(requestQueue==null) {
           requestQueue= Volley.newRequestQueue(context.getApplicationContext());
       }
       return requestQueue;
    }
    public static synchronized Mysingleton getInstance(Context context){
        if( minstance==null){
            minstance=new Mysingleton(context);
        }
        return  minstance;
    }
    public void addToRequestQue(Request request){
      requestQueue.add(request);
    }
}

