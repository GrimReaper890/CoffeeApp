package com.fyppractice.fyppractice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fyppractice.fyppractice.adminActivities.MainActivityAdmin;
import com.fyppractice.fyppractice.crActivities.MainActivityCr;
import com.fyppractice.fyppractice.loginPreferenceManager.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    EditText etUsername, etPassword;
    Button btnLogin;
    RadioButton rBtnAdmin, rBtnCr;
    TextView tvRegisterLink;
    String URL = "http://10.0.2.2:8080/fyppracticedb/check_login.php";
    String Username, Password;
    SessionManager session;
    //    0 =student(CR)     1 = "ADMIN"
    String TypeChecker = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);
        session = new SessionManager(getApplicationContext());
        rBtnAdmin = (RadioButton) findViewById(R.id.r_btn_admin);
        rBtnCr = (RadioButton) findViewById(R.id.r_btn_cr);
        tvRegisterLink = (TextView) findViewById(R.id.tvRegisterLink);
        tvRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });


        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                get the user entered values
                Username = etUsername.getText().toString();
                Password = etPassword.getText().toString();

                if (Username.isEmpty() || Password.isEmpty()) {
                    etUsername.setError("Please fill the complete form");
                    etPassword.setError("Please fill the complete form");

                } else if (rBtnCr.isChecked() || rBtnAdmin.isChecked()) {
//                    agr teacher wala radio button ha to radio krain
                    if (rBtnAdmin.isChecked()) {

                        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
//                            this will help us to read out the JSON object we get from php code that we wrote on server side
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    if (jsonObject.getString("success").equals("1")) {
                                        Log.e("LoginCliker==>", "Nope nothing u r good");
                                        startActivity(new Intent(LoginActivity.this, MainActivityAdmin.class));
//                                    set the session login by giving userName
                                        session.setLogin(true, Username,TypeChecker);
                                        finish();
                                    } else {
                                        Log.e("LoginCliker==>", "U are doomed 3");
                                        etUsername.setError("Please fill the complete form");
                                        etPassword.setError("Please fill the complete form");
                                    }


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                Toast.makeText(LoginActivity.this, response, Toast.LENGTH_SHORT).show();


                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }) {
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> params = new HashMap<>();
                                params.put("pName", Username);
                                params.put("typeId", TypeChecker);
                                params.put("ppassword", Password);
                                return params;

                            }
                        };
                        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                        requestQueue.add(stringRequest);

                    } else if (rBtnCr.isChecked()) {
                        TypeChecker = "1";

                        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
//                            this will help us to read out the JSON object we get from php code that we wrote on server side
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    if (jsonObject.getString("success").equals("1")) {
                                        Log.e("LoginCliker==>", "Nope nothing u r good");
                                        startActivity(new Intent(LoginActivity.this, MainActivityCr.class));
//                                    set the session login by giving userName and the login type
                                        session.setLogin(true, Username,TypeChecker);
                                        finish();
                                    } else {
                                        Log.e("LoginCliker==>", "U are doomed 3");
                                        etUsername.setError("Please fill the complete form");
                                        etPassword.setError("Please fill the complete form");
                                    }


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                Toast.makeText(LoginActivity.this, response, Toast.LENGTH_SHORT).show();


                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }) {
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> params = new HashMap<>();
                                params.put("pName", Username);
                                params.put("typeId", TypeChecker);
                                params.put("ppassword", Password);
                                return params;

                            }
                        };
                        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                        requestQueue.add(stringRequest);


                    }


                }else{
                    Toast.makeText(LoginActivity.this, "Please select any of type either ADMIN or CR", Toast.LENGTH_SHORT).show();
                }
            }


        });


    }
}
