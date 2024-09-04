package com.dysaniaer.usercenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dysaniaer.usercenter.model.domain.User;
import javax.servlet.http.HttpServletRequest;


/**
 * 用户服务
 * @author RationalDysaniaer
 * @description 针对表【user(用户)】的数据库操作Service
 * @createDate 2024-08-30 23:23:28
 */
public interface UserService extends IService<User> {



    /**
     * 用户注册
     * @param userAccount 用户账户
     * @param userPassword 用户密码
     * @param checkPassword 校验密码
     * @param plantCode 星球编号
     * @return 新用户id
     */
    long userResigester(String userAccount, String userPassword, String checkPassword, String plantCode);

    /**
     * 用户登录
     *
     * @param userAccount  用户账户
     * @param userPassword 用户密码
     * @param request       请求
     * @return 脱敏后的用户信息
     */
    User userLogin(String userAccount, String userPassword, HttpServletRequest request);

    /**
     * 用户脱敏
     *
     * @param user
     * @return
     */
    User getSafetyUser(User user);

    /**
     * 用户注销
     * @param request
     * @return
     */
    int userLogout(HttpServletRequest request);
}
