package com.fyppractice.fyppractice.crActivities;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fyppractice.fyppractice.LoginActivity;
import com.fyppractice.fyppractice.R;
import com.fyppractice.fyppractice.loginPreferenceManager.SessionManager;
import com.fyppractice.fyppractice.recyclerCuAdapters.crClassAdapter;
import com.fyppractice.fyppractice.rvData.crClassActivityData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivityCr extends AppCompatActivity implements crClassAdapter.StudentItemClickCallback {
    SessionManager session;
    String URL = "http://10.0.2.2:8080/fyppracticedb/get_cr_required_data.php";
    //    String URL = "http://10.0.2.2/fyppracticedb/check_cr_login.php";
    TextView txtVuWelcome;
    RecyclerView recyclerViewStdnts;
    crClassAdapter crClassAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cr);
        session = new SessionManager(getApplicationContext());
        txtVuWelcome = (TextView) findViewById(R.id.welcome_msg_txt_vu);

//        the actvity that intents out this activity sends CR NAME as a STRING EXTRA

//        now a vision of extacy ! is simple .....  get another extra STRING EXTRA of the class that CR belongs to ... it will be like simple :P
        txtVuWelcome.setText(getIntent().getStringExtra("cr_name"));
        txtVuWelcome.append("\t" + getIntent().getStringExtra("cr_type") + "\t" + getIntent().getStringExtra("cr_section"));


        recyclerViewStdnts = (RecyclerView) findViewById(R.id.stdnt_recyclerVu);
        //        String request dfor the application of DEMOn
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                Toast.makeText(MainActivityCr.this, response, Toast.LENGTH_SHORT).show();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("result");
                    int length = jsonArray.length();
                    crClassActivityData crClassData = new crClassActivityData(length);
                    for (int i = 0; i < length; i++) {
                        JSONObject QueryData = jsonArray.getJSONObject(i);
                        crClassActivityData.stdntName[i] = QueryData.getString("name");
                        crClassActivityData.stdntContact[i] = QueryData.getString("contact");
                        crClassActivityData.stdntRollNumber[i] = QueryData.getString("rolnumber");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivityCr.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                Log.e("SECTION HAHWALA", getIntent().getStringExtra("cr_section"));
                params.put("cr_section", getIntent().getStringExtra("cr_section"));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

    }


    public void getAllStudents(View view) {


        crClassAdapter = new crClassAdapter(MainActivityCr.this, crClassActivityData.getListData());
        recyclerViewStdnts.setLayoutManager(new LinearLayoutManager(MainActivityCr.this));
        recyclerViewStdnts.setAdapter(crClassAdapter);
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        recyclerViewStdnts.setItemAnimator(itemAnimator);
        crClassAdapter.setItemClickCallback(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        get the id of clicked menu item to do some functionality on that
        int menuItemid = item.getItemId();
        switch (menuItemid) {
            case R.id.menu_logout:
//                preferencemanger sa kho k apna apko khali kr la or satha islgged in k leya value ko false kr la
                session.setLogin(false, "", "");
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    //    These are the callbacks coming from the dataAdapter of the CRCLASS
    @Override
    public void onItemClick(int p) {

        new ViewStudentDetailsDialogue(MainActivityCr.this, crClassActivityData.stdntName[p], crClassActivityData.stdntContact[p], crClassActivityData.stdntRollNumber[p]).show();

    }

    @Override
    public void onSecondaryIconClick(int p) {
        new EditStudentDetailsDialogue(MainActivityCr.this, crClassActivityData.stdntName[p], crClassActivityData.stdntContact[p], crClassActivityData.stdntRollNumber[p]).show();
    }


    //    The Ccustom DialogueBox for Viewing the User
    public class ViewStudentDetailsDialogue extends Dialog {
        String s, s1, s2;
        TextView stdRollNumb, stdntName, stdntContact;
        Button buttonDismmser;

        public ViewStudentDetailsDialogue(@NonNull Context context, String s, String s1, String s2) {
            super(context);
            this.s = s;
            this.s1 = s1;
            this.s2 = s2;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.student_view_details_dlg_box);
            getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);   //this line makes the dialogueBox
            stdRollNumb = (TextView) findViewById(R.id.txt_vu_dtal_r_numb);
            stdntName = (TextView) findViewById(R.id.txt_vu_dtal_std_name);
            stdntContact = (TextView) findViewById(R.id.txt_vu_dtail_std_cntct);
            buttonDismmser = (Button) findViewById(R.id.btn_dtailVu_stdnt_exit);

            stdRollNumb.setText(s2);
            stdntName.setText(s);
            stdntContact.setText(s1);

            buttonDismmser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                killing the dialogbox
                    dismiss();
                }
            });

        }
    }

    //    The Ccustom DialogueBox for EDITING the User
    public class EditStudentDetailsDialogue extends Dialog {
        String s, s1, s2;
        String updatedRNumber, updatedName, updateContact;
        EditText stdRollNumb, stdntName, stdntContact;
        Button buttonDismmser, buttonUpdater;

        public EditStudentDetailsDialogue(@NonNull Context context, String s, String s1, String s2) {
            super(context);
            this.s = s;
            this.s1 = s1;
            this.s2 = s2;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.edt_stdnt_details_by_user_dlg_box);
            getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);   //this line makes the dialogueBox

            stdRollNumb = (EditText) findViewById(R.id.edt_stdnt_r_numb);
            stdntName = (EditText) findViewById(R.id.edt_stdnt_name);
            stdntContact = (EditText) findViewById(R.id.edt_stdnt_contct);

            buttonDismmser = (Button) findViewById(R.id.btn_edtVu_stdnt_exit);
            buttonUpdater = (Button) findViewById(R.id.btn_edtVu_stdnt_update);

            stdRollNumb.setText(s2);
            stdntName.setText(s);
            stdntContact.setText(s1);

            buttonDismmser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                killing the dialogbox
                    dismiss();
                }
            });


//            updating if the data is changed
            buttonUpdater.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updatedRNumber=    stdRollNumb.getText().toString();
                    updatedName  = stdntName.getText().toString();
                    updateContact =   stdntContact.getText().toString();
                    if(updatedRNumber.isEmpty()||updatedName.isEmpty()||updateContact.isEmpty()){
                        stdRollNumb.setError("please fill out all fields");
                        stdntName.setError("please fill out all fields");
                        stdntContact.setError("please fill out all fields");
                    }else{
                        updateTheServerDb(updatedRNumber,updatedName,updateContact);
                    }

                }
            });

        }


        private void updateTheServerDb(String updatedRNumber, String updatedName, String updateContact) {
//            StringRequest stringRequest = new StringRequest(1,)

        }
    }
}
