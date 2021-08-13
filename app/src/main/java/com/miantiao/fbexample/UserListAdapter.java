package com.miantiao.fbexample;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class UserListAdapter extends ArrayAdapter<FbUser> {

    public UserListAdapter(Context context, ArrayList<FbUser> users) {
        super(context, android.R.layout.simple_list_item_1, users);
    }

}
