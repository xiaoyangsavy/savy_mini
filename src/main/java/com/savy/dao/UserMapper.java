package com.savy.dao;

import com.savy.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

//用户
public interface UserMapper {

    //获取用户id
    Integer getUserId(@Param("userName") String userName, @Param("password") String password);
    //根据id获取用户信息
    User getUserById(@Param("userId") int userId);
    //注册用户
    Integer insertUser(@Param("userName") String userName, @Param("password") String password, @Param("permissionId") Integer permissionid, @Param("name") String name, @Param("sex") String sex, @Param("phone") String phone, @Param("email") String email, @Param("sno") String sno, @Param("realName") String realName, @Param("college") String college);
    //用户管理——添加用户
    Integer addUser(@Param("userName") String userName, @Param("password") String password, @Param("permissionId") Integer permissionId);
    //用户管理——查询用户
    List<User> selectUser(@Param("start") Integer start, @Param("end") Integer end);
    //用户管理——修改用户
    Integer updateUser(@Param("userName") String userName, @Param("userId") int userId, @Param("password") String password, @Param("permissionId") Integer permissionId, @Param("name") String name);
    //用户管理——删除用户
    Integer deleteUser(@Param("userId") int userId);
    //查找用户权限
    Integer searchPermission(@Param("userName") String userName);
    //用户登录
    Integer login(@Param("userName") String userName, @Param("password") String password);
    //查找用户ID
    Integer selectID(@Param("userName") String userName);
    //统计用户个数
    Integer userCount();
    //查找权限的名称
    String selectPermissionName(@Param("typeId") Integer typeId);
    //查找用户是否存在
    Integer user_count(@Param("userName") String userName);
    //查找用户密码
    String userPass(@Param("userName") String userName);
    //设置最后登录时间
    Integer lastLoginDate(@Param("lastLoginDate") Date lastLoginDate, @Param("userId") Integer userId);

}
