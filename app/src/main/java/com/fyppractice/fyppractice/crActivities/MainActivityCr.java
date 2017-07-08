package com.fyppractice.fyppractice.crActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.fyppractice.fyppractice.LoginActivity;
import com.fyppractice.fyppractice.R;
import com.fyppractice.fyppractice.loginPreferenceManager.SessionManager;

public class MainActivityCr extends AppCompatActivity {
    SessionManager session;
    String URL = "http://10.0.2.2/fyppracticedb/check_cr_login.php";
    TextView txtVuWelcome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_cr);
        session = new SessionManager(getApplicationContext());
        txtVuWelcome = (TextView) findViewById(R.id.welcome_msg_txt_vu);
        txtVuWelcome.setText("Welcmoe dear "+getIntent().getStringExtra("cr_name")+"\n this is a place where you are granted \n with the MIGHT");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
//        get the id of clicked menu item to do some functionality on that
        int menuItemid = item.getItemId();
        switch (menuItemid){
            case R.id.menu_logout:
//                preferencemanger sa kho k apna apko khali kr la or satha islgged in k leya value ko false kr la
                session.setLogin(false, "","");
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
