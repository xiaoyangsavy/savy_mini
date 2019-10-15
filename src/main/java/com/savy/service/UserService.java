package com.savy.service;

import com.savy.dao.UserMapper;
import com.savy.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

//用户
@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public Integer getUserId(String userName,String password){
        Integer userId = userMapper.getUserId(userName,password);
        return userId;
    }

    public User getUserById(int userId){
        User user = userMapper.getUserById(userId);
        return user;
    }

    public Integer insertUser(String userName,String password,int permissionId,String name,String sex,String phone,String email,String sno,String realName,String college){
        Integer insert_user=userMapper.insertUser(userName,password,permissionId,name,sex,phone,email,sno,realName,college);
        return insert_user;
    }

    public Integer addUser(String userName,String password,int permissionId){
        Integer add_User=userMapper.addUser(userName,password,permissionId);
        return  add_User;
    }

    public Integer updateUser(String userName,int userId,String password,Integer permissionId){
        String name=null;
        if(permissionId>0){
            name=userMapper.selectPermissionName(permissionId);
            name=name+"管理员";
        }
        else {
            name="普通用户";
        }
      //  System.out.println("----------------------------"+permissionId+name);
        Integer update_User=userMapper.updateUser(userName,userId,password,permissionId,name);
        return update_User;
    }

    public Integer deleteUser(int userId){
        Integer delete_User=userMapper.deleteUser(userId);
        return  delete_User;
    }
    public Integer searchPermission(String userName){
        Integer search_Permission=userMapper.searchPermission(userName);
        return search_Permission;
    }
    public boolean login(String userName,String password){
        boolean flag = false;
        Integer count=userMapper.login(userName,password);
        if(count==1){
            flag = true;
        }
        return flag;
    }
    public Integer selectID(String userName){
        Integer select_ID=userMapper.selectID(userName);
        return select_ID;
    }
    public Integer user_count(String userName){
        Integer user_count=userMapper.user_count(userName);
        return  user_count;
    }
    public String userPass( String userName){
        String userPass=userMapper.userPass(userName);
        return userPass;
    }
    public Integer lastLoginDate(Integer userId)
    {
        Date lastLoginDate=new Date();
        Integer last_LoginDate=userMapper.lastLoginDate(lastLoginDate,userId);
        return  last_LoginDate;
    }

}
