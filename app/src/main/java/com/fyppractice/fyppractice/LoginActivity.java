package com.fyppractice.fyppractice;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
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

    String URL = "http://10.0.2.2:8080/fyppracticedb/check_cr_login.php";
    String cr_type, cr_semster, cr_section, cr_name, cr_session, cr_password;
    SessionManager session;
    //    0 =student(CR)     1 = "ADMIN"
    String TypeChecker = "0";
    Spinner typeSpinner, crTypeSpinner, sectionTypeSpinner, semesterTypeSpinner, SessionArraySpinner;
    ArrayAdapter sectionSpinnerAdapter, smesterSpinnerAdapter;
    String[] csSections = {"REG", "SS1", "SS2"};
    String[] iTSections = {"REG", "SS1", "SS2", "SS3"};
    String[] sESections = {"REG", "SS"};
    String[] MSCsSections = {"REG", "SS"};
    String[] MITSections = {"REG", "SS"};
    String[] BSSMESTERS = {"SEM-1", "SEM-2", "SEM-3", "SEM-4", "SEM-5", "SEM-6", "SEM-7", "SEM-8"};
    String[] MSSMESTERS = {"SEM-1", "SEM-2", "SEM-3", "SEM-4"};
    String Title;  //for managing user Type the more DAMN PROPER WAY

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etUsername = (EditText) findViewById(R.id.etUsername);
        etPassword = (EditText) findViewById(R.id.etPassword);

        typeSpinner = (Spinner) findViewById(R.id.type_spinner);
        crTypeSpinner = (Spinner) findViewById(R.id.cr_type_spinner);
        sectionTypeSpinner = (Spinner) findViewById(R.id.section_type_spinner);
        semesterTypeSpinner = (Spinner) findViewById(R.id.semester_type_spinner);
        SessionArraySpinner = (Spinner) findViewById(R.id.session_type_spinner);


        session = new SessionManager(getApplicationContext());


        Title = typeSpinner.getSelectedItem().toString();      // by default here will be the admin stored on roll :-)
//        if user click on the spinner changes the spinnner value then there their will be no NULL POINTER EXCEPTIOn
//

        typeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView incomingTitle = (TextView) view;
                Title = incomingTitle.getText().toString();
                if (Title.equals("CR")) {
                    crTypeSpinner.setVisibility(View.VISIBLE);
                } else {
                    return;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        crTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView incomingTitle = (TextView) view;
                String Title = incomingTitle.getText().toString();
                if (Title.equals("BSCS")) {
                    sectionSpinnerAdapter = new ArrayAdapter(LoginActivity.this, android.R.layout.simple_spinner_item, csSections);
                    smesterSpinnerAdapter = new ArrayAdapter(LoginActivity.this, android.R.layout.simple_spinner_item, BSSMESTERS);
                } else if (Title.equals("BSIT")) {
                    sectionSpinnerAdapter = new ArrayAdapter(LoginActivity.this, android.R.layout.simple_spinner_item, iTSections);
                    smesterSpinnerAdapter = new ArrayAdapter(LoginActivity.this, android.R.layout.simple_spinner_item, BSSMESTERS);
                } else if (Title.equals("BSSE")) {
                    sectionSpinnerAdapter = new ArrayAdapter(LoginActivity.this, android.R.layout.simple_spinner_item, sESections);
                    smesterSpinnerAdapter = new ArrayAdapter(LoginActivity.this, android.R.layout.simple_spinner_item, BSSMESTERS);
                } else if (Title.equals("MIT")) {
                    sectionSpinnerAdapter = new ArrayAdapter(LoginActivity.this, android.R.layout.simple_spinner_item, MITSections);
                    smesterSpinnerAdapter = new ArrayAdapter(LoginActivity.this, android.R.layout.simple_spinner_item, MSSMESTERS);
                } else if (Title.equals("MSCS")) {
                    sectionSpinnerAdapter = new ArrayAdapter(LoginActivity.this, android.R.layout.simple_spinner_item, MSCsSections);
                    smesterSpinnerAdapter = new ArrayAdapter(LoginActivity.this, android.R.layout.simple_spinner_item, MSSMESTERS);
                }

                sectionTypeSpinner.setAdapter(sectionSpinnerAdapter);
                sectionTypeSpinner.setVisibility(View.VISIBLE);
                semesterTypeSpinner.setAdapter(smesterSpinnerAdapter);
                semesterTypeSpinner.setVisibility(View.VISIBLE);

            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                condition to check eitehr user is connected with internet or not
//                then on clcik getting all the edit text values and  save them in the string

                if (isConnected(LoginActivity.this)) {

                    cr_name = etUsername.getText().toString();
                    cr_password = etPassword.getText().toString();
                    cr_type = crTypeSpinner.getSelectedItem().toString();
                    cr_semster = semesterTypeSpinner.getSelectedItem().toString();
                    cr_session= SessionArraySpinner.getSelectedItem().toString();
                    cr_section= sectionTypeSpinner.getSelectedItem().toString();

                    if (cr_name.isEmpty() || cr_password.isEmpty()) {
                        etUsername.setError("Please fill all the fields");
                        etPassword.setError("Please fill all the fields");

                    } else {
//                Toast.makeText(LoginActivity.this, "1"+cr_type+"2"+cr_name+"3"+cr_semster+"4"+cr_session+"5"+cr_password, Toast.LENGTH_SHORT).show();

                        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Toast.makeText(LoginActivity.this, response, Toast.LENGTH_SHORT).show();
                                if (response.equals("true")) {
                                    TypeChecker = "1";
                                    Intent i = new Intent(LoginActivity.this, MainActivityCr.class);
                                    i.putExtra("cr_name",cr_name);
                                    i.putExtra("cr_type",cr_type);
                                    i.putExtra("cr_section",cr_section);
                                    startActivity(i);
//                                    set the session login by giving userName
                                    session.setLogin(true, cr_name, TypeChecker);
                                    finish();
                                } else {
                                    Toast.makeText(LoginActivity.this, "Please Recheck the email or signUp", Toast.LENGTH_SHORT).show();
                                }
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
                                params.put("cr_semster", cr_semster);
                                params.put("cr_type", cr_type);
                                params.put("cr_name", cr_name);
                                params.put("cr_session", cr_session);
                                params.put("cr_password", cr_password);
                                params.put("cr_section",cr_section);

                                return params;

                            }


                        };
                        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                        requestQueue.add(stringRequest);
                    }

                } else {
                    Toast.makeText(LoginActivity.this, "Please connect to internet and retry", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


//    ---------------------------------------------------THE METHOD TO CHECK INTER NET COMING STRAIGH AWAYS--------------------//>

    //checking that Either internet is connected or not
    public boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if ((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting()))
                return true;
            else return false;
        } else return false;
    }


}