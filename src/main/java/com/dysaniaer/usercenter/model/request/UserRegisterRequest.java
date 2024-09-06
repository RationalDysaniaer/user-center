package com.dysaniaer.usercenter.model.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户注册请求体
 * 用来接收用户注册请求的参数
 * 将对象转换为字节序列的过程称为对象的序列化， 将字节序列恢复为对象的过程称为对象的反序列化。
 * 为什么要实现Serializable接口：序列化类必须实现java.io.Serializable接口，它只是一个标准，没有任何方法需要实现，实现该接口的类对象才能被序列化。
 *
 * @author RationalDysaniaer
 * @date 2024/9/6 8:06
 */
@Data
public class UserRegisterRequest implements Serializable {

    /**
     * 序列化版本号
     * 为了保持版本兼容性，实现序列化接口的类必须定义一个常量，该常量的值表示序列化类的版本。
     * 该序列化版本号由GenerateSerialVersionUID插件生成
     */
    private static final long serialVersionUID = -6763171681860946043L;;

    /**
     *  用户账号
     */
    private String userAccount;

    /**
     * 用户密码
     */
    private String userPassword;

    /**
     * 确认密码
     */
    private String checkPassword;

    /**
     * 星球编号
     */
    private String planetCode;

}
