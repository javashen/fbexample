package com.miantiao.fbexample;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class FbUserDAO {
    private static FbUserDAO singleInstance = null;
    //private static Integer bInitialized = 0;
    private List<FbUser> fbUsers = new ArrayList<FbUser>();

    public static FbUserDAO getFbUserDAO(){
        if( null == singleInstance){
            singleInstance = new FbUserDAO();
            singleInstance.createTmpData();
            singleInstance.init();
            //bInitialized = 1;
            return singleInstance;
        };

        return singleInstance;
    }
    private void createTmpData(){
        FbUser u1 = new FbUser("lilida", "male", 48, "guangdong shenzhen", "javashen@163.com");
        FbUser u2 = new FbUser("likelu", "female", 21, "guangdong shenzhen", "abc@gmail.com");
        fbUsers.add(u1);
        fbUsers.add(u2);
    }

    private void init(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users");

        for( FbUser u : fbUsers){
            myRef.child(u.getUserName()).setValue(u);
        }

    }
}
