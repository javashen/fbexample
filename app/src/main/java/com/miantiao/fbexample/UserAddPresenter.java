package com.miantiao.fbexample;

public class UserAddPresenter implements IUserAddPresenter{
    private IUserAddView userAddView;
    private IFbUserDAO usersDAO;
    public void onUserAdd(){
        String userName = userAddView.getUserName();
        String usersex = userAddView.getUsersex();
        Integer userage = userAddView.getUserage();
        String useraddress = userAddView.getUseraddress();
        String useremail = userAddView.getUseremail();

        FbUser user = new FbUser(userName, usersex, userage, useraddress, useremail);
        usersDAO.addUser(user);
        userAddView.initContentView();
        //Log.i("FbUserDAO",user.toString());
    }

    public UserAddPresenter(IUserAddView view, IFbUserDAO userDAO){
        userAddView = view;
        this.usersDAO = userDAO;
    }
}
