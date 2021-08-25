package com.miantiao.fbexample;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mockingDetails;
import static org.mockito.Mockito.when;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UserAddPresenterTest {
    @Mock
    IFbUserDAO userDAO;

    @Mock
    IUserAddView userAddView;

    @Test
    public void onUserAddTest(){
        UserAddPresenter userAddPresenter = new UserAddPresenter(userAddView, userDAO);
        when(userAddView.getUseraddress()).thenReturn("shenzhen");
        when(userAddView.getUserage()).thenReturn(100);
        when(userAddView.getUseremail()).thenReturn("lilida@gmail.com");
        when(userAddView.getUserName()).thenReturn("lilida");
        when(userAddView.getUsersex()).thenReturn("Male");
        doNothing().when(userAddView).initContentView();

        //doNothing().when(userDAO).addUser(any(FbUser.class));
        doAnswer(invocation -> {
            FbUser arg0 = (FbUser)invocation.getArguments()[0];
            assertEquals("lilida", arg0.userName);
            assertEquals((Integer)100, arg0.age);
            //assertEquals((Integer)101, arg0.age);
            //System.out.println(arg0);

            return Boolean.TRUE;
        }).when(userDAO).addUser(any(FbUser.class));

        //when(userDAO.addUser(any(FbUser.class))).thenReturn(Boolean.TRUE);

        userAddPresenter.onUserAdd();

        //System.out.println(mockingDetails(userAddView).getInvocations());


    }
}
