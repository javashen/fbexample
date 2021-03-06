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
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.google.firebase.database.Transaction.*;

public class FbUserDAO implements  IFbUserDAO{
    private static FbUserDAO singleInstance = null;
    //private static Integer bInitialized = 0;
    private List<FbUser> fbUsers = new ArrayList<FbUser>();
    private List<IUsersObserver> usersObservers = new ArrayList<>();

    public static FbUserDAO getFbUserDAO(){
        if( null == singleInstance){
            singleInstance = new FbUserDAO();
            //singleInstance.createTmpData();
            singleInstance.init();
            Log.i("FbUserDAO","FbUserDAO is created");
            //bInitialized = 1;
            return singleInstance;
        };

        return singleInstance;
    }
    public void observeUserList(IUsersObserver observer){
        usersObservers.add(observer);
        Log.i("FbUserDAO","a observer added");
    }

    public void removeObserver(IUsersObserver observer){
        usersObservers.remove(observer);
        Log.i("FbUserDAO","a observer removed");
    }

    private void trigeObserver(){
        for(IUsersObserver observer : usersObservers){
            observer.OnUserListChange();
        }
    }
    /*private void createTmpData(){
        FbUser u1 = new FbUser("lilida", "male", 48, "guangdong shenzhen", "javashen@163.com");
        FbUser u2 = new FbUser("likelu", "female", 21, "guangdong shenzhen", "abc@gmail.com");
        //fbUsers.add(u1);
        fbUsers.add(u2);
    }*/

    private void init(){
        fbUsers.clear();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("users");

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                fbUsers.clear();
                for(DataSnapshot tempSnapshot : dataSnapshot.getChildren()){
                    fbUsers.add(tempSnapshot.getValue(FbUser.class));
                    //Log.i("FbUserDAO",tempSnapshot.getValue(FbUser.class).toString());
                }
                trigeObserver();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w("FbUserDAO", "loadPost:onCancelled", databaseError.toException());
            }
        };
        myRef.addValueEventListener(postListener);

    }

    public boolean addUser(FbUser user){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        database.getReference("users").child(user.getUserName()).setValue(user);

        return true;
    }

    public List<FbUser> getFbUsers() {
        return fbUsers;
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
