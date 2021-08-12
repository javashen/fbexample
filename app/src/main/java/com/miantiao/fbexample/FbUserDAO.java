package com.miantiao.fbexample;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;

import java.util.ArrayList;
import java.util.List;

import static com.google.firebase.database.Transaction.*;

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
        //fbUsers.add(u1);
        fbUsers.add(u2);
    }

    private void init(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users");

        for( FbUser u : fbUsers){
            myRef.child(u.getUserName()).setValue(u);
        }

    }

    public Integer updateAge(){
        Integer oldAge = 48;
        Integer newAge = 100;
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users").child("lilida").child("age");

        myRef.runTransaction(new Handler(){

            @NonNull
            @Override
            public Result doTransaction(@NonNull MutableData mutableData) {
                Integer dbAge = mutableData.getValue(Integer.class);
                if(null == dbAge){
                    return Transaction.success(mutableData);
                }
                else if(oldAge == dbAge){
                    dbAge = newAge;
                    mutableData.setValue(dbAge);
                    return Transaction.success(mutableData);
                }
                else{
                    return Transaction.abort();
                }
                //return null;
            }

            @Override
            public void onComplete(@Nullable DatabaseError databaseError, boolean b, @Nullable DataSnapshot dataSnapshot) {
                if (b == false) {
                    Log.i("dbtran", "transaction failed ");

                }
                else{
                    Log.i("dbtran", "transaction completed ");
                }
            }
        });

        return 0;
    }
}
