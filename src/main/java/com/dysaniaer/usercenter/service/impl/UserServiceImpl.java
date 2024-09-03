package com.dysaniaer.usercenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dysaniaer.usercenter.mapper.UserMapper;
import com.dysaniaer.usercenter.model.domain.User;
import com.dysaniaer.usercenter.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.dysaniaer.usercenter.contant.UserConstant.USER_LOGIN_STATE;

/**
 * 用户服务实现类
 * @author RationalDysaniaer
 * @description 针对表【user(用户)】的数据库操作Service实现
 * @createDate 2024-08-30 23:23:28
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * 盐值：混淆密码
     */
    private static final String SALT = "Dysaniaer";


    @Override
    public long userResigester(String userAccount, String userPassword, String checkPassword) {
        //1.校验
        if(StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)){
            //todo 修改为自定义异常
            return -1L;
        }
        if (userAccount.length() < 4){
            return -1L;
        }
        if (userPassword.length() < 8 || !userPassword.equals(checkPassword)){
            return -1L;
        }

        //校验账户不能包含特殊字符
        Pattern pattern=Pattern.compile("^[a-zA-Z0-9]+$");
        //testStr被检测的文本
        Matcher matcher = pattern.matcher(userAccount);
        //匹配上的时候返回true,匹配不通过返回false
        boolean bool = matcher.matches();
        if (!bool){
            return -1L;
        }

        //账户不能重复
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        Long count = userMapper.selectCount(queryWrapper);
        if (count > 0){
            return -1L;
        }

        //2.加密
        String encryptPassword =  DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());

        //3.插入数据
        User user = new User();
        user.setUserAccount(userAccount);
        user.setUserPassword(encryptPassword);
        boolean saveResult = this.save(user);
        if(!saveResult){
            return -1L;
        }
        return user.getId();
    }

    @Override
    public User userLogin(String userAccount, String userPassword, HttpServletRequest request) {
        //1.校验
        if(StringUtils.isAnyBlank(userAccount, userPassword)){
            return null;
        }
        if (userAccount.length() < 4){
            return null;
        }
        if (userPassword.length() < 8){
            return null;
        }

        //校验账户不能包含特殊字符
        Pattern pattern=Pattern.compile("^[a-zA-Z0-9]+$");
        //testStr被检测的文本
        Matcher matcher = pattern.matcher(userAccount);
        //匹配上的时候返回true,匹配不通过返回false
        boolean bool = matcher.matches();
        if (!bool){
            return null;
        }

        //2.加密
        String encryptPassword =  DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());

        //查询用户是否存在
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("userAccount", userAccount);
        queryWrapper.eq("userPassword", encryptPassword);
        User user = userMapper.selectOne(queryWrapper);
        if(user == null){
            log.info("user login failed,userAccount cannot match userPassword");
            return null;
        }

        //3.用户脱敏
        User safetyUser = getSafetyUser(user);

        //4.记录用户的登录态
        request.getSession().setAttribute(USER_LOGIN_STATE, safetyUser);
        return safetyUser;
    }

    /**
     * 用户脱敏
     *
     * @param user
     * @return
     */
    @Override
    public User getSafetyUser(User user){
        if(user == null){
            return null;
        }
        User safetyUser = new User();
        safetyUser.setId(user.getId());
        safetyUser.setUsername(user.getUsername());
        safetyUser.setUserAccount(user.getUserAccount());
        safetyUser.setAvatarUrl(user.getAvatarUrl());
        safetyUser.setGander(user.getGander());
        safetyUser.setPhone(user.getPhone());
        safetyUser.setEmail(user.getUserPassword());
        safetyUser.setUserStatus(user.getUserStatus());
        safetyUser.setCreateTime(user.getCreateTime());
        safetyUser.setUserRole(user.getUserRole());
        return safetyUser;
    }
}




