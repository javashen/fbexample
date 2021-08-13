package com.miantiao.fbexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class UsersListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);
    }

    @Override
    protected void onStart() {
        super.onStart();
        ArrayList<FbUser> users = new ArrayList<FbUser>(FbUserDAO.getFbUserDAO().getFbUsers());
        UserListAdapter usersAdapter = new UserListAdapter(this, users);
        ((ListView)findViewById(R.id.userlistview)).setAdapter(usersAdapter);

    }
}