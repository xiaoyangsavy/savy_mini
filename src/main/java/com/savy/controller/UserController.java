package com.savy.controller;

import com.savy.model.User;
import com.savy.service.UserService;
import com.savy.util.Result;
import com.savy.util.ResultStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

//@CrossOrigin(origins = "*", maxAge = 3600)  //解决跨域
@RequestMapping(value = "/user")
@Controller
public class UserController {

    @Autowired
    UserService userService;

    /*@RequestMapping(value = "/login")
    @ResponseBody
    public String login() {
        System.out.println("call /user/login");
        Integer userId = userService.getUserId("admin","admin");
        return String.valueOf(userId);
    }*/

    @RequestMapping(value = "/getUser",method = {RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Result<User> getUser(@RequestParam int userId) {
        System.out.println("call /user/getUser");
        Result<User> result=new Result<User>();
        User user = userService.getUserById(userId);
        if(user!=null) {
            result.setResultStatus(ResultStatus.SUCCESS);
            result.setMessage("获取用户信息成功！");
            result.setData(user);
        }else{
            result.setResultStatus(ResultStatus.NO_DATA);
            result.setMessage("获取用户信息失败！");
        }
        return result;
    }
    @RequestMapping(value = "/register",method = {RequestMethod.POST},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Result<Integer> register(@RequestBody Map<String,String> myMap){
        System.out.println("call /user/register");
        String userName=myMap.get("userName");
        String password=myMap.get("password");
        String sex=myMap.get("sex");
        String phone=myMap.get("phone");
        String email=myMap.get("email");
        String sno=myMap.get("sno");
        String realName=myMap.get("realName");
        String college=myMap.get("college");
        Integer r=0;
        Result<Integer> result=new Result<Integer>();
        Integer user_id=userService.selectID(userName);
        if(user_id!=null){
            result.setResultStatus(ResultStatus.FAIL);
            result.setMessage("注册失败！用户名重复");
            result.setData(r);
            return result;
        }
        if(userName==""||userName==null){
            result.setResultStatus(ResultStatus.FAIL);
            result.setMessage("注册失败！用户名不能为空");
            result.setData(r);
            return result;
        }
        if(password==""||password==null){
            result.setResultStatus(ResultStatus.FAIL);
            result.setMessage("注册失败！密码不能为空");
            result.setData(r);
            return result;
        }
        if (userName!=""&&userName!=null&&user_id==null&&password!=""&&password!=null){
            r=userService.insertUser(userName,password,0,"普通用户",sex,phone,email,sno,realName,college);
            result.setResultStatus(ResultStatus.SUCCESS);
            result.setMessage("注册成功！");
            result.setData(r);
        }
        else {
            result.setResultStatus(ResultStatus.FAIL);
            result.setMessage("注册失败！");
            result.setData(r);
        }
        return result;
    }

    @RequestMapping(value = "/addUser",method = {RequestMethod.POST},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Result<Integer> addUser(@RequestBody Map<String,Object> myMap){
        System.out.println("call /user/register");
        String userName=String.valueOf(myMap.get("userName"));
        String password=String.valueOf(myMap.get("password"));
        Integer permissionId=(Integer)myMap.get("permission");
        Integer r=0;
        Result<Integer> result=new Result<Integer>();
        Integer user_id=userService.selectID(userName);
        if (userName!=""&&userName!=null&&user_id==null){
            r=userService.addUser(userName,password,permissionId);
            result.setResultStatus(ResultStatus.SUCCESS);
            result.setMessage("添加用户成功！");
            result.setData(r);
        }
        else {
            result.setResultStatus(ResultStatus.FAIL);
            result.setMessage("添加用户失败！");
            result.setData(r);
        }
        return result;
    }

    @RequestMapping(value = "/updateUser",method = {RequestMethod.POST},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Result<Integer> updateUser(@RequestBody Map<String,Object> myMap){
        System.out.println("call /user/updateUser");
        String userName=String.valueOf(myMap.get("userName"));
        int userId=(Integer)myMap.get("userId");
        String password=String.valueOf(myMap.get("password"));
        Integer permissionId=(Integer)myMap.get("permission");
        System.out.println("%s"+userName+" "+userId+" "+password+" "+permissionId);
        Integer r=0;
        Result<Integer> result=new Result<Integer>();
        if (userId>0){
            r=userService.updateUser(userName,userId,password,permissionId);
            result.setResultStatus(ResultStatus.SUCCESS);
            result.setMessage("修改用户成功！");
            result.setData(r);
        }
        else {
            result.setResultStatus(ResultStatus.FAIL);
            result.setMessage("修改用户失败！");
            result.setData(r);
        }
        return result;
    }
    @RequestMapping(value = "/deleteUser",method = {RequestMethod.POST},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Result<Integer> deleteUser(@RequestBody Map<String,Object> myMap){
        System.out.println("call /user/deleteUser");
        int userId=Integer.valueOf(myMap.get("userId").toString());
        Result<Integer> result=new Result<>();
        Integer r=0;
        if(userId>0){
            r=userService.deleteUser(userId);
            result.setResultStatus(ResultStatus.SUCCESS);
            result.setMessage("删除用户成功！");
            result.setData(r);
        }else {
            result.setResultStatus(ResultStatus.FAIL);
            result.setMessage("删除用户失败！");
            result.setData(r);
        }
        return result;
    }

    //获取用户权限，命名规范get
    @RequestMapping(value = "/getPermission",method = {RequestMethod.GET},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Result<Integer> getPermission(@RequestParam String userName){
        Result<Integer> result=new Result<>();
        System.out.println("call /user/getPermission");
        Integer permission=userService.searchPermission(userName);
        if(permission!=null)
        {
            result.setResultStatus(ResultStatus.SUCCESS);
            result.setMessage("获取用户权限成功！");
            result.setData(permission);
        }else {
            result.setResultStatus(ResultStatus.FAIL);
            result.setMessage("获取用户权限失败！");
            result.setData(permission);
        }

        return result;
    }
    @RequestMapping(value = "/login",method = {RequestMethod.POST},produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Result<Map> login(@RequestBody Map<String,String> myMap){
        System.out.println("call /user/login:"+myMap.get("userName")+";"+myMap.get("password"));
        String userName = myMap.get("userName");
        String password = myMap.get("password");
        Result<Map> result=new Result<>();
        Map map=new HashMap();

        boolean flag=userService.login(userName,password);
        if(flag){
            Integer userId=userService.selectID(userName);
            Integer lastLoginDate=userService.lastLoginDate(userId);
            map.put("userId",userId);
            result.setResultStatus(ResultStatus.SUCCESS);
            result.setMessage("用户登录成功！");
            result.setData(map);
        }else {
            int user_count=userService.user_count(userName);
            if(user_count<=0){
                result.setResultStatus(ResultStatus.FAIL);
                result.setMessage("用户名不存在！");
                result.setData(map);
                return result;
            }else {
                String userPass = userService.userPass(userName);
                if (!userPass.equals(password)) {
                    result.setResultStatus(ResultStatus.FAIL);
                    result.setMessage("密码错误！");
                    result.setData(map);
                    return result;
                }
            }
        }
        return result;

    }
}
