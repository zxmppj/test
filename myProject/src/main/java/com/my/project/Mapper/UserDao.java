package com.my.project.Mapper;

import com.my.project.entity.UserBean;

public interface UserDao {
    UserBean getUser(UserBean user);
    int saveUserBean(UserBean user);
    int getUserId();
}
