package com.fyppractice.fyppractice;

import android.content.Intent;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    EditText etName, etPhone, etUsernameR, etPasswordR;
    Button btnRegister;
    RadioButton rBtnAdmin, rBtnCr;
    String URL = "http://10.0.2.2/fyppracticedb/registerme.php";
    String name, phone, username, password;

    //    0 =student(CR)     1 = "ADMIN"
    String TypeChecker = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = (EditText) findViewById(R.id.etName);
        etPhone = (EditText) findViewById(R.id.etPhone);
        etUsernameR = (EditText) findViewById(R.id.etUsernameR);
        etPasswordR = (EditText) findViewById(R.id.etPasswordR);


        rBtnAdmin = (RadioButton) findViewById(R.id.regBased_btn_Admin);
        rBtnCr = (RadioButton) findViewById(R.id.regBased_btn_cr);


        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = etName.getText().toString();
                phone = etPhone.getText().toString();
                username = etUsernameR.getText().toString();
                password = etPasswordR.getText().toString();
                if (name.isEmpty() || phone.isEmpty() || username.isEmpty() || password.isEmpty()) {
                    etName.setError("there is a field not propoerly filled");
                    etPhone.setError("there is a field not propoerly filled");
                    etUsernameR.setError("there is a field not propoerly filled");
                    etPasswordR.setError("there is a field not propoerly filled");
                } else if (rBtnCr.isChecked() || rBtnAdmin.isChecked()) {
                    if (rBtnAdmin.isChecked()) {
                        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                Toast.makeText(RegisterActivity.this, response, Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(RegisterActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }){
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String,String> params=new HashMap<>();
                                params.put("name",name);
                                params.put("phone",phone);
                                params.put("username",username);
                                params.put("password",password);
                                params.put("typeId", TypeChecker);

                                return params;

                            }
                        };
                        RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
                        requestQueue.add(stringRequest);


                    } else if (rBtnCr.isChecked()) {

                        TypeChecker = "1";
                        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                Toast.makeText(RegisterActivity.this, response, Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));

                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(RegisterActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }){
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String,String> params=new HashMap<>();
                                params.put("name",name);
                                params.put("phone",phone);
                                params.put("username",username);
                                params.put("password",password);
                                params.put("typeId", TypeChecker);

                                return params;

                            }
                        };
                        RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
                        requestQueue.add(stringRequest);





                    }
                }else {
                    Toast.makeText(RegisterActivity.this, "Please select any of the type either admin or CR", Toast.LENGTH_SHORT).show();
                }

            }

        });
    }
}
