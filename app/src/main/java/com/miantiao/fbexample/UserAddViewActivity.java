package com.miantiao.fbexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class UserAddViewActivity extends AppCompatActivity implements IUserAddView {

    private IUserAddPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        presenter = new UserAddPresenter(this, FbUserDAO.getFbUserDAO());

        findViewById(R.id.adduserbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onUserAdd();
            }
        });

        findViewById(R.id.listuserbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserAddViewActivity.this, UsersListActivity.class);
                startActivity(intent);
            }
        });
    }

//    public void onUserAdd() {
//        String userName = getUserName();
//        String usersex = getUsersex();
//        Integer userage = getUserage();
//        String useraddress = getUseraddress();
//        String useremail = getUseremail();
//
//        FbUser user = new FbUser(userName, usersex, userage, useraddress, useremail);
//        FbUserDAO.getFbUserDAO().addUser(user);
//        initContentView();
//        //Log.i("FbUserDAO",user.toString());
//    }

    //@org.jetbrains.annotations.NotNull
    public String getUseremail() {
        String useremail = ((EditText)findViewById(R.id.inputemailvalue)).getText().toString();
        return useremail;
    }

    public String getUseraddress() {
        return ((EditText)findViewById(R.id.inputaddressvalue)).getText().toString();
    }

    //@org.jetbrains.annotations.NotNull
    public Integer getUserage() {
        Integer userage = Integer.valueOf(((EditText)findViewById(R.id.inputagevalue)).getText().toString());
        return userage;
    }

    public String getUsersex() {
        return ((EditText)findViewById(R.id.inputsexvalue)).getText().toString();
    }

    public String getUserName() {
        return ((EditText)findViewById(R.id.usernamevalue)).getText().toString();
    }

    @Override
    protected void onStart() {
        super.onStart();
        initContentView();
    }

    public void initContentView() {
        ((EditText)findViewById(R.id.usernamevalue)).setText("");
        ((EditText)findViewById(R.id.inputsexvalue)).setText("");
        ((EditText)findViewById(R.id.inputagevalue)).setText("");
        ((EditText)findViewById(R.id.inputaddressvalue)).setText("");
        ((EditText)findViewById(R.id.inputemailvalue)).setText("");
    }
}