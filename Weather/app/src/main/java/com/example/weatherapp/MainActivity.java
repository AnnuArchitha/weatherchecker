package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    EditText e1;
    Button b1;
    TextView t1;
    ImageView i1;
    String base = "https://api.openweathermap.org/data/2.5/weather?q=";
    String api = "&appid=402745c76783d47d6006db202a0fef41";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        e1 = findViewById(R.id.city);
        b1 = findViewById(R.id.get);
i1=findViewById(R.id.image);
        t1 = findViewById(R.id.result);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = base + e1.getText().toString() + api;
                // Log.i("URL","URL"+url);
                final JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject jsonObject) {
                                Log.i("JSON", "JSON" + jsonObject);
                                try {
                                    String info = jsonObject.getString("weather");
                                    Log.i("INFO", "INFO" + info);
                                    JSONArray ar = new JSONArray(info);
                                    for (int i = 0; i < ar.length(); i++) {
                                        JSONObject parobj = ar.getJSONObject(i);
                                        String myweather = parobj.getString("main");

                                        t1.setText(myweather);
                                        if(myweather.equals("Clear")){

                                      i1.setImageResource(R.drawable.clear);
                                        }
                                        if(myweather.equals("Rain")){
                                            i1.setImageResource(R.drawable.rainy);
                                        }
                                        if(myweather.equals("Drizzle")){
                                            i1.setImageResource(R.drawable.drizzle);
                                        }
                                        if(myweather.equals("Clouds")){
                                            i1.setImageResource(R.drawable.cloud);
                                        }
                                        if(myweather.equals("Fog")){
                                            i1.setImageResource(R.drawable.fog);
                                        }
                                        if(myweather.equals("Mist")){
                                            i1.setImageResource(R.drawable.mist);
                                        }
                                        if(myweather.equals("Sun")){
                                            i1.setImageResource(R.drawable.suny);
                                        }
                                        if(myweather.equals("Snow")){
                                            i1.setImageResource(R.drawable.snow);
                                        }
                                        Log.i("ID", "ID:" + parobj.getString("id"));
                                        Log.i("MAIN", "MAIN:" + parobj.getString("main"));
                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    String coor = jsonObject.getString("coord");
                                    Log.i("COOR", "COOR:" + coor);
                                    JSONObject co = new JSONObject("coor");
                                    String lon = co.getString("lon");
                                    String lat = co.getString("lat");
                                    Log.i("LON", "LON:" + lon);
                                    Log.i("LAT", "LAT" + lat);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("Error", "Some thing went wrong" +error);

                    }
                }
                );
                Mysingleton.getInstance(MainActivity.this).addToRequestQue(jsonObjectRequest);
            }
        });
    }
}



