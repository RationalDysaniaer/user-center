package com.dysaniaer.usercenter.service;

import com.dysaniaer.usercenter.model.domain.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import javax.annotation.Resource;


/**
 * 用户服务测试
 * @Author RationalDysaniaer
 */
@SpringBootTest
class UserServiceTest {
    @Resource
    private UserService userService;
    @Test
    public void testAddUser() {
        User user = new User();
        user.setUsername("testUser");
        user.setUserAccount("123");
        user.setAvatarUrl("");
        user.setGander(0);
        user.setUserPassword("123456");
        user.setPhone("123");
        user.setEmail("456");

        boolean result = userService.save(user);
        System.out.println(user.getId());
        //断言，判断两个值是否相等
        Assertions.assertEquals(true,result);
        //Assertions.assertTrue(result);
    }

    @Test
    void userResigester() {
        String userAccount = "yupi";
        String userPassword = "12345678";
        String checkPassword = "123456789";
        long result = userService.userResigester(userAccount,userPassword,checkPassword);
        Assertions.assertEquals(-1,result);

        userAccount = "yu";
        userPassword = "12345678";
        checkPassword = "12345678";
        result = userService.userResigester(userAccount,userPassword,checkPassword);
        Assertions.assertEquals(-1,result);

        userAccount = "yupi";
        userPassword = "123456";
        checkPassword = "123456";
        result = userService.userResigester(userAccount,userPassword,checkPassword);
        Assertions.assertEquals(-1,result);

        userAccount = "yu pi";
        userPassword = "12345678";
        checkPassword = "12345678";
        result = userService.userResigester(userAccount,userPassword,checkPassword);
        Assertions.assertEquals(-1,result);

        userAccount = "dogYupi";
        userPassword = "12345678";
        checkPassword = "12345678";
        result = userService.userResigester(userAccount,userPassword,checkPassword);
        Assertions.assertEquals(-1,result);

        userAccount = "yupi";
        userPassword = "12345678";
        checkPassword = "12345678";
        result = userService.userResigester(userAccount,userPassword,checkPassword);
        Assertions.assertTrue(result > 0);
    }
}