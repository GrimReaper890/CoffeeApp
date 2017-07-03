package com.fyppractice.fyppractice.adminActivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.fyppractice.fyppractice.LoginActivity;
import com.fyppractice.fyppractice.R;
import com.fyppractice.fyppractice.loginPreferenceManager.SessionManager;

public class MainActivityAdmin extends AppCompatActivity {
    SessionManager session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);
        session = new SessionManager(getApplicationContext());
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
