package com.example.parsingjson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private RequestQueue mQueue;
    public RadioButton radioButton, radioButton1;
    public RadioButton radioButton3;
    public CheckedTextView checkedTextView;
    public CheckedTextView checkedTextView1;
    public CheckedTextView checkedTextView2;
    public TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQueue = Volley.newRequestQueue(this);
        jsonParse();


    }

    public void jsonParse() {
        String url = "http://www.mocky.io/v2/582695f5100000560464ca40 ";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < 6; i++) {
                        JSONObject employee = response.getJSONObject(i);
                        int id = employee.getInt("id");
                        String Descrip = employee.getString("description");
                        String Status = employee.getString("status");
                        Date date = (Date) employee.get("scheduledDate");
                        SimpleDateFormat df = new SimpleDateFormat("MM");
                        SimpleDateFormat df1 = new SimpleDateFormat("dd");
                        String month = df.format(date);
                        String day = df1.format(date);
                        if (day.equals("12")) {
                            textView = findViewById(R.id.textView17);
                            textView.setText(day + " " + month);
                            if (Status.equals("PENDING")) {
                                if (id == 1) {
                                    radioButton = findViewById(R.id.radioButton4);
                                    radioButton.setText(Descrip);
                                }
                                if (id == 4) {
                                    radioButton1 = findViewById(R.id.radioButton5);
                                    radioButton1.setText(Descrip);
                                }
                            }
                            if (Status.equals("COMPLETED")) {
                                if (id == 2) {
                                    checkedTextView = findViewById(R.id.checkedtoday1);
                                    checkedTextView.setText(Descrip);
                                    checkedTextView.setChecked(true);

                                }
                                if (id == 3) {
                                    checkedTextView1 = findViewById(R.id.checkedtoday2);
                                    checkedTextView1.setText(Descrip);
                                    checkedTextView1.setChecked(true);
                                }
                            }

                        } else {
                            TextView textView1;
                            textView1 = findViewById(R.id.textView13);
                            textView1.setText(day);

                            if (Status.equals("PENDING")) {
                                if (id == 5) {
                                    radioButton3 = findViewById(R.id.radioButton9);
                                    radioButton3.setText(Descrip);

                                }
                            }
                            if (Status.equals("COMPLETED")) {
                                if (id == 6) {
                                    checkedTextView2 = findViewById(R.id.checkedcompleted1);
                                    checkedTextView2.setText(Descrip);
                                    checkedTextView2.setChecked(true);
                                }
                            }

                        }


                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {

            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);

    }
























}

