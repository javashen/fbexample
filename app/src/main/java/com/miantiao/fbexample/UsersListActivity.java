package com.miantiao.fbexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

public class UsersListActivity extends AppCompatActivity implements IFbUserDAO.IUsersObserver {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);
        Log.i("FbUserDAO","UserListActivity is created");
    }

    @Override
    protected void onStart() {
        super.onStart();
        initListView();
        FbUserDAO.getFbUserDAO().observeUserList(this);
        Log.i("FbUserDAO","UserListActivity is started");

    }

    private void initListView() {
        ArrayList<FbUser> users = new ArrayList<FbUser>(FbUserDAO.getFbUserDAO().getFbUsers());
        UserListAdapter usersAdapter = new UserListAdapter(this, users);
        ((ListView)findViewById(R.id.userlistview)).setAdapter(usersAdapter);
    }

    @Override
    public void OnUserListChange() {
        initListView();

    }

    @Override
    protected void onStop() {
        super.onStop();
        FbUserDAO.getFbUserDAO().removeObserver(this);
    }
}