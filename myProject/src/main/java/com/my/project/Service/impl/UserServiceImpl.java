package com.my.project.Service.impl;

import com.my.project.Mapper.LsxxDao;
import com.my.project.Mapper.UserDao;
import com.my.project.entity.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public int toLogin(String username, String password) {
        UserBean userBean = new UserBean();
        userBean.setPassward(password);
        userBean.setUsername(username);
        UserBean user = userDao.getUser(userBean);
        if(null!=user){
            return 0;
        }
        int userid = userDao.getUserId();
        userid++;
        userBean.setUserid(userid);
        int res = userDao.saveUserBean(userBean);

        return res;
    }

    @Override
    public int getUser(String username, String password) {
        UserBean userBean = new UserBean();
        userBean.setPassward(password);
        userBean.setUsername(username);
        UserBean user = userDao.getUser(userBean);
        if(null==user){
            return 0;
        }
        if(password.equals(user.getPassward())){
            return 1;
        }
        return 0;
    }
}
