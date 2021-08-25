package com.miantiao.fbexample;

import java.util.List;

public interface IFbUserDAO {
    public void observeUserList(IUsersObserver observer);
    public void removeObserver(IUsersObserver observer);
    public boolean addUser(FbUser user);
    public List<FbUser> getFbUsers();

    interface IUsersObserver {

        public void OnUserListChange();
    }
}
