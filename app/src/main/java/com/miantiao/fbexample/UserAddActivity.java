package com.miantiao.fbexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class UserAddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FbUserDAO usersDAO = FbUserDAO.getFbUserDAO();
        setContentView(R.layout.activity_users);

        findViewById(R.id.adduserbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = ((EditText)findViewById(R.id.usernamevalue)).getText().toString();
                String usersex = ((EditText)findViewById(R.id.inputsexvalue)).getText().toString();
                Integer userage = Integer.valueOf(((EditText)findViewById(R.id.inputagevalue)).getText().toString());
                String useraddress = ((EditText)findViewById(R.id.inputaddressvalue)).getText().toString();
                String useremail = ((EditText)findViewById(R.id.inputemailvalue)).getText().toString();

                FbUser user = new FbUser(userName, usersex, userage, useraddress, useremail);
                FbUserDAO.getFbUserDAO().addUser(user);
                //Log.i("FbUserDAO",user.toString());
            }
        });
    }

}